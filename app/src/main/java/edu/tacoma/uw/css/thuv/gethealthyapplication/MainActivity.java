package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements
            RegistrationFragment.UserAddListener, SigninFragment.OnFragmentInteractionListener{

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.main_activity) != null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_activity, new SigninFragment())
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void addUser(String url) {

    }
}