package com.recoveryapp.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.recoveryapp.R;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.WorkoutLog;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ProfileHolder>{
    private List<WorkoutLog> workoutLogs = new ArrayList<>();

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View profileV = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.profile_item,parent, false);
        return new ProfileHolder(profileV);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        WorkoutLog currentWorkoutLog= workoutLogs.get(position);

        holder.textViewName.setText(String.valueOf(currentWorkoutLog.getWorkoutLogId()));
        holder.textViewSets.setText("Ilość ćwiczeń: 4");
        holder.textViewDescription.setText("Descirption");

        /*
        Context context =  holder.imageViewWorkout.getContext();
        String iconName = currentWorkout.getImagePath();
        int resID = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        holder.imageViewWorkout.setImageResource(resID);*/
    }
    @Override
    public int getItemCount() {
       return 0;
    }

    public void setWorkoutLogs(List<WorkoutLog> workoutLogs) {
        this.workoutLogs = workoutLogs;
        notifyDataSetChanged();
    }
    class ProfileHolder extends  RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewSets;
        private TextView textViewDescription;
        private ImageView imageViewWorkoutLog;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_workout_log_name);
            textViewSets = itemView.findViewById(R.id.text_view_workout_log_sets);
            textViewDescription = itemView.findViewById(R.id.text_view_workout_log_description);
            imageViewWorkoutLog = itemView.findViewById(R.id.image_view_workout_log);
        }
    }
}
