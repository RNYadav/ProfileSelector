package com.example.profileselector.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.profileselector.network.GetDataService;
import com.example.profileselector.network.RetrofitClientInstance;
import com.example.profileselector.model.Profile;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    private static final String TAG = "REPO";
    private ProfileDao profileDao;
    private LiveData<List<Profile>> profiles;

    public ProfileRepository(final Application application) {
        ProfileDatabase database = ProfileDatabase.getInstance(application);
        profileDao = database.profileDao();
        profiles = profileDao.getProfiles();
        fetchInternetData(application,null);
    }

    void fetchInternetData(Application application, @Nullable String gender){
        //Networkcall
        GetDataService dataService = RetrofitClientInstance.getInstance().create(GetDataService.class);
        Call<JsonElement> call;
        if(gender==null)
            call = dataService.getRandomUser();
        else
            call = dataService.getRandomUser(gender);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    try {
                        JSONObject json = new JSONObject(response.body().toString());
                        JSONArray jsonArray = json.optJSONArray("results");
                        Profile[] list = new Profile[jsonArray.length()];
                        for(int i = 0; i<jsonArray.length(); i++)
                            list[i] = new Profile().jsonParser(jsonArray.optJSONObject(i));
                        insert(list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(application, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }

                    //Data obj = new Gson().fromJson(response.body().toString(), Data.class);
                    //Log.d(TAG, "onResponse: "+obj.getResults().size());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause() );
                Toast.makeText(application, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    LiveData<List<Profile>> getProfiles() {
        return profiles;
    }

    private void insert(Profile... profile){
        new InsertProfileAsyncTask(profileDao).execute(profile);
    }
    void update(Profile profile){
        new UpdateProfileAsyncTask(profileDao).execute(profile);
    }
    void delete(Profile profile){
        new DeleteProfileAsyncTask(profileDao).execute(profile);
    }
    void deleteAllProfile(){
        new DeleteAllProfileAsyncTask(profileDao).execute();
    }

    private static class InsertProfileAsyncTask extends AsyncTask<Profile, Void, Void>{
        private ProfileDao profileDao;

        private InsertProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }
        @Override
        protected Void doInBackground(Profile... profiles) {
            for(Profile profile : profiles)
                profileDao.insert(profile);
            return null;
        }
    }

    private static class UpdateProfileAsyncTask extends AsyncTask<Profile, Void, Void>{
        private ProfileDao profileDao;

        private UpdateProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }
        @Override
        protected Void doInBackground(Profile... profiles) {
            for(Profile profile : profiles)
                profileDao.update(profile);
            return null;
        }
    }

    private static class DeleteProfileAsyncTask extends AsyncTask<Profile, Void, Void>{
        private ProfileDao profileDao;

        private DeleteProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }
        @Override
        protected Void doInBackground(Profile... profiles) {
            for(Profile profile : profiles)
                profileDao.delete(profile);
            return null;
        }
    }

    private static class DeleteAllProfileAsyncTask extends AsyncTask<Void, Void, Void>{
        private ProfileDao profileDao;

        private DeleteAllProfileAsyncTask(ProfileDao profileDao){
            this.profileDao = profileDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            profileDao.deleteAllProfiles();
            return null;
        }
    }

}
