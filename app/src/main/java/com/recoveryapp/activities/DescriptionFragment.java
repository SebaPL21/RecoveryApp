package com.recoveryapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.recoveryapp.R;
import com.recoveryapp.adapters.ExercisesAdapter;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.ExerciseDescriptionViewModel;
import com.recoveryapp.viewmodel.ExercisesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String Extra_Exercise_ID = "com.recoveryapp.activities.Extra_Exercise_ID";

    // TODO: Rename and change types of parameters
    //private  DescriptionFragmentAdapter adapter;
    private ViewPager2 view;
    private String exercise_id;
    private Exercise exercise;
    private Intent intent;
    TextView exerciseName;
    TextView exerciseSimpleDesc;
    ExerciseDescriptionViewModel viewModel;



    public DescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Description.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(String param1) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        String test = args.getString("index");
        System.out.println(test+"indexTest");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent data = new Intent();
        exercise_id = data.getStringExtra(DescriptionFragment.Extra_Exercise_ID);

        //exerciseName = view.findViewById(R.id.fragment_name);
        //view.findViewById(R.id.fragment_description);
        //System.out.println(exercise_id+"asdasdasda--++++");
        /* if (getArguments() != null) {
            name = getArguments().getString(exercise.getName());
            description = getArguments().getString(exercise_id);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // System.out.println(exercise_id+"asdasdasda--++++");
         ExercisesDescriptionActivity exer = (ExercisesDescriptionActivity) getActivity();
         Exercise exerciseMore = exer.getExecise();
        //System.out.println(exerciseMore.getName());
        String desc =  String.valueOf(exerciseMore.getDescription());
        String name = String.valueOf(exerciseMore.getName());
        System.out.println(desc);
        View v = inflater.inflate(R.layout.fragment_description, container, false);

        exerciseName = v.findViewById(R.id.fragment_name);
        exerciseSimpleDesc = v.findViewById(R.id.fragment_desc);
        exerciseName.setText(name);
        exerciseSimpleDesc.setText(desc);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


      }
}
