package edu.tacoma.uw.css.thuv.gethealthyapplication.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private OnFragmentInteractionListener mListener;
    private User mUser;

    private TextView mCurrentBMI;
    private TextView mBMIRange;
    private TextView mCurrentCalories;
    private TextView mCaloriesToConsumeToLoseWeight;
    private TextView mExpectedWaterConsumption;

    /* Required empty public constructor.*/
    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "missing");


        String url = "https://tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?cmd=user&email="
                        + email;

        UserAsyncTask userAsyncTask = new UserAsyncTask();
        userAsyncTask.execute(new String[]{url});

        mCurrentBMI = (TextView) view.findViewById(R.id.text_current_bmi_display);
        mBMIRange = (TextView) view.findViewById(R.id.text_healthy_bmi_range_display);
        mCurrentCalories = (TextView) view.findViewById(R.id.text_current_calories_display);
        mCaloriesToConsumeToLoseWeight = (TextView) view.findViewById
                (R.id.text_calories_to_consume_to_lose_weight_display);

        mExpectedWaterConsumption = (TextView) view.findViewById(R.id.text_expected_water_consumption_display);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class UserAsyncTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "UserAsyncTask";

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
                    response = "Unable to download the user's information, Reason: "
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

                mUser = User.parseUserJSON(result);

                mCurrentBMI.setText(mUser.getCurrentBMI());
                mBMIRange.setText(mUser.getHealthyBMIRange());
                mCurrentCalories.setText(mUser.getCurrentCaloriesIntake());
                mCaloriesToConsumeToLoseWeight
                        .setText(mUser.GetCaloriesToConsumeToLoseWeight());

                mExpectedWaterConsumption.setText(mUser.getExpectedWaterConsumption());

            } catch (JSONException e) {
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        }
    }
}