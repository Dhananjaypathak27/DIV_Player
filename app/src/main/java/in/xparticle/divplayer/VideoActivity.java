package in.xparticle.divplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.khizar1556.mkvideoplayer.MKPlayer;

import java.util.List;

import in.xparticle.divplayer.models.VideoFile;
import in.xparticle.divplayer.viewmodels.VideoFolderViewModel;
import in.xparticle.divplayer.viewmodels.VideoFolderViewModelFactory;

public class VideoActivity extends AppCompatActivity {

    public MKPlayer mkPlayer;
    int position;
    VideoFolderViewModel videoFolderViewModel;
    ConstraintLayout videoLayout;
    private static final String TAG = "VideoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video);
        videoLayout = findViewById(R.id.video_layout);
        String myFolderName = getIntent().getStringExtra("folderName");
        position = Integer.parseInt(getIntent().getStringExtra("position"));

        mkPlayer = new MKPlayer(this);

        mkPlayer.playInFullScreen(true);

        videoFolderViewModel = new ViewModelProvider(this,new VideoFolderViewModelFactory(this.getApplication(),myFolderName)).
                get(VideoFolderViewModel.class);
        subscribeObservers();
        videoFunctionality();
        Log.d(TAG, "onCreate: "+myFolderName+" "+ position);

    }

    private void videoFunctionality() {
        mkPlayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {

                position++;


                try {
                    subscribeObservers();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(VideoActivity.this,"No more video available",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onPreviousClick() {
                if(position>0){
                    position = position -1;
                }
                subscribeObservers();
            }
        });
    }

    private void subscribeObservers(){
        videoFolderViewModel.getCurrentUrl(position).observe(VideoActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mkPlayer.play(s);
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mkPlayer.stop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mkPlayer.pause();
    }
}