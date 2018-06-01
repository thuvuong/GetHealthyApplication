package edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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


    private static final String TAG = "LoginActivity";

    private SharedPreferences mSharedPreferences;

    private String mUserEmail;

    /** Empty required constructor.*/
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

        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS),
                                                    Context.MODE_PRIVATE);

        if (mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, new SigninFragment())
                    .commit();
        }
    }

    /**
     * Creating a new user to the system.
     *
     * @param url The url which contains the characteristics about
     *            the new user.
     */
    @Override
    public void processingNewUser(String url, String email) {
        mUserEmail = email;

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
    public void processingUser(String url, String email) {
        mUserEmail = email;

        LogInUserTask task = new LogInUserTask();
        task.execute(new String[]{url.toString()});
    }

    public void loginUser() {
        mSharedPreferences
                .edit()
                .putBoolean(getString(R.string.LOGGEDIN), true)
                .putString("email", mUserEmail)
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

        private static final String TAG = "AddUserTask";

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
                            Toast.LENGTH_SHORT).show();

                    loginUser();
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to register",
                            Toast.LENGTH_SHORT).show();
                }

            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_SHORT).show();
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
                    loginUser();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to log in",
                            Toast.LENGTH_SHORT).show();
                }
            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
