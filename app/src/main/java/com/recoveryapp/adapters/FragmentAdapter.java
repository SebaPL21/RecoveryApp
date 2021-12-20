package com.recoveryapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.recoveryapp.activities.DescriptionFragment;
import com.recoveryapp.activities.HowToExerciseFragment;
import com.recoveryapp.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private List<Exercise> exercises = new ArrayList<>();


    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new HowToExerciseFragment();
        }
        return new DescriptionFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}

