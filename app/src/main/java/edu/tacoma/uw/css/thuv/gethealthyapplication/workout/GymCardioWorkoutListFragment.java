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
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.GymCardioWorkout;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.HomeWeightLiftingWorkout;

/**
 * A fragment representing a list of cardio workout at the gym.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class GymCardioWorkoutListFragment extends Fragment {

    public final static String TAG = "GymCardioWorkoutListFragment";

    private static final String ARG_COLUMN_COUNT = "column-count";


    private static final String GC_WORKOUT_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?cmd=gymcardioworkout";


    private static final String HW_WORKOUT_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?cmd=homeweightworkout";

    private List<HomeWeightLiftingWorkout> mHWWorkoutList;

    private RecyclerView mRecyclerView;

    private int mColumnCount = 1;

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnListFragmentInteractionListener mListener;

    /** The list of cardio gym workouts.*/
    private List<GymCardioWorkout> mGCWorkoutList;



    private GymCardioDB mGymCardioDB;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment.
     */
    public GymCardioWorkoutListFragment() {

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
                        R.layout.fragment_gymcardioworkout_list,
                        container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.workout_toolbar);
        toolbar.setTitle("");
        TextView title = (TextView) getActivity().findViewById(R.id.workout_toolbar_tv);
        title.setText("Workout: Cardio At the Gym");

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
                GymCardioWorkoutAsyncTask courseAsyncTask = new GymCardioWorkoutAsyncTask();
                courseAsyncTask.execute(new String[]{GC_WORKOUT_URL});
            } else {
                Toast.makeText(view.getContext(),
                        "No network connection available. Displaying locally stored data",
                        Toast.LENGTH_SHORT).show();

                if (mGymCardioDB == null) {
                    mGymCardioDB = new GymCardioDB(getActivity());
                }
                if (mGCWorkoutList == null) {
                    mGCWorkoutList = mGymCardioDB.getGymCardioWorkouts();
                }

                mRecyclerView.setAdapter(new MyGymCardioWorkoutRecyclerViewAdapter(mGCWorkoutList, mListener));
            }
        }
        return view;
    }

    /**
     * Setting up the asynchronous loading from the server database.
     */
    private class GymCardioWorkoutAsyncTask
                            extends AsyncTask<String, Void, String> {

        private GymCardioDB mGymCardioDB;
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
                mGCWorkoutList = GymCardioWorkout.parseWorkoutJSON(result);
            }
            catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(),
                        e.getMessage(), Toast.LENGTH_SHORT).show();

                return;
            }

            // Everything is good, show the list of courses.
           /* if (!mWorkoutList.isEmpty()) {
                mRecyclerView.setAdapter(
                        new MyGymCardioWorkoutRecyclerViewAdapter(
                                mWorkoutList, mListener));
            }*/
            // Everything is good, show the list of courses.
            if (!mGCWorkoutList.isEmpty()) {

                if (mGymCardioDB == null) {
                    mGymCardioDB = new GymCardioDB(getActivity());
                }

                // Delete old data so that you can refresh the local
                // database with the network data.
                //mGymCardioDB.deleteCourses();

                // Also, add to the local database
                for (int i=0; i<mGCWorkoutList.size(); i++) {
                    GymCardioWorkout gym_cardio = mGCWorkoutList.get(i);
                    mGymCardioDB.insertGymCardioWorkout(gym_cardio.getTitle());
                }
                mRecyclerView.setAdapter(new MyGymCardioWorkoutRecyclerViewAdapter(mGCWorkoutList, mListener));
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
        void onListFragmentInteraction(GymCardioWorkout item);
    }
}