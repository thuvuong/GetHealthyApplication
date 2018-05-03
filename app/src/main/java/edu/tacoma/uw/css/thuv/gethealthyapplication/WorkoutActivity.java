package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.ExpandableListViewAdapter;

public class WorkoutActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // get the ExpandableListView
        expListView = (ExpandableListView) findViewById(R.id.expand_list_view);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListViewAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Where would you like to workout at?");
        listDataHeader.add("What type of the workout?");

        // Adding child data
        List<String> location = new ArrayList<String>();
        location.add("Gym");
        location.add("Home");

        List<String> type = new ArrayList<String>();
        type.add("Cardio");
        type.add("Weight Lifting");

        listDataChild.put(listDataHeader.get(0), location); // Header, Child data
        listDataChild.put(listDataHeader.get(1), type);
    }
}

