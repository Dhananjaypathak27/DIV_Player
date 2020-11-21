package in.xparticle.divplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.khizar1556.mkvideoplayer.MKPlayer;

import in.xparticle.divplayer.models.VideoFile;

public class VideoActivity extends AppCompatActivity {

    MKPlayer mkPlayer;
    private static final String TAG = "VideoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        VideoFile videoFile = getIntent().getParcelableExtra("file");
        mkPlayer = new MKPlayer(this);
        mkPlayer.play(videoFile.getPath());



        Log.d(TAG, "onCreate: "+videoFile.toString());


        mkPlayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {

            }

            @Override
            public void onPreviousClick() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mkPlayer.stop();
    }
}