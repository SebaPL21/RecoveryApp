package com.recoveryapp.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithWorkout {
    @Embedded
    public Category category;
    @Relation(parentColumn = "categoryId",entityColumn = "fk_categoryId")
    public List<Workout> workoutList;
}
