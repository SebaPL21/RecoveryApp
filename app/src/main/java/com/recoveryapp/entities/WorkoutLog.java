package com.recoveryapp.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "workout_log_table")
public class WorkoutLog {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Time date;
    private int fk_workoutId;

    public WorkoutLog(Time date, int fk_workoutId) {
        this.date = date;
        this.fk_workoutId = fk_workoutId;
    }

    public int getId() {
        return id;
    }

    public Time getDate() {
        return date;
    }

    public int getFk_workoutId() {
        return fk_workoutId;
    }
}
