/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.tacoma.uw.css.thuv.gethealthyapplication.gym_cardio_workout.GymCardioWorkout;


/**
 * The Workout activity which provides different types of exercises
 * for different location workouts.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class WorkoutActivity extends AppCompatActivity
        implements GymCardioFragment.OnFragmentInteractionListener,
        WorkoutFragment.OnFragmentInteractionListener,
        GymCardioWorkoutListFragment.OnListFragmentInteractionListener{

    /** The title of the workout page.*/
    public TextView mTitle;

    RadioButton gymBtn, cardioBtn;
    /**
     * Figuring how the activity should be oriented.
     *
     * @param savedInstanceState The given data from another activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

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

    /**
     * Launches the workout fragment from the fragment interaction
     * if the gym and cardio are selected.
     *
     * @param v Provided specification for the layout.
     */
    public void launch(View v) {
        onFragmentInteraction(Uri.EMPTY);
    }


    /**
     * An interaction listener to launch the fragment to show the list
     * of cardio workout list at the gym when gym and cardio options are selected,
     * only gym and cardio option is implemented so far.
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {
        gymBtn = (RadioButton) findViewById(R.id.radio_gym);
        cardioBtn = (RadioButton) findViewById(R.id.btn_cardio);
        if (gymBtn.isChecked() && cardioBtn.isChecked()) {
            GymCardioFragment gymCardioFragment = new GymCardioFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.workout_fragment_container,
                            gymCardioFragment, null).commit();
        } else {
            Toast.makeText(this, "Only Gym and Cardio are implemented so far." +
                    " Please select Gym and Cardio", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Empty interaction listener
     *
     * @param item
     */
    @Override
    public void onListFragmentInteraction(GymCardioWorkout item) {

    }
}