package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    public MainActivity() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void login(final String theEmail, final String thePassword) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

}