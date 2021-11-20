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

    private long exerciseId1;
    private long exerciseId2;
    private long exerciseId3;
    private long exerciseId4;

    public WorkoutLog(long fk_workoutId, long exerciseId1, long exerciseId2, long exerciseId3, long exerciseId4) {
        this.fk_workoutId = fk_workoutId;
        this.exerciseId1 = exerciseId1;
        this.exerciseId2 = exerciseId2;
        this.exerciseId3 = exerciseId3;
        this.exerciseId4 = exerciseId4;
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

    public void setFk_workoutId(long fk_workoutId) {
        this.fk_workoutId = fk_workoutId;
    }

    public long getExerciseId1() {
        return exerciseId1;
    }

    public void setExerciseId1(long exerciseId1) {
        this.exerciseId1 = exerciseId1;
    }

    public long getExerciseId2() {
        return exerciseId2;
    }

    public void setExerciseId2(long exerciseId2) {
        this.exerciseId2 = exerciseId2;
    }

    public long getExerciseId3() {
        return exerciseId3;
    }

    public void setExerciseId3(long exerciseId3) {
        this.exerciseId3 = exerciseId3;
    }

    public long getExerciseId4() {
        return exerciseId4;
    }

    public void setExerciseId4(long exerciseId4) {
        this.exerciseId4 = exerciseId4;
    }
}
