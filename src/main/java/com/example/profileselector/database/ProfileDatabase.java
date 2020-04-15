package com.example.profileselector.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.profileselector.model.Name;
import com.example.profileselector.model.Profile;

@Database(entities = Profile.class, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    private static ProfileDatabase instance;

    public abstract ProfileDao profileDao();

    public static synchronized  ProfileDatabase getInstance(Context context){
        if( instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProfileDatabase.class, "profile_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProfileDao profileDao;
        private PopulateDBAsyncTask(ProfileDatabase db) {
            profileDao = db.profileDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 0; i<10; i++) {
                Profile profile = new Profile();
                profile.setGender("male");
                Name name = new Name();
                name.setFirst("User");
                name.setLast(" "+i);
                //profile.setName(name);
                profileDao.insert(profile);
            }
            return null;
        }
    }
}
