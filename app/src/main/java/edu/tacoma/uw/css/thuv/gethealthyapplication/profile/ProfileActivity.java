package edu.tacoma.uw.css.thuv.gethealthyapplication.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import edu.tacoma.uw.css.thuv.gethealthyapplication.HomeActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.WorkoutActivity;

public class ProfileActivity extends AppCompatActivity
                implements ProfileFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.text_profile_toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTitle.setText("My Profile");
        getSupportActionBar().setIcon(R.drawable.small_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if (findViewById(R.id.profile_fragment_contianer) != null ) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.profile_fragment_contianer, new ProfileFragment())
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
        inflater.inflate(R.menu.profile_menu, menu);

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
            case R.id.action_home:
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
                return true;

            case R.id.action_about:
                Toast.makeText(ProfileActivity.this,
                        "This is an app to help you get healthy! ;)",
                        Toast.LENGTH_LONG).show();
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
}