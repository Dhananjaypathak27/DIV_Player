package in.xparticle.divplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.xparticle.divplayer.adapters.VideoFolderAdapter;
import in.xparticle.divplayer.models.VideoFile;
import in.xparticle.divplayer.viewmodels.FolderViewModel;
import in.xparticle.divplayer.viewmodels.FolderViewModelFactory;
import in.xparticle.divplayer.viewmodels.VideoFolderViewModel;
import in.xparticle.divplayer.viewmodels.VideoFolderViewModelFactory;

public class VideoFolderActivity extends AppCompatActivity {

    //ui
    private TextView titleText;
    private String myFolderName;
    private ImageView backButton;
    private RecyclerView mRecyclerView;

    //variable
    VideoFolderViewModel mVideoFolderViewModel;
    VideoFolderAdapter mVideoFolderAdapter;
    List<VideoFile> videoFiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_folder);

        backButton = findViewById(R.id.back_button);
        myFolderName = getIntent().getStringExtra("folderName");
        backButton.setVisibility(View.VISIBLE);
        titleText = findViewById(R.id.top_folder_name);
        mVideoFolderViewModel = new ViewModelProvider(this,new VideoFolderViewModelFactory(this.getApplication(),myFolderName)).get(VideoFolderViewModel.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleText.setText(myFolderName);
        initRecyclerView();
        subscribeObservers();

    }

    private void subscribeObservers() {
        mVideoFolderViewModel.getVideos().observe(this, new Observer<List<VideoFile>>() {
            @Override
            public void onChanged(List<VideoFile> videoFiles) {
                mVideoFolderAdapter.setVideos(videoFiles);
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.video_folder_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mVideoFolderAdapter = new VideoFolderAdapter(VideoFolderActivity.this);
        mRecyclerView.setAdapter(mVideoFolderAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

    }
}