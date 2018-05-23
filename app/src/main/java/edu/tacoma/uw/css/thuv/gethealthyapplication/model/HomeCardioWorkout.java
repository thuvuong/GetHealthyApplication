package edu.tacoma.uw.css.thuv.gethealthyapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Gathers and displays cardio workouts which should be done at home.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class HomeCardioWorkout implements Serializable {

    /* These class constants are used to match attributes in database.*/
    public static final String TITLE = "title";

    /** The title of the workout.*/
    private String mTitle;
    // image to display for each workout
    // private static int mImageID;
    private static List<HomeCardioWorkout> mWorkoutList;

    /**
     * Initializes the fields.
     *
     * @param mTitle The title of the workout.
     */
    public HomeCardioWorkout( String mTitle) {
        //this.mImageID = imageID;
        this.mTitle = mTitle;
        mWorkoutList = new ArrayList<HomeCardioWorkout>();

    }

    /**
     * Converting JSON code, from the server, into a list of workouts.
     *
     * @param theWorkoutJSON The list of workouts as a JSON code.
     * @return The list of workouts as a GymCardioWorkout object.
     * @throws JSONException
     */
    public static List<HomeCardioWorkout> parseWorkoutJSON(String theWorkoutJSON)
            throws JSONException {

        List<HomeCardioWorkout> workoutList = new ArrayList<HomeCardioWorkout>();
        if (theWorkoutJSON != null) {

            JSONArray arr = new JSONArray(theWorkoutJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                HomeCardioWorkout workout = new HomeCardioWorkout(
                        obj.getString(HomeCardioWorkout.TITLE));

                workoutList.add(workout);
                mWorkoutList.add(workout);


            }

        }

        return workoutList;
    }

    public List<HomeCardioWorkout> getHomeCardioWorkoutList() {
        return mWorkoutList;
    }

    public String getTitle() {
        return mTitle;
    }
    //public int getImageId() { return mImageID;}
    //public void setImageId(int imageID) {mImageID = imageID;}
    public void setTitle(String theTitle) {
        mTitle = theTitle;
    }

}