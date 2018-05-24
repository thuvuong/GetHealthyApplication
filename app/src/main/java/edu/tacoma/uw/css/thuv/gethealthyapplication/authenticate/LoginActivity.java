package edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.css.thuv.gethealthyapplication.HomeActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

/**
 * A log in page where a user can log in, with an existing account,
 * or create a new account.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class LoginActivity extends AppCompatActivity implements
                        RegistrationFragment.UserAddListener,
        SigninFragment.SigninInteractionListener {


    private SharedPreferences mSharedPreferences;

    /** An empty constructor.*/
    public LoginActivity() {

    }

    /**
     * Starting the sign in fragment.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        if (!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, new SigninFragment())
                    .commit();
        } else {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        }


    }

    /**
     * Creating a new user to the system.
     *
     * @param url The url which contains the characteristics about
     *            the new user.
     */
    @Override
    public void addUser(String url) {
        AddUserTask task = new AddUserTask();
        task.execute(new String[]{url.toString()});
    }

    /**
     * Allows an existing user to log in into the system.
     *
     * @param url The url which contains log in information
     *            about the current user.
     */
    @Override
    public void logInUser(String url) {
        LogInUserTask task = new LogInUserTask();
        task.execute(new String[]{url.toString()});
    }

    @Override
    public void login(String email, String pwd) {
        mSharedPreferences
                .edit()
                .putBoolean(getString(R.string.LOGGEDIN), true)
                .putString("email", email)
                .commit();

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Storing the new user information the databse by doing
     * asynchronous loading.
     */
    private class AddUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        /**
         * Checks the connection for each url.
         *
         * @param urls The urls.
         * @return The parsed url filled with the new user information.
         */
        @Override
        protected String doInBackground(String... urls){
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls){
                try{
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while((s = buffer.readLine()) != null){
                        response += s;
                    }
                }catch(Exception e){
                    response = "Unable to register. Reason: "
                            + e.getMessage();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }
            return response;
        }

        /**
         * Checking whether the registration process went smoothly,
         * or not; either way, the user will receive a toast message
         * stating the result of the registration process.
         */
        @Override
        protected void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if(status.equals("success")){
                    Toast.makeText(getApplicationContext(), "Successfully Registered!",
                            Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(), "Failed to register",
                            Toast.LENGTH_LONG).show();
                }
            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    /**
     * Checking if a specific user is in the data base.
     */
    private class LogInUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        /**
         * Parsing the url.
         *
         * @param urls The url containing user's log in information.
         * @return The parsed url filled with the user's information.
         */
        @Override
        protected String doInBackground(String... urls){
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls){
                try{
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while((s = buffer.readLine()) != null){
                        response += s;
                        response = response + "&";
                    }
                }catch(Exception e){
                    response = "Unable to log in. Reason: "
                            + e.getMessage();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }
            return response;
        }

        /**
         * If the user exists in the data base, it takes the user
         * to the home page; else, the user is prompted to enter
         * a valid log in information.
         *
         * @param result The result of whether the user exists in
         *               the database.
         */
        @Override
        protected void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if(status.equals("success")){
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to log in",
                            Toast.LENGTH_LONG).show();
                }
            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}



/*

//package edu.tacoma.uw.css.thuv.gethealthyapplication.profile;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONException;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
//import edu.tacoma.uw.css.thuv.gethealthyapplication.model.User;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ProfileFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// */
//public class ProfileFragment extends Fragment {
//
//    private static final String TAG = "ProfileFragment";
//
//    private OnFragmentInteractionListener mListener;
//    private User mUser;
//
//    private TextView mCurrentBMI;
//    private TextView mBMIRange;
//    private TextView mCurrentCalories;
//    private TextView mCaloriesToConsumeToLoseWeight;
//    private TextView mExpectedWaterConsumption;
//
//    private View mView;
//
//    /* Required empty public constructor.*/
//    public ProfileFragment() {
//        mUser = null;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        mView = inflater.inflate(R.layout.fragment_profile, container, false);
//
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS)
//                , Context.MODE_PRIVATE);
//        String email = sharedPreferences.getString("email", "missing");
//
//
//        String url = "tcssandroidthuv.000webhostapp.com/get_healthy_app/list.php?cmd=user&email="
//                + email;
//
//        UserAsyncTask userAsyncTask = new UserAsyncTask();
//        userAsyncTask.execute(new String[]{url});
//
//        if (mUser != null) {
//            mCurrentBMI.setText(mUser.currentBMI());
//            mBMIRange.setText(mUser.healthyBMIRange());
//            mCurrentCalories.setText(mUser.currentCaloriesIntake());
//            mCaloriesToConsumeToLoseWeight
//                    .setText(mUser.caloriesToConsumeToLoseWeight());
//
//            mExpectedWaterConsumption.setText(mUser.expectedWaterConsumption());
//        }
//
////        try {
////            User tempUser = User.parseUserJSON(url);
////
////            mUser = new User(tempUser.getEmail(), tempUser.getPassword(),
////                            tempUser.getFirstName(), tempUser.getLastName(),
////                            tempUser.getHeight(), tempUser.getWeight(),
////                            tempUser.getSex(), tempUser.getAge());
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//
//        return mView;
//    }
//
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//
//    private class UserAsyncTask extends AsyncTask<String, Void, String> {
//
//        private static final String TAG = "UserAsyncTask";
//
//        @Override
//        protected String doInBackground(String... urls) {
//            String response = "";
//            HttpURLConnection urlConnection = null;
//            for (String url : urls) {
//                try {
//                    URL urlObject = new URL(url);
//                    urlConnection = (HttpURLConnection) urlObject.openConnection();
//
//                    InputStream content = urlConnection.getInputStream();
//
//                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
//                    String s = "";
//                    while ((s = buffer.readLine()) != null) {
//                        response += s;
//                    }
//
//                } catch (Exception e) {
//                    response = "Unable to download the list of courses, Reason: "
//                            + e.getMessage();
//                }
//                finally {
//                    if (urlConnection != null)
//                        urlConnection.disconnect();
//                }
//            }
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.i(TAG, "onPostExecute");
//
//            if (result.startsWith("Unable to")) {
//                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT)
//                        .show();
//                return;
//            }
//            try {
//                User tempUser = User.parseUserJSON(result);
//                if (mView != null) {
//                    mCurrentBMI = (TextView) mView.findViewById(R.id.text_current_bmi_display);
//                    mBMIRange = (TextView) mView.findViewById(R.id.text_healthy_bmi_range_display);
//                    mCurrentCalories = (TextView) mView.findViewById(R.id.text_current_calories_display);
//                    mCaloriesToConsumeToLoseWeight = (TextView) mView.findViewById
//                            (R.id.text_calories_to_consume_to_lose_weight_display);
//
//                    mExpectedWaterConsumption = (TextView) mView.findViewById(R.id.text_expected_water_consumption_display);
//
//                    mUser = new User(tempUser.getEmail(), tempUser.getPassword(),
//                            tempUser.getFirstName(), tempUser.getLastName(),
//                            tempUser.getHeight(), tempUser.getWeight(),
//                            tempUser.getSex(), tempUser.getAge());
//                }
//
//
//            }
//            catch (JSONException e) {
//                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT)
//                        .show();
//                return;
//            }
//        }
//    }
//}

//