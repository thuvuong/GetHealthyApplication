package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

/**
 * A weight lifting fragment.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class GymWeightLiftingFragment extends Fragment {

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

    /** Required empty public constructor.*/
    public GymWeightLiftingFragment() {

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
        return inflater.inflate(R.layout.fragment_gym_weight_lifting, container,
                                false);
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

/*
Instructions:
    Complete 3 Sets of 12 Repetitions with moderately
    heavy weights for you for each exercise with a
    (30 second) interval break between each set.

Muscle Groups:
    Biceps (Arms):
        Bicep Pullups

        Barbell Curls

        Hammer Curls

    Triceps (Arms):
        Triceps Pulldown [using Cables and Pulleys machine]

        Single Triceps Pull-down [using Cables and Pulleys machine]

        Triceps Skull Crushers

        Triceps Pushups [at the Dip Station]

    Chest:
        Flat Bench Press

        Inclined Dumbbell Press

        Dumbell Flies

        Chest Cable Pulls [from low to high]

    Shoulders:
        Sitting Dumbbell Arnold Press




*/