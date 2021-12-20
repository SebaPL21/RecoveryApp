package com.recoveryapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDescriptionAdapter extends RecyclerView.Adapter<ExerciseDescriptionAdapter.ExerciseDescriptionHolder> {
    private List<Exercise> exercises = new ArrayList<>();
    private OnClickListener onClickListener;
    @NonNull
    @Override
    public ExerciseDescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_description,parent,false);
        return new ExerciseDescriptionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseDescriptionHolder holder, int position) {
    Exercise currentExercise= exercises.get(position);
    holder.name.setText(currentExercise.getName());
    holder.description.setText(currentExercise.getDescription());
    //holder.level.setText("Poziom trudności: " + String.valueOf(currentExercise.getDifficultLevel()));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercisesDesriptionList(List<Exercise> exercise){
        this.exercises = exercise;
        notifyDataSetChanged();
    }


    class ExerciseDescriptionHolder extends  RecyclerView.ViewHolder{
        private TextView name;
        private TextView description;
      //  private TextView level;

        public ExerciseDescriptionHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fragment_name);
            description = itemView.findViewById(R.id.fragment_description);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onClickListener != null && position != RecyclerView.NO_POSITION){
                        onClickListener.onItemClick(exercises.get(position));
                        System.out.println(exercises.get(position).getExerciseId());
                    }
                }
            });
        }
    }
    public interface OnClickListener{
        void onItemClick(Exercise exercise);
    }
    public void setOnClickListener(OnClickListener listener){
        this.onClickListener = listener;
    }
}
