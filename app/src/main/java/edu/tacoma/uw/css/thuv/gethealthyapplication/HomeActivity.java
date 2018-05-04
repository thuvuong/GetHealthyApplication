package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    /** Called when the user taps the Food button */
    public void launchFoodActivity(View view) {
        // Launch Food Activity when user click on Food button
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);

    }

}