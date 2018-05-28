package edu.tacoma.uw.css.thuv.gethealthyapplication.profile;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

public class ProfileActivity extends AppCompatActivity
                implements ProfileFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (findViewById(R.id.profile_fragment_contianer) != null ) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.profile_fragment_contianer, new ProfileFragment())
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}