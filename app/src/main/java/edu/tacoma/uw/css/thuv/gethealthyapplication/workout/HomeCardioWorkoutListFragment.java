package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.data.GymCardioDB;
import edu.tacoma.uw.css.thuv.gethealthyapplication.data.HomeCardioDB;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.HomeCardioWorkout;

/**
 * A fragment representing a list of cardio workout at home.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class HomeCardioWorkoutListFragment extends Fragment {

    public final static String TAG = "HomeCardioWorkoutListFragment";

    private static final String ARG_COLUMN_COUNT = "column-count";

    /** The url link which contains the list of workouts.*/
    private static final String WORKOUT_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?cmd=homecardiovideo";

    private RecyclerView mRecyclerView;

    /** The number of columns to display the current workout list.*/
    private int mColumnCount = 1;

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnListFragmentInteractionListener mListener;

    /** The list of cardio home workouts.*/
    private List<HomeCardioWorkout> mWorkoutList;

    private HomeCardioDB mHomeCardioDB;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment.
     */
    public HomeCardioWorkoutListFragment() {

    }


    /**
     * Figuring how the fragment should be oriented.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_homecardioworkout_list,
                container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.workout_toolbar);
        toolbar.setTitle("");
        TextView title = (TextView) getActivity().findViewById(R.id.workout_toolbar_tv);
        title.setText("Workout: Cardio At Home");

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(
                        new LinearLayoutManager(context));

            } else {
                mRecyclerView.setLayoutManager(
                        new GridLayoutManager(context, mColumnCount));

            }
            ConnectivityManager connMgr = (ConnectivityManager)
                    getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                HomeCardioWorkoutAsyncTask courseAsyncTask = new HomeCardioWorkoutAsyncTask();
                courseAsyncTask.execute(new String[]{WORKOUT_URL});
            } else {
                Toast.makeText(view.getContext(),
                        "No network connection available. Displaying locally stored data",
                        Toast.LENGTH_SHORT).show();

                if (mHomeCardioDB == null) {
                    mHomeCardioDB = new HomeCardioDB(getActivity());
                }
                if (mWorkoutList == null) {
                    mWorkoutList = mHomeCardioDB.getHomeCardioWorkouts();
                }

                mRecyclerView.setAdapter(new MyHomeCardioWorkoutRecyclerViewAdapter(mWorkoutList, mListener));
            }
        }
        return view;
    }



    /**
     * Setting up the asynchronous loading from the server database.
     */
    private class HomeCardioWorkoutAsyncTask
            extends AsyncTask<String, Void, String> {

        private HomeCardioDB mHomeCardioDB;
        /**
         * Displays the list of workouts.
         *
         * @param result The result of the attempt to retrieve the
         *               list of workouts.
         */
        @Override
        protected void onPostExecute(String result) {

            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(),
                        result, Toast.LENGTH_SHORT).show();

                return;
            }

            try {
                mWorkoutList = HomeCardioWorkout.parseWorkoutJSON(result);
            }
            catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(),
                        e.getMessage(), Toast.LENGTH_SHORT).show();

                return;
            }
            if (!mWorkoutList.isEmpty()) {
                mRecyclerView.setAdapter(new MyHomeCardioWorkoutRecyclerViewAdapter(mWorkoutList, mListener));
            }
        }


        /**
         * Grabs the list of workouts.
         *
         * @param urls The workout url.
         * @return The workout list as  a String.
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection)
                            urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to retrieve workouts, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
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
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        void selectVideo(HomeCardioWorkout item);
    }
}