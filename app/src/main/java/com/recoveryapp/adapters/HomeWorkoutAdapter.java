package com.recoveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Workout;

import java.util.ArrayList;
import java.util.List;

public class HomeWorkoutAdapter extends RecyclerView.Adapter<HomeWorkoutAdapter.HomeWorkoutHolder> {
    private List<Workout> workoutList = new ArrayList<>();
    private WorkoutAdapter.OnClickListener onClickListener;

    @NonNull
    @Override
    public HomeWorkoutAdapter.HomeWorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_workout_item,parent,false);
        return new HomeWorkoutAdapter.HomeWorkoutHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkoutAdapter.HomeWorkoutHolder holder, int position) {
        Workout currentWorkout = workoutList.get(position);
        holder.textViewName.setText(currentWorkout.getName());
        holder.textViewDifficultLevel.setText("Poziom trudności: "+String.valueOf(currentWorkout.getDifficultLevel()));

        /*settig image*/
        Context context =  holder.imageViewWorkout.getContext();
        String iconName = currentWorkout.getImagePath();
        int resID = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        holder.imageViewWorkout.setImageResource(resID);
    }



    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public void setWorkoutList(List<Workout> workouts){
        this.workoutList = workouts;
        notifyDataSetChanged();
    }


    class HomeWorkoutHolder extends  RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDifficultLevel;
        private ImageView imageViewWorkout;

        public HomeWorkoutHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_home_workout_name);
            textViewDifficultLevel = itemView.findViewById(R.id.text_view_home_difficult_level);
            imageViewWorkout = itemView.findViewById(R.id.image_view_home_workout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onClickListener != null && position != RecyclerView.NO_POSITION){
                        onClickListener.onItemClick(workoutList.get(position));
                    }
                }
            });
        }
    }
    /*On click listener*/
    public interface OnClickListener{
        void onItemClick(Workout workout);
    }
    public void setOnClickListener(WorkoutAdapter.OnClickListener listener){
        this.onClickListener = listener;
    }
}