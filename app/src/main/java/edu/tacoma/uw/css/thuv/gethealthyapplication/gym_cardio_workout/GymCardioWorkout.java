/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication.gym_cardio_workout;

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

    /* These class constants are used to match the database name.*/
    public static final String ID = "id";
    public static final String TITLE = "title";

    /** Specifies which workout it is.*/
    private String mId;

    /** The title of the workout.*/
    private String mTitle;


    /**
     * Initializes the fields.
     *
     * @param mId Specifies which workout it is.
     * @param mTitle The title of the workout.
     */
    public GymCardioWorkout(String mId, String mTitle) {
        this.mId = mId;
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
                                            obj.getString(GymCardioWorkout.ID),
                                            obj.getString(GymCardioWorkout.TITLE));

                workoutList.add(workout);
            }

        }

        return workoutList;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public void setTitle(String theTitle) {
        mTitle = theTitle;
    }

    public void setId(String theId) {
        mId = theId;
    }
}