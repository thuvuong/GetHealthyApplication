package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

/**
 * A fragment which presents healthy recipes for all three meals.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class HealthyRecipesFragment extends Fragment {

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

    public final static String BUTTON_SELECTED = "button_selected";
    /** The breakfast button.*/
    private Button btnBreakfast;

    /** The lunch button.*/
    private Button btnLunch;

    /** The dinner button.*/
    private Button btnDinner;

    /** Required empty public constructor.*/
    public HealthyRecipesFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthyRecipesFragment.
     */
    public static HealthyRecipesFragment newInstance(String param1, String param2) {
        HealthyRecipesFragment fragment = new HealthyRecipesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Gathering how the fragment should be oriented.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /**
     * Selecting the layout of this fragment.
     *
     * @param inflater Specifies how to display the fragment.
     * @param container The container where this fragment will reside.
     * @param savedInstanceState The given data from an activity.
     * @return The view of how this fragment will be displayed.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_healthy_recipes, container,
                                    false);
        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        toolbar.setTitle("Food: Healthy Recipes");

        btnBreakfast = (Button) v.findViewById(R.id.breakfast_btn);
        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity.bundle.putString(BUTTON_SELECTED, "breakfast");
                launchMeal(v);
            }
        });

        btnLunch = (Button) v.findViewById(R.id.lunch_btn);
        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.bundle.putString(BUTTON_SELECTED, "lunch");
                launchMeal(v);
            }
        });

        btnDinner = (Button) v.findViewById(R.id.dinner_btn);
        btnDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bundle.putString(BUTTON_SELECTED, "dinner");
                launchMeal(v);
            }
        });
        return v;
    }

    /**
     * Launches the Meal list fragment.
     *
     * @param v The speicifcs of how the breakfast fragment should look.
     */
    public void launchMeal(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        FoodListFragment foodListFragment = new FoodListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.food_fragment_container, foodListFragment, null)
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