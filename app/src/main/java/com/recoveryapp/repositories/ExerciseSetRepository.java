package com.recoveryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.dao.ExerciseSetDao;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.ExerciseSet;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExerciseSetRepository {
    private ExerciseSetDao exerciseSetDao;
    private LiveData<List<ExerciseSet>> exerciseSets;

    public ExerciseSetRepository(Application application) {
        RecoveryDatabase database = RecoveryDatabase.getInstance(application);
        exerciseSetDao = database.exerciseSetDao();
        exerciseSets = exerciseSetDao.getAllExerciseSets();
    }

    public void insert(ExerciseSet exerciseSet) {
        new InsertExerciseSetAsyncTask(exerciseSetDao).execute(exerciseSet);
    }

    public void update(ExerciseSet exerciseSet) {
        new UpdateExerciseSetAsyncTask(exerciseSetDao).execute(exerciseSet);
    }

    public void delete(ExerciseSet exerciseSet) {
        new DeleteExerciseSetAsyncTask(exerciseSetDao).execute(exerciseSet);
    }

    public LiveData<List<ExerciseSet>> getExerciseSets() {
        return exerciseSets;
    }

    public List<Long> getLastIds()

    {
        return exerciseSetDao.getLastIds();
    }

    public ExerciseSet findById(long id) {
        return exerciseSetDao.findById(id);
    }

    private static class InsertExerciseSetAsyncTask extends AsyncTask<ExerciseSet, Void, Void> {
        private ExerciseSetDao exerciseSetDao;

        public InsertExerciseSetAsyncTask(ExerciseSetDao exerciseSetDao) {
            this.exerciseSetDao = exerciseSetDao;
        }

        @Override
        protected Void doInBackground(ExerciseSet... exerciseSets) {
            exerciseSetDao.insert(exerciseSets[0]);
            return null;
        }
    }

    private static class UpdateExerciseSetAsyncTask extends AsyncTask<ExerciseSet, Void, Void> {
        private ExerciseSetDao exerciseSetDao;

        public UpdateExerciseSetAsyncTask(ExerciseSetDao exerciseSetDao) {
            this.exerciseSetDao = exerciseSetDao;
        }

        @Override
        protected Void doInBackground(ExerciseSet... exerciseSets) {
            exerciseSetDao.update(exerciseSets[0]);
            return null;
        }
    }

    private static class DeleteExerciseSetAsyncTask extends AsyncTask<ExerciseSet, Void, Void> {
        private ExerciseSetDao exerciseSetDao;

        public DeleteExerciseSetAsyncTask(ExerciseSetDao exerciseSetDao) {
            this.exerciseSetDao = exerciseSetDao;
        }

        @Override
        protected Void doInBackground(ExerciseSet... exerciseSets) {
            exerciseSetDao.delete(exerciseSets[0]);
            return null;
        }
    }
}
