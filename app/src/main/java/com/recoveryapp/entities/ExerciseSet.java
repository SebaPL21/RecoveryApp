package com.recoveryapp.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_set_table")
public class ExerciseSet {
    @PrimaryKey(autoGenerate = true)
    private long exerciseSetId;

    private long fk_exerciseId;
    private int set;

    public ExerciseSet(long fk_exerciseId, int set) {
        this.fk_exerciseId = fk_exerciseId;
        this.set = set;
    }

    public long getExerciseSetId() {
        return exerciseSetId;
    }

    public void setExerciseSetId(long exerciseSetId) {
        this.exerciseSetId = exerciseSetId;
    }

    public long getFk_exerciseId() {
        return fk_exerciseId;
    }

    public int getSet() {
        return set;
    }
}
