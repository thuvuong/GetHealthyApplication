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
 * A fragment which presents healthy recipes for all three meals.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class HealthyRecipesFragment extends Fragment
            implements BreakfastFragment.OnFragmentInteractionListener {

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

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
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_healthy_recipes, container,
                                    false);
        TextView mTitle = (TextView) getActivity().findViewById(R.id.food_toolbar_tv);
        mTitle.setText("Food: Healthy Recipes");
        btnBreakfast = (Button) v.findViewById(R.id.breakfast_btn);
        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBreakfast(v);
            }
        });
        return v;
    }

    /**
     * Launches the breakfast fagment.
     *
     * @param v The speicifcs of how the breakfast fragment should look.
     */
    public void launchBreakfast(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        BreakfastFragment breakfastFragment = new BreakfastFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, breakfastFragment, null)
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
                    + " must implement OnFragmentInteractionListener");
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
     * The listener method for the BreakfastFragment being implemented.
     *
     * @param uri The specifics on the click.
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

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