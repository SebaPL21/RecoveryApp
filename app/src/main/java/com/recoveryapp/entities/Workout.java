package com.recoveryapp.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "workout_table")
public class Workout {
    @PrimaryKey(autoGenerate = true)
    private int id_workout;

    private String name;

    private String description;

    private String imagePath;

    private int difficultLevel;

    private int fk_categoryId;

    public Workout(String name, String description, String imagePath, int difficultLevel, int fk_categoryId) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.difficultLevel = difficultLevel;
        this.fk_categoryId = fk_categoryId;
    }

    public int getId_workout() {
        return id_workout;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getDifficultLevel() {
        return difficultLevel;
    }

    public int getFk_categoryId() {
        return fk_categoryId;
    }
}
