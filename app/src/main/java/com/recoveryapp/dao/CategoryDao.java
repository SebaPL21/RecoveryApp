package com.recoveryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.CategoryWithWorkout;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("Select * FROM category_table")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM category_table WHERE categoryId = :id")
    Category findById(long id);

    @Query("Select * FROM category_table WHERE categoryId = :id")
    CategoryWithWorkout getCategorieByIdWithWorkouts(long id);

}
