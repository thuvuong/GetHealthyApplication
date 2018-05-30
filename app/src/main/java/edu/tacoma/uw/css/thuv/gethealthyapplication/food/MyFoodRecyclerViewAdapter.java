package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.FoodListFragment.OnListFragmentInteractionListener;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo.FoodVideo;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFoodRecyclerViewAdapter extends RecyclerView.Adapter<MyFoodRecyclerViewAdapter.ViewHolder> {

    private final List<FoodVideo> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyFoodRecyclerViewAdapter(List<FoodVideo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        //holder.mContentView.setText(mValues.get(position).getUrl());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectVideo(holder.mItem);
            }
        });
        holder.mShareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.shareVideo(holder.mItem);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mShareView;
        public final ImageView mImageView;
        public FoodVideo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mShareView = (ImageView) view.findViewById(R.id.share_image);
            mImageView = (ImageView) view.findViewById(R.id.playyoutube_img);
            mIdView = (TextView) view.findViewById(R.id.title_tv);
            mContentView = (TextView) view.findViewById(R.id.url_tv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
