package com.janos.nagy.beadando_dreql5;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public Item(String name) {
        this.name = name;
    }
}