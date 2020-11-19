package in.xparticle.divplayer.viewmodels;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FolderViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public FolderViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new FolderViewModel(mApplication, mParam);
    }
}
