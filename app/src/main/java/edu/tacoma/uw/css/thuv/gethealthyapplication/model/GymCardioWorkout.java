package edu.tacoma.uw.css.thuv.gethealthyapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Gathers and displays cardio workouts which should be done in the gym.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class GymCardioWorkout implements Serializable {

    /* These class constants are used to match attributes in database.*/
    public static final String TITLE = "title";
    public static final String URL = "url";

    /** The title of the workout.*/
    private String mTitle;

    /**
     * Initializes the fields.
     *
     * @param mTitle The title of the workout.
     */
    public GymCardioWorkout( String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Converting JSON code, from the server, into a list of workouts.
     *
     * @param theWorkoutJSON The list of workouts as a JSON code.
     * @return The list of workouts as a GymCardioWorkout object.
     * @throws JSONException
     */
    public static List<GymCardioWorkout> parseWorkoutJSON(String theWorkoutJSON)
                                                            throws JSONException {

        List<GymCardioWorkout> workoutList = new ArrayList<GymCardioWorkout>();
        if (theWorkoutJSON != null) {

            JSONArray arr = new JSONArray(theWorkoutJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                GymCardioWorkout workout = new GymCardioWorkout(
                                            obj.getString(GymCardioWorkout.TITLE));

                workoutList.add(workout);
            }

        }
        return workoutList;
    }

    /** Get the title of the gym cardio workout*/
    public String getTitle() {
        return mTitle;
    }

    /** Set the title of the workout */
    public void setTitle(String theTitle) {
        mTitle = theTitle;
    }

}