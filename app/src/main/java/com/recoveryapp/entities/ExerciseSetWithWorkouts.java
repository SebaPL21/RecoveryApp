package com.recoveryapp.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ExerciseSetWithWorkouts {
    @Embedded
    public ExerciseSet exerciseSet;
    @Relation(
            parentColumn = "exerciseSetId",
            entity = Workout.class,
            entityColumn = "workoutId",
            associateBy = @Junction(value = WorkoutExerciseSetCrossRef.class, parentColumn = "exerciseSetId", entityColumn = "workoutId")
    )
    public List<Workout> workouts;
}
