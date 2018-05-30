package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import edu.tacoma.uw.css.thuv.gethealthyapplication.HomeActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.GymCardioWorkout;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.muscle_group_exercise.MuscleGroupExercise;


/**
 * The Workout activity which provides different types of exercises
 * for different location workouts.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class WorkoutActivity extends AppCompatActivity
        implements WorkoutFragment.OnFragmentInteractionListener,
        GymCardioWorkoutListFragment.OnListFragmentInteractionListener,
        MuscleGroupExerciseListFragment.OnListFragmentInteractionListener,
        MuscleGroupExerciseDetailFragment.OnExerciseDetailFragmentInteractionListener {

    RadioButton gymBtn,homeBtn, cardioBtn, weightLiftingBtn;
    /**
     * Figuring how the activity should be oriented.
     *
     * @param savedInstanceState The given data from another activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.workout_toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.workout_toolbar_tv);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTitle.setText("Workout");
        getSupportActionBar().setIcon(R.drawable.small_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if (findViewById(R.id.workout_fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.workout_fragment_container, new WorkoutFragment())
                    .commit();
        }
    }

    /**
     * Gathering how the activity should be oriented.
     *
     * @param menu The given menu to add to this activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.workout_menu, menu);

        return true;
    }

    /**
     * Choosing what occurs when the menu items are selected.
     *
     * @param theItem The chosen menu item.
     * @return Whether or not the given item was selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem theItem) {

        switch (theItem.getItemId()) {
            case R.id.action_home:
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
                return true;

            case R.id.action_about:
                Toast.makeText(WorkoutActivity.this,
                        "This is an app to help you get healthy! ;)",
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_log_out:
                SharedPreferences sharedPreferences =
                        getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                        .commit();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(theItem);
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
        gymBtn = (RadioButton) findViewById(R.id.gym_btn);
        homeBtn = (RadioButton) findViewById(R.id.home_btn);
        cardioBtn = (RadioButton) findViewById(R.id.cardio_btn);
        weightLiftingBtn = (RadioButton) findViewById((R.id.weight_lifting_btn));

        if (gymBtn.isChecked() && cardioBtn.isChecked()) {
            GymCardioWorkoutListFragment gymCardioFragment = new GymCardioWorkoutListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.workout_fragment_container,
                            gymCardioFragment, null).addToBackStack(null).commit();
        } else if (gymBtn.isChecked() && weightLiftingBtn.isChecked()) {

            MuscleGroupExerciseListFragment muscleGroupExerciseListFragment =
                                            new MuscleGroupExerciseListFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.workout_fragment_container,
                            muscleGroupExerciseListFragment, null).addToBackStack(null).commit();

        } else if (homeBtn.isChecked() && cardioBtn.isChecked()) {

        } else if (homeBtn.isChecked() && weightLiftingBtn.isChecked()) {

        } else {
            Toast.makeText(this, "Only Gym and Cardio are implemented so far." +
                    " Please select Gym and Cardio", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onListFragmentInteraction(MuscleGroupExercise item) {
        MuscleGroupExerciseDetailFragment muscleGroupExerciseDetailFragment
                                    = new MuscleGroupExerciseDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(MuscleGroupExerciseDetailFragment
                .Muscle_Group_Exercise_ITEM_SELECTED, item);

        muscleGroupExerciseDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.workout_fragment_container, muscleGroupExerciseDetailFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onExerciseDetailFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(GymCardioWorkout item) {
        
    }
}