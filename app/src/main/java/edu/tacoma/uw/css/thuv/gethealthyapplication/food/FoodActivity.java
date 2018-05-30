package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import edu.tacoma.uw.css.thuv.gethealthyapplication.HomeActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo.FoodVideo;

import static java.net.Proxy.Type.HTTP;

public class FoodActivity extends AppCompatActivity implements FoodFragment.OnFragmentInteractionListener,
        HealthyRecipesFragment.OnFragmentInteractionListener,
        FoodListFragment.OnListFragmentInteractionListener {

    private static final String TAG = "";
    public static Bundle bundle = new Bundle();
    public static final String VIDEO_OBJECT ="video_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //TextView mTitle = (TextView) mToolbar.findViewById(R.id.food_toolbar_tv);

        toolbar.setTitle("Food ");
        setSupportActionBar(toolbar);
        //mTitle.setText("Food");
        getSupportActionBar().setIcon(R.drawable.small_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if (findViewById(R.id.food_fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.food_fragment_container, new FoodFragment())
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



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void selectVideo(FoodVideo item) {

        Uri webpage = Uri.parse(item.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    @Override
    public void shareVideo(FoodVideo item) {
        // Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Click on link to see this awesome video:) "+ item.getUrl());
        sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

    }
}
