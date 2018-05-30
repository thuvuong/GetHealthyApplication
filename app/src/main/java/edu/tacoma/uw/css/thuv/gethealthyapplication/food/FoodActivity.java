package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.breakfastvideo.BreakfastVideo;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.log.LogInformation;

/**
 * Organizes the different Food fragments.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class FoodActivity extends AppCompatActivity
                implements FoodFragment.OnFragmentInteractionListener,
        HealthyRecipesFragment.OnFragmentInteractionListener,
        BreakfastListFragment.OnListFragmentInteractionListener,
        BreakfastVideoFragment.OnFragmentInteractionListener,
        LogFragment.OnListFragmentInteractionListener,
        MealLogFragment.OnFragmentInteractionListener,
        BreakfastMealFragment.OnFragmentInteractionListener,
        LunchMealFragment.OnFragmentInteractionListener,
        DinnerMealFragment.OnFragmentInteractionListener{

    public static final String VIDEO_OBJECT ="video_object";

    /**
     * Displays a fragment.
     *
     * @param savedInstanceState The given data from the previous activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.food_toolbar);
        //TextView mTitle = (TextView) mToolbar.findViewById(R.id.food_toolbar_tv);

        mToolbar.setTitle("Food ");
        setSupportActionBar(mToolbar);
        //mTitle.setText("Food");
        getSupportActionBar().setIcon(R.drawable.get_healthy_logo_small);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if (findViewById(R.id.fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new FoodFragment())
                    .commit();
        }
    }

    /**
     * Gathering how the activity should be oriented.
     *
     * @param menu The given menu to add to this activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.food_menu, menu);

        return true;
    }

    /**
     * Choosing what occurs when the menu items are selected.
     *
     * @param theItem The chosen menu item.
     * @return Whether or not the given item was selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem theItem) {

        switch (theItem.getItemId()) {

            case R.id.action_about:
                Toast.makeText(FoodActivity.this,
                        "This is an app to help you get healthy! ;)",
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_home:
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
                return true;

            case R.id.action_log_out:
                SharedPreferences sharedPreferences =
                        getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                        .commit();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(theItem);
        }
    }


    /**
     * An empty method implementation to make the implemented
     * fragments work properly.
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void selectVideo(BreakfastVideo item) {
        Bundle args = new Bundle();
        args.putSerializable(VIDEO_OBJECT, item);
        BreakfastVideoFragment breakfastVideoFragment = new BreakfastVideoFragment();
        breakfastVideoFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, breakfastVideoFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void mealLog(Uri uri) {

    }

    @Override
    public void addLog(String url) {
        AddLogTask task = new AddLogTask();
        task.execute(new String[]{url.toString()});
    }

    /**
     * Storing the new user information the databse by doing
     * asynchronous loading.
     */
    private class AddLogTask extends AsyncTask<String, Void, String> {

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
                    response = "Unable to log. Reason: "
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
                    Intent i = new Intent(FoodActivity.this, HomeActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(), "Failed to log",
                            Toast.LENGTH_LONG).show();
                }
            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }
}