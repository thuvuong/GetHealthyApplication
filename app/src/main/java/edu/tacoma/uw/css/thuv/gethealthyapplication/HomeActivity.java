/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The home activity where the user can choose between the Food,
 * Profile, or Workout activities.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * Setting up how the activity should be oriented.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        mTitle.setText("Home");
        getSupportActionBar().setIcon(R.drawable.get_healthy_logo_small);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    /**
     * Gathering how the activity should be oriented.
     *
     * @param menu The given menu to add to this activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

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

            case R.id.action_log_out:
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                return true;


            default:
                return super.onOptionsItemSelected(theItem);
        }
    }

    /**
     * Called when the user taps the Food button.
     *
     * @param view Specifics on how to display this activity.
     */
    public void launchFoodActivity(View view) {
        // Launch Food Activity when user click on Food button
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);

    }

    /**
     * Called when the user taps the Workout activity.
     *
     * @param view Specifics on how to display the Workout activity.
     */
    public void launchWorkoutActivity(View view) {
        // Launch Food Activity when user click on Food button
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);

    }
}