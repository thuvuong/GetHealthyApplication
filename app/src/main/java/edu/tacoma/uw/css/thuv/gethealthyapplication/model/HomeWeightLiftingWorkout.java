package edu.tacoma.uw.css.thuv.gethealthyapplication.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.HomeActivity;

/**
 * Gathers and displays weightlifting workouts which should be done at home.
 *
 * @author Team 11
 * @version May 19, 2018
 */
public class HomeWeightLiftingWorkout {
    /* These class constants are used to match attributes in database.*/
    public static final String TITLE = "title";

    /** The title of the workout.*/
    private String mTitle;
    // image to display for each workout
    // private static int mImageID;


    /**
     * Initializes the fields.
     *
     * @param mTitle The title of the workout.
     */
    public HomeWeightLiftingWorkout( String mTitle) {
        //this.mImageID = imageID;
        this.mTitle = mTitle;


    }

    /**
     * Converting JSON code, from the server, into a list of workouts.
     *
     * @param theWorkoutJSON The list of workouts as a JSON code.
     * @return The list of workouts as a GymCardioWorkout object.
     * @throws JSONException
     */
    public static List<HomeWeightLiftingWorkout> parseWorkoutJSON(String theWorkoutJSON)
            throws JSONException {

        List<HomeWeightLiftingWorkout> workoutList = new ArrayList<HomeWeightLiftingWorkout>();
        if (theWorkoutJSON != null) {

            JSONArray arr = new JSONArray(theWorkoutJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                HomeWeightLiftingWorkout workout = new HomeWeightLiftingWorkout(
                        obj.getString(HomeWeightLiftingWorkout.TITLE));

                workoutList.add(workout);

            }

        }

        return workoutList;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String theTitle) {
        mTitle = theTitle;
    }
}
