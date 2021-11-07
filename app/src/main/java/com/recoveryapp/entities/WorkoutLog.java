package com.recoveryapp.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "workout_log_table")
public class WorkoutLog {
    @PrimaryKey(autoGenerate = true)
    private long workoutLogId;

    //private Date date;
    private long fk_workoutId;

    public WorkoutLog(long fk_workoutId) {
        this.fk_workoutId = fk_workoutId;
    }

    public long getWorkoutLogId() {
        return workoutLogId;
    }

    public void setWorkoutLogId(long workoutLogId) {
        this.workoutLogId = workoutLogId;
    }


    public long getFk_workoutId() {
        return fk_workoutId;
    }
}
