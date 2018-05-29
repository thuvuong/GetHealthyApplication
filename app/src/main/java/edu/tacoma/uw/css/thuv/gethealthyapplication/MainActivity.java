package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.FoodFragment;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.FoodListFragment;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.FoodVideoFragment;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.HealthyRecipesFragment;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.OpenVideoActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo.FoodVideo;

public class MainActivity extends AppCompatActivity implements FoodFragment.OnFragmentInteractionListener,
        HealthyRecipesFragment.OnFragmentInteractionListener,
        FoodListFragment.OnListFragmentInteractionListener,
        FoodVideoFragment.OnFragmentInteractionListener {

    public static Bundle bundle = new Bundle();
    public static final String VIDEO_OBJECT ="video_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast.makeText(MainActivity.this,
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
//        Bundle args = new Bundle();
//        args.putSerializable(VIDEO_OBJECT, item);

        Uri webpage = Uri.parse(item.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//        Intent intent = new Intent(this, OpenVideoActivity.class);
//        intent.putExtra(VIDEO_OBJECT, item);
        startActivity(intent);
//        finish();
//        FoodVideoFragment foodVideoFragment = new FoodVideoFragment();
//        foodVideoFragment.setArguments(args);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.food_fragment_container, foodVideoFragment)
//                .addToBackStack(null)
//                .commit();

    }
}
