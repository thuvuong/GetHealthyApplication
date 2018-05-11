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
import android.widget.TextView;

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
     * Launches the workout fragment.
     *
     * @param v Provided specification for the layout.
     */
    public void launch(View v) {
        GymCardioFragment gymCardioFragment = new GymCardioFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.workout_fragment_container,
                        gymCardioFragment, null).commit();
    }

    /**
     * Empty interaction listener.
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

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