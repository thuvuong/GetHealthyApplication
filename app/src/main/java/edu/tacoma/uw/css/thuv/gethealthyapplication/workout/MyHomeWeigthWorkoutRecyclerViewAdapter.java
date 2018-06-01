package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.HomeWeightLiftingWorkout;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.HomeWeigthWorkoutListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * A RecyclerView, which allows us to reuse the formatting for
 * part of the style.
 *
 * @author Team 11
 * @version May 31, 2018
 */
public class MyHomeWeigthWorkoutRecyclerViewAdapter extends
        RecyclerView.Adapter<MyHomeWeigthWorkoutRecyclerViewAdapter.ViewHolder> {

    private final List<HomeWeightLiftingWorkout> mValues;
    private final OnListFragmentInteractionListener mListener;

    /**
     * Initalizing the member variables.
     *
     * @param items The given list of GymCardioWorkout exercises.
     * @param listener The given listener.
     */
    public MyHomeWeigthWorkoutRecyclerViewAdapter(List<HomeWeightLiftingWorkout> items,
                                                  OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_homeweigthworkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mShareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.shareVideo(holder.mItem);
            }
        });
        holder.mShareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.shareVideo(holder.mItem);
            }
        });
        //holder.mContentView.setText(mValues.get(position).getUrl());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectVideo(holder.mItem);
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.selectVideo(holder.mItem);
                    mListener.shareVideo(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Specifying how it should display a list of items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public final ImageView mShareView;
        public HomeWeightLiftingWorkout mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.youtube_img);
            mShareView = (ImageView) view.findViewById(R.id.share_image);
            mIdView = (TextView) view.findViewById(R.id.title_textview);
            mContentView = (TextView) view.findViewById(R.id.url_textview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
