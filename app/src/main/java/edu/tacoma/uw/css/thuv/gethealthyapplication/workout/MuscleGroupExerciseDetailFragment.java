package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.muscle_group_exercise.MuscleGroupExercise;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnExerciseDetailFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MuscleGroupExerciseDetailFragment extends Fragment {

    private OnExerciseDetailFragmentInteractionListener mListener;

    private TextView mExerciseInstructions;
    private TextView mExerciseGroup;
    private TextView mExerciseOne;
    private TextView mExerciseTwo;
    private TextView mExerciseThree;
    private TextView mExerciseFour;

    public MuscleGroupExerciseDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_muscle_group_exercise_detail, container, false);

        mExerciseInstructions = (TextView) view.findViewById(R.id.exercise_instructions);
        mExerciseOne = (TextView) view.findViewById(R.id.exercise_one);
        mExerciseTwo = (TextView) view.findViewById(R.id.exercise_two);
        mExerciseThree = (TextView) view.findViewById(R.id.exercise_three);
        mExerciseFour = (TextView) view.findViewById(R.id.exercise_four);

        return view;
    }

    public void updateView(MuscleGroupExercise theMuscleGroupExercise) {
        if (theMuscleGroupExercise != null) {
            mExerciseInstructions.setText(theMuscleGroupExercise.getInstructions());
            mExerciseOne.setText(theMuscleGroupExercise.getFirstExercise());
            mExerciseTwo.setText(theMuscleGroupExercise.getSecondExercise());
            mExerciseThree.setText(theMuscleGroupExercise.getThirdExercise());
            mExerciseFour.setText(theMuscleGroupExercise.getFourthExercise());
        }
    }

    public final static String Muscle_Group_Exercise_ITEM_SELECTED = "muscle_group_exercise_selected";

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        if (args != null) {
            // Set muscle group exercise information based on argument passed
            updateView((MuscleGroupExercise) args.getSerializable(Muscle_Group_Exercise_ITEM_SELECTED));
        }

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onExerciseDetailFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnExerciseDetailFragmentInteractionListener) {
            mListener = (OnExerciseDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnExerciseDetailFragmentInteractionListener");
        }
    }

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
    public interface OnExerciseDetailFragmentInteractionListener {
        void onExerciseDetailFragmentInteraction(Uri uri);
    }
}