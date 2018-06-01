package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.LogListFragment.OnLogListFragmentInteractionListener;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.log.LogInformation;

import java.util.List;

/**
 * Recylar view adapter for the food list fragment
 *
 * @author Team 11
 * @version May 31, 2018
 *
 * {@link RecyclerView.Adapter} that can display a {@link LogInformation} and makes a call to the
 * specified {@link OnLogListFragmentInteractionListener}.
 */
public class MyLogListRecyclerViewAdapter extends RecyclerView.Adapter<MyLogListRecyclerViewAdapter.ViewHolder> {

    private final List<LogInformation> mValues;
    private final OnLogListFragmentInteractionListener mListener;
    private final String TAG = "OnBindViewHolder";
    public MyLogListRecyclerViewAdapter(List<LogInformation> items, OnLogListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_log, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Log.i(TAG, "onBineViewHolder");
        holder.mIdView.setText(mValues.get(position).getDate());
        Log.i(TAG, "onBineViewHolderIDView");
        holder.mContentView.setText(mValues.get(position).getType());

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
        return mValues.size();
    }

    /**
     * View holder to define the component of the view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public LogInformation mItem;

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
