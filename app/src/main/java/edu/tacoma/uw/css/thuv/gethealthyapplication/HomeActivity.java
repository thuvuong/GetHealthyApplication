package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.drawable.get_healthy_app_logo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu theMenu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, theMenu);

        return true;
    }

    /**
     * Choosing what occurs when the menu items are selected.
     *
     * @param theItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem theItem) {

        switch (theItem.getItemId()) {
            case R.id.action_home:
                Toast.makeText(this, "You have selected the Home action.",
                        Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_log_out:
                Toast.makeText(this, "You have selected the Log out action.",
                        Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(theItem);
        }
    }
}