package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

/**
 * Displays the different components of the food section of the
 * application.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class FoodFragment extends Fragment {

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

    /** The button to open the food page.*/
    private Button btnFood;

    /** The button to open the meal log.*/
    private Button btnMealLog;

    /** Required empty public constructor.*/
    public FoodFragment() {

    }

    /**
     * Setting up the fragment.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Picking the layout of this fragment and initializing the Food
     * button.
     *
     * @param inflater Specifies how to display the fragment.
     * @param container The container where this fragment will reside.
     * @param savedInstanceState The given data from an activity.
     * @return The view of how this fragment will be displayed.
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food, container,
                                                            false);
        btnFood = (Button) v.findViewById(R.id.healthy_recipe_btn);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHealthyRecipes(v);
            }
        });
        btnMealLog = (Button) v.findViewById(R.id.meal_log_btn);
        btnMealLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMealLog(v);
            }
        });
        return v;
    }

    /** Called when the user taps the Food button */
    public void launchHealthyRecipes(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        HealthyRecipesFragment newFragment = new HealthyRecipesFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment, null)
                .addToBackStack(null)
                .commit();

    }

    /** Called when the user taps the Meal Log button. */
    public void launchMealLog(View v){
        FragmentManager fragmentManager = getFragmentManager();
        MealLogFragment mealLogFragment = new MealLogFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mealLogFragment, null)
                .addToBackStack(null)
                .commit();
    }


    /**
     * The listener notifies the activity about the button being pressed.
     *
     * @param uri The specifics on the click.
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * Setting up listener when the fragment is first attached
     * to the activity.
     *
     * @param context The new activity where this fragment will be placed.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SigninInteractionListener");
        }
    }

    /**
     * Disassociating with the current activity and stopping the
     * listener.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}