package com.recoveryapp.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_set_table")
public class ExerciseSet {
    @PrimaryKey(autoGenerate = true)
    private int id_exerciseSet;

    private int fk_exerciseId;

    public ExerciseSet(int fk_exerciseId, int set) {
        this.fk_exerciseId = fk_exerciseId;
        this.set = set;
    }

    private int set;

    public int getId_exerciseSet() {
        return id_exerciseSet;
    }

    public int getFk_exerciseId() {
        return fk_exerciseId;
    }

    public int getSet() {
        return set;
    }
}
