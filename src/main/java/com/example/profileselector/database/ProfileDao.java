package com.example.profileselector.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.profileselector.model.Profile;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProfileDao {
    @Insert
    void insert(Profile... profile);

    @Update
    void update(Profile profile);

    @Delete
    void delete(Profile profile);

    @Query("DELETE FROM profile_table")
    void deleteAllProfiles();

    @Query("SELECT * FROM profile_table ORDER BY id DESC")
    DataSource.Factory<Integer, Profile> getProfiles();
}
