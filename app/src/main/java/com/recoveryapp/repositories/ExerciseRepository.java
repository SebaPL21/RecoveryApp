package com.recoveryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.dao.ExerciseDao;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;

import java.util.List;

public class ExerciseRepository {
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exercises;

    public ExerciseRepository(Application application) {
        RecoveryDatabase database = RecoveryDatabase.getInstance(application);
        exerciseDao = database.exerciseDao();
        exercises = exerciseDao.getAllExercises();
    }
    public void insert(Exercise exercise){
        new InsertExerciseAsyncTask(exerciseDao).execute(exercise);
    }
    public void update(Exercise exercise){
        new UpdateExerciseAsyncTask(exerciseDao).execute(exercise);
    }
    public void delete(Exercise exercise){
        new DeleteExerciseAsyncTask(exerciseDao).execute(exercise);
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return exercises;
    }
    public Exercise findById(long id){
        return exerciseDao.findById(id);
    }


    private static class InsertExerciseAsyncTask extends AsyncTask<Exercise,Void, Void> {
        private ExerciseDao exerciseDao;

        public InsertExerciseAsyncTask(ExerciseDao exerciseDao) {
            this.exerciseDao = exerciseDao;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.insert(exercises[0]);
            return null;
        }
    }
    private static class UpdateExerciseAsyncTask extends AsyncTask<Exercise,Void, Void> {
        private ExerciseDao exerciseDao;

        public UpdateExerciseAsyncTask(ExerciseDao exerciseDao) {
            this.exerciseDao = exerciseDao;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.update(exercises[0]);
            return null;
        }
    }
    private static class DeleteExerciseAsyncTask extends AsyncTask<Exercise,Void, Void> {
        private ExerciseDao exerciseDao;

        public DeleteExerciseAsyncTask(ExerciseDao exerciseDao) {
            this.exerciseDao = exerciseDao;
        }

        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.delete(exercises[0]);
            return null;
        }
    }
}
