package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.MuscleGroupExerciseListFragment.OnListFragmentInteractionListener;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.musclegroupexercise.MuscleGroupExercise;

import java.util.List;

/**
 /**
 * A RecyclerView, which allows us to reuse the formatting for
 * part of the style.
 *
 * @author Team 11
 * @version May 31, 2018
 */

public class MyMuscleGroupExerciseRecyclerViewAdapter
        extends RecyclerView.Adapter<MyMuscleGroupExerciseRecyclerViewAdapter.ViewHolder> {

    private final List<MuscleGroupExercise> mMuscleGroupExerciseListValues;
    private final OnListFragmentInteractionListener mListener;

    /**
     * Initalizing the member variables.
     *
     * @param theMuscleGroupExerciseList The given list of GymCardioWorkout exercises.
     * @param listener The given listener.
     */
    public MyMuscleGroupExerciseRecyclerViewAdapter(List<MuscleGroupExercise>
                                                            theMuscleGroupExerciseList,
                                                    OnListFragmentInteractionListener listener) {
        mMuscleGroupExerciseListValues = theMuscleGroupExerciseList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_musclegroupexercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mIdView.setText(mMuscleGroupExerciseListValues.get(position).getId());
        holder.mContentView.setText(mMuscleGroupExerciseListValues.get(position).getMuscleGroup());

        holder.mItem = mMuscleGroupExerciseListValues.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMuscleGroupExerciseListValues.size();
    }

    /**
     * Specifying how it should display a list of items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public MuscleGroupExercise mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
