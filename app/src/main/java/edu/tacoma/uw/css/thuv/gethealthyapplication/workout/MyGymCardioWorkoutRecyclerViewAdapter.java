package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.GymCardioWorkoutListFragment.OnListFragmentInteractionListener;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.GymCardioWorkout;

import java.util.List;

/**
 * A RecyclerView, which allows us to reuse the formatting for
 * part of the style.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class MyGymCardioWorkoutRecyclerViewAdapter
        extends RecyclerView.Adapter<MyGymCardioWorkoutRecyclerViewAdapter.ViewHolder> {

    /**
     * The list of GymCardioWorkout exercises.
     */
    private final List<GymCardioWorkout> mValues;

    /**
     * The listener for this adapter class to notify the activity on
     * any changes.
     */
    private final OnListFragmentInteractionListener mListener;

    /**
     * Initalizing the member variables.
     *
     * @param items The given list of GymCardioWorkout exercises.
     * @param listener The given listener.
     */
    public MyGymCardioWorkoutRecyclerViewAdapter(
                        List<GymCardioWorkout> items,
                        OnListFragmentInteractionListener listener) {

        mValues = items;
        mListener = listener;
    }

    /**
     * Storing the layout.
     *
     * @param parent The parent view group.
     * @param viewType The specified view.
     * @return The newly stored view holder.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gymcardioworkout, parent,
                            false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getTitle());
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

    /**
     * Counts the number of items.
     *
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Specifying how it should display a list of items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public GymCardioWorkout mItem;

        /**
         * Initializing fields.
         *
         * @param view The given layout.
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.imageView9);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}