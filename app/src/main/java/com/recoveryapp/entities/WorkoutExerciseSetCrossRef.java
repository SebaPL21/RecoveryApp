package com.recoveryapp.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

@Entity(primaryKeys = {"workoutId","exerciseSetId"})
public class WorkoutExerciseSetCrossRef {
    public long workoutId;
    public long exerciseSetId;

    public WorkoutExerciseSetCrossRef(long workoutId, long exerciseSetId) {
        this.workoutId = workoutId;
        this.exerciseSetId = exerciseSetId;
    }
}
