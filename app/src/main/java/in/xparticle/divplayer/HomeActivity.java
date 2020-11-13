package in.xparticle.divplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import in.xparticle.divplayer.adapters.FolderAdapter;

public class HomeActivity extends AppCompatActivity {

//    ui
    RecyclerView mRecyclerView;

    //var
    FolderAdapter mFolderAdapter;
    ArrayList<String> mFolderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.folder_recyclerView);
        mFolderList = new ArrayList<>();
        initRecyclerView();
        faceData();
    }

    private void initRecyclerView() {
        mFolderAdapter = new FolderAdapter(this,mFolderList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mRecyclerView.setAdapter(mFolderAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void faceData() {
        String data = "super man";
        for(int i=0;i<100;i++) {
            mFolderList.add(data);
        }
        mFolderAdapter.notifyDataSetChanged();
    }
}