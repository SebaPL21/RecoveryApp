package com.recoveryapp.adapters;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.recoveryapp.R;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ProfileHolder>{
    private List<Exercise> exercises = new ArrayList<>();
    private ProfileViewModel logs;

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View profileV = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.profile_item,parent, false);
        return new ProfileHolder(profileV);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        Exercise currentExercise= exercises.get(position);
      //  holder.ExLvl.setText(String.valueOf(currentExercise.getDifficultLevel()));

    }


    @Override
    public int getItemCount() {
       return 0;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }
    public class ProfileHolder extends RecyclerView.ViewHolder {
        PieChart pieChart;
        public ProfileHolder(@NonNull View profileV) {
            super(profileV);

        }
    }
}
