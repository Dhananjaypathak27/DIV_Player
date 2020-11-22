package in.xparticle.divplayer.viewmodels;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khizar1556.mkvideoplayer.MKPlayer;

import java.util.ArrayList;
import java.util.List;

import in.xparticle.divplayer.models.VideoFile;



public class VideoFolderViewModel extends ViewModel {

    private static final String TAG = "VideoFolderViewModel";
    private final MutableLiveData<List<VideoFile>> mVideos = new MutableLiveData<>();
    private final MutableLiveData<String> currentUrl = new MutableLiveData<>();
    List<VideoFile> tempFolderVideos;
    public VideoFolderViewModel(Context context, String folderName) {

        getFolderVideos(context,folderName);
    }

    public LiveData<List<VideoFile>> getVideos(){
        return mVideos;
    }

    public LiveData<String> getCurrentUrl(int position){
        currentUrl.postValue(tempFolderVideos.get(position).getPath());
        return currentUrl;
    }

    public void getFolderVideos(Context context, String folderName) {
        tempFolderVideos = new ArrayList<>();

        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
            MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME
        };

        String selection = MediaStore.Video.Media.DATA + " like?";
        String[] selectionArgs = new String[]{"%" + folderName + "%"};


        Cursor cursor = context.getContentResolver().query(uri,projection,
                selection,selectionArgs,null);
        if(context != null){
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String title = cursor.getString(2);
                String size = cursor.getString(3);
                String dataAdded = cursor.getString(4);
                String duration = cursor.getString(5);
                String fileName = cursor.getString(6);
                VideoFile videoFiles = new VideoFile(id,path,title,size,dataAdded,
                        duration,fileName);
                Log.d("TAG", "getAllVideos: "+path);
                tempFolderVideos.add(videoFiles);
            }
            mVideos.postValue(tempFolderVideos);
            cursor.close();
        }

    }

}
