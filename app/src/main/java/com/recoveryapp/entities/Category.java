package com.recoveryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id_category;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId_category() {
        return id_category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
