package in.xparticle.divplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.xparticle.divplayer.adapters.FolderAdapter;
import in.xparticle.divplayer.viewmodels.FolderViewModel;
import in.xparticle.divplayer.viewmodels.FolderViewModelFactory;

public class HomeActivity extends AppCompatActivity {

//    ui
    RecyclerView mRecyclerView;

    //var
    private static final int REQUEST_CODE_PERMISSION = 123;
    FolderAdapter mFolderAdapter;
    List<String> mFolderList;
    FolderViewModel mFolderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mRecyclerView = findViewById(R.id.folder_recyclerView);
        mFolderViewModel = new ViewModelProvider(this, new FolderViewModelFactory(this.getApplication(),"new awesome param")).get(FolderViewModel.class);
        mFolderList = new ArrayList<>();
        permission();

        initRecyclerView();

        subscribeObservers();
//        fakeData();
    }

    private void subscribeObservers(){
        mFolderViewModel.getFolders().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> Folders) {
                mFolderAdapter.setFolder(Folders);
            }
        });
    }
    private void initRecyclerView() {
        mFolderAdapter = new FolderAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        subscribeObservers();
        mRecyclerView.setAdapter(mFolderAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }


    private void permission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(HomeActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
        }
        else{
//            Toast.makeText(HomeActivity.this,"first",Toast.LENGTH_SHORT).show();
                //setting observer in recyclerview
            initRecyclerView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(HomeActivity.this, "Permission Granted 2", Toast.LENGTH_SHORT).show();

                //setting observer in recyclerview
                mRecyclerView = findViewById(R.id.folder_recyclerView);
                mFolderViewModel = new ViewModelProvider(this, new FolderViewModelFactory(this.getApplication(),"new awesome param")).get(FolderViewModel.class);
                mFolderList = new ArrayList<>();
                permission();
                initRecyclerView();
                subscribeObservers();

            }
            else{
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
            }
        }
    }





//    private void fakeData() {
//        String data = "super man";
//        for(int i=0;i<100;i++) {
//            mFolderList.add(data);
//        }
//        mFolderAdapter.notifyDataSetChanged();
//    }
}