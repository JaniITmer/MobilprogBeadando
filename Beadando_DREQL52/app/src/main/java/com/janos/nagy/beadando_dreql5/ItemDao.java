package com.janos.nagy.beadando_dreql5;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items")
    LiveData<List<Item>> getAll();

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item item);
}