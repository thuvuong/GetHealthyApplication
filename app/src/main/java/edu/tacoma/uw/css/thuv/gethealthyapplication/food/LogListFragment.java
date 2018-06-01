package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.log.LogInformation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * The log list fragment where the user can see a list of log.
 *
 *
 * @author Team 11
 * @version May 31, 2018
 */
public class LogListFragment extends Fragment {


    /** All the private fields for this fragment*/
    private static final String LOG_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?";

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnLogListFragmentInteractionListener mListener;
    private List<LogInformation> mLogList;
    private RecyclerView mRecyclerView;
    private String TAG = "";

    private SharedPreferences mSharedPreferences;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LogListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loglist_list, container, false);

        mSharedPreferences = getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS),
                Context.MODE_PRIVATE);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            LogAsyncTask logAsyncTask = new LogAsyncTask();
            String url = buildLogURL(view);
            logAsyncTask.execute(new String[]{url});
        }
        return view;
    }


    /**
     * Private class that allows the program to synchronize to the database
     * and retrieve the data.
     *
     */
    private class LogAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls){
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null){
                        response += s;
                    }
                } catch (Exception e){
                    response = "Unable to download the list of logs, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
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
                mLogList = LogInformation.parseLogJSON(result);
            } catch (JSONException e){
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!mLogList.isEmpty()){
                mRecyclerView.setAdapter(new MyLogListRecyclerViewAdapter(mLogList, mListener));
            }
        }
    }


    /**
     * Method that will generated the corresponding url address to retrieve the data
     * from the database
     *
     * @param v The view that is currently using
     * @return string value of the url
     */
    public String buildLogURL(View v){

        StringBuilder sb = new StringBuilder(LOG_URL);

        try{
            sb.append("cmd=");
            sb.append(URLEncoder.encode("meallog", "UTF-8"));

            String email = mSharedPreferences.getString("email", "Missing");
            Log.i(TAG, "Email" + email);
            sb.append("&email=");
            sb.append(URLEncoder.encode(email, "UTF-8"));

            Log.i(TAG, "Url is " + sb.toString());
            LogAsyncTask logAsyncTask = new LogAsyncTask();
            logAsyncTask.execute(sb.toString());
        } catch (Exception e){
            Toast.makeText(this.getContext(), "Something wrong with the url" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        return sb.toString();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLogListFragmentInteractionListener) {
            mListener = (OnLogListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLogListFragmentInteractionListener");
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
    public interface OnLogListFragmentInteractionListener {
        void onListFragmentInteraction(LogInformation item);
    }
}
