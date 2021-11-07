package com.recoveryapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private long exerciseId;

    private String name;

    private String description;

    private String steps;

    private String imagePath;

    private int difficultLevel;

    public Exercise(String name, String description, String steps, String imagePath, int difficultLevel) {
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.imagePath = imagePath;
        this.difficultLevel = difficultLevel;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSteps() {
        return steps;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getDifficultLevel() {
        return difficultLevel;
    }
}
