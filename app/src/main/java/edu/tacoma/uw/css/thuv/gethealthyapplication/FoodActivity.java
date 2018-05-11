/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Organizes the different Food fragments.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class FoodActivity extends AppCompatActivity
                implements FoodFragment.OnFragmentInteractionListener,
                HealthyRecipesFragment.OnFragmentInteractionListener,
                BreakfastFragment.OnFragmentInteractionListener{

    /**
     * Displays a fragment.
     *
     * @param savedInstanceState The given data from the previous activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new FoodFragment())
                    .commit();
        }
    }

    /**
     * An empty method implementation to make the implemented
     * fragments work properly.
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}