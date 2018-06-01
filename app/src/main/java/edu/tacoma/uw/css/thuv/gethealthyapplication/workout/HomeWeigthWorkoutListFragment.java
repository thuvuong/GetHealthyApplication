package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo.FoodVideo;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.HomeWeightLiftingWorkout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

 /**
 * A fragment representing a list of weight lifting workout at home.
 *
 * @author Team 11
 * @version May 31, 2018
 */
public class HomeWeigthWorkoutListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String HOMEWEIGHT_WORKOUT_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?cmd=homeweightworkout";
    private static final String TAG = "homeweight";
    private List<HomeWeightLiftingWorkout> mHomeWeightWorkoutList;
    private int mColumnCount = 1;
    private RecyclerView mRecyclerView;
    private OnListFragmentInteractionListener mListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeWeigthWorkoutListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * Picking the layout of this fragment
     *
     * @param inflater Specifies how to display the fragment.
     * @param container The container where this fragment will reside.
     * @param savedInstanceState The given data from an activity.
     * @return The view of how this fragment will be displayed.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homeweigthworkout_list, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.workout_toolbar);
        toolbar.setTitle("");
        TextView title = (TextView) getActivity().findViewById(R.id.workout_toolbar_tv);
        title.setText("Weightlifting At Home Videos");

        Toast.makeText(this.getContext(),
                "Click on the item to see video! You can share by click on share icon :)"
                , Toast.LENGTH_LONG)
                .show();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            HomeWeightAsyncTask homeWeightAsyncTask = new HomeWeightAsyncTask();
            homeWeightAsyncTask.execute(new String[]{HOMEWEIGHT_WORKOUT_URL});


        }
        return view;
    }

    private class HomeWeightAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to download the list of courses, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, "onPostExecute");

            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            try {
                mHomeWeightWorkoutList = HomeWeightLiftingWorkout.parseWorkoutJSON(result);
            }
            catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                return;
            }

// Everything is good, show the list of courses.
            if (!mHomeWeightWorkoutList.isEmpty()) {
                mRecyclerView.setAdapter(new MyHomeWeigthWorkoutRecyclerViewAdapter(mHomeWeightWorkoutList, mListener));
            }

        }
    }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void selectVideo(HomeWeightLiftingWorkout item);
        void shareVideo(HomeWeightLiftingWorkout item);
    }
}
