package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WorkoutActivity extends AppCompatActivity implements GymCardioFragment.OnFragmentInteractionListener, WorkoutFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        if (findViewById(R.id.workout_fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.workout_fragment_container, new WorkoutFragment())
                    .commit();
        }
    }

    public void launch(View v) {
        GymCardioFragment gymCardioFragment = new GymCardioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.workout_fragment_container, gymCardioFragment, null).commit();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
