package com.recoveryapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.ExerciseDescriptionViewModel;
import com.recoveryapp.viewmodel.ExercisesViewModel;

import java.util.ArrayList;
import java.util.List;


public class HowToExerciseFragment extends Fragment {
    TextView level, description;
    ImageView imageView;
    private List<Exercise> exercises = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ExercisesDescriptionActivity exer = (ExercisesDescriptionActivity) getActivity();
        Exercise exerciseMore =exer.getExecise();
        String steps = exerciseMore.getSteps();
        String lvl = String.valueOf(exerciseMore.getDifficultLevel());
        View v = inflater.inflate(R.layout.fragment_how_to_exercise, container, false);

        /*Image view*/
        String iconName = exerciseMore.getImagePath();
        int resID = getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName());


        level = v.findViewById(R.id.fragment_level);
        description = v.findViewById(R.id.fragment_description);
        imageView = v.findViewById(R.id.how_to_exercise_image);

        level.setText("Poziom trudności: "+ lvl);
        description.setText(steps);
        imageView.setImageResource(resID);



        return v;
    }
}