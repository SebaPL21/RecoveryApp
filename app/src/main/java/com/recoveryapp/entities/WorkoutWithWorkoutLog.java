package com.recoveryapp.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithWorkoutLog {
    @Embedded
    public Workout workout;
    @Relation(parentColumn ="workoutId",entityColumn = "fk_workoutId")
    public List<WorkoutLog> workoutLogList;
}
