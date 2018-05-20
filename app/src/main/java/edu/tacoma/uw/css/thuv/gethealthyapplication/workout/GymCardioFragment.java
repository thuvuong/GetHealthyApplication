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
 * Displays a list of workouts to do if our user wants to do a cardio
 * workout in the gym.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class GymCardioFragment extends Fragment {

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

    /** Required empty public constructor.*/
    public GymCardioFragment() {

    }


    /**
     * Filling up the container with a new instance of this fragment.
     *
     * @param savedInstanceState The data given from an activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity().findViewById(R.id.workout_fragment_container) != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.workout_fragment_container, new GymCardioWorkoutListFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * Picking the layout of this fragment.
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
        return inflater.inflate(R.layout.fragment_gym_cardio, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}