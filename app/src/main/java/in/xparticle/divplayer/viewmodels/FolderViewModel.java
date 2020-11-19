package in.xparticle.divplayer.viewmodels;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import in.xparticle.divplayer.HomeActivity;
import in.xparticle.divplayer.models.VideoFile;

public class FolderViewModel extends ViewModel {

    private static final String TAG = "FolderViewModel";
    private MutableLiveData<List<String>> mFolders = new MutableLiveData<>();

    public FolderViewModel(Context context,String Str) {

        getAllFolders(context);
    }


    private void getAllFolders(Context context) {
        List<String> tempVideoFolder = new ArrayList<>();
        List<VideoFile> tempVideoFiles = new ArrayList<>();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String projection[] ={
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME
        };


        Cursor cursor = context.getContentResolver().query(uri,projection,
                        null,null,null);

        if(context != null){
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String path = cursor.getString(1);
                String title = cursor.getString(2);
                String size = cursor.getString(3);
                String dataAdded = cursor.getString(4);
                String duration = cursor.getString(5);
                String fileName = cursor.getString(6);

                VideoFile videoFile = new VideoFile(id,path,title,size,dataAdded,duration,fileName);

                // /storage/sd_card/VideoDir/Abc/MyVideoFile.mp4
                int slashFirstIndex = path.lastIndexOf("/");
                String subString = path.substring(0,slashFirstIndex);
                // /storage/sd_card/VideoDir/Abc because last index excluded so slash excluded
                int index = subString.lastIndexOf("/");

                String folderName = subString.substring(index + 1 ,slashFirstIndex);
                //after doing this it will give us "Abc" as a folder name;

                if(!tempVideoFolder.contains(folderName)){
                    tempVideoFolder.add(folderName);
                    Log.d(TAG, "getAllFolders: "+folderName);
                }


                //can be use to access video files for future use
                tempVideoFiles.add(videoFile);



            }
        }
        mFolders.postValue(tempVideoFolder);

    }


    public LiveData<List<String>> getFolders(){
        return mFolders;
    }


    
}
