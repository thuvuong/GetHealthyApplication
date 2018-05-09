package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.gym_cardio_workout.GymCardioWorkout;

public class WorkoutActivity extends AppCompatActivity implements GymCardioFragment.OnFragmentInteractionListener,
        WorkoutFragment.OnFragmentInteractionListener, GymCardioWorkoutListFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mTitle.setText("Workout");
        getSupportActionBar().setIcon(R.drawable.get_healthy_logo_small);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

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

    @Override
    public void onListFragmentInteraction(GymCardioWorkout item) {

    }
}
