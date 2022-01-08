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

    private long exerciseSetId1;
    private long exerciseSetId2;
    private long exerciseSetId3;
    private long exerciseSetId4;


    public WorkoutLog(long fk_workoutId, long exerciseSetId1, long exerciseSetId2, long exerciseSetId3, long exerciseSetId4) {
        this.fk_workoutId = fk_workoutId;
        this.exerciseSetId1 = exerciseSetId1;
        this.exerciseSetId2 = exerciseSetId2;
        this.exerciseSetId3 = exerciseSetId3;
        this.exerciseSetId4 = exerciseSetId4;
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


    public long getExerciseSetId1() {
        return exerciseSetId1;
    }


    public long getExerciseSetId2() {
        return exerciseSetId2;
    }


    public long getExerciseSetId3() {
        return exerciseSetId3;
    }


    public long getExerciseSetId4() {
        return exerciseSetId4;
    }


}
