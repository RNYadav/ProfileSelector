package com.example.profileselector.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.profileselector.database.ProfileRepository;
import com.example.profileselector.model.Profile;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository repository;
    private LiveData<PagedList<Profile>> profiles;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application);
        profiles = repository.getProfiles();
    }

    public LiveData<PagedList<Profile>> getProfiles() {
        return profiles;
    }

    public void update(Profile profile){
        if(profile.getStatus()==1)
            repository.delete(profile);
        else
            repository.update(profile);
    }

    public void gernderSpecificProfiles(String gender){
        repository.deleteAllProfile();
        repository.fetchInternetData(getApplication(), gender);

    }
}
