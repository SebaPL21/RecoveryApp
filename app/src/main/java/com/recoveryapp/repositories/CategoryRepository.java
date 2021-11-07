package com.recoveryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.entities.Category;

import java.util.List;

public class CategoryRepository {
    private CategoryDao categoryDao;
    private LiveData<List<Category>> categories;

    public CategoryRepository(Application application) {
        RecoveryDatabase database = RecoveryDatabase.getInstance(application);
        categoryDao = database.categoryDao();
        categories = categoryDao.getAllCategories();
    }
    public void insert(Category category){
        new InsertCategoryAsyncTask(categoryDao).execute(category);
    }
    public void delete(Category category){
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }
    public void update(Category category){
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }

    public LiveData<List<Category>> getAllCategories() {
        return categories;
    }
    public Category findById(long id) {
        return categoryDao.findById(id);
    }


    private static class InsertCategoryAsyncTask extends AsyncTask<Category,Void, Void> {
        private CategoryDao categoryDao;

        public InsertCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }
    private static class UpdateCategoryAsyncTask extends AsyncTask<Category,Void, Void> {
        private CategoryDao categoryDao;

        public UpdateCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }
    private static class DeleteCategoryAsyncTask extends AsyncTask<Category,Void, Void> {
        private CategoryDao categoryDao;

        public DeleteCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.delete(categories[0]);
            return null;
        }
    }
}
