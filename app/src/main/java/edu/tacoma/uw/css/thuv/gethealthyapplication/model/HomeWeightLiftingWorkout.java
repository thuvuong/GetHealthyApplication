package edu.tacoma.uw.css.thuv.gethealthyapplication.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.HomeActivity;

/**
 * Gathers and displays weightlifting workouts which should be done at home.
 *
 * @author Team 11
 * @version May 19, 2018
 */
public class HomeWeightLiftingWorkout implements Serializable {

    /* A constant are used to match title attribute in database.*/
    public static final String TITLE = "title";
    /* A constant are used to match category attribute in database.*/
    public static final String CATEGORY = "category";
    /* A constant are used to match url attribute in database.*/
    public static final String URL = "url";


    /** The title of the workout.*/
    private String mTitle;
    private String mUrl;
    private String mCategory;


    /**
     * Initializes the fields.
     *
     * @param title The title of the workout.
     * @param url The link to the video
     * @param category The category for the workout video
     */
    public HomeWeightLiftingWorkout(String title, String url, String category) {
        //this.mImageID = imageID;
        this.mTitle = title;
        this.mUrl = url;
        this.mCategory = category;

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
                        obj.getString(HomeWeightLiftingWorkout.TITLE),
                                obj.getString(HomeWeightLiftingWorkout.URL), obj.getString(HomeWeightLiftingWorkout.CATEGORY));

                workoutList.add(workout);

            }

        }

        return workoutList;
    }

    /** Get the category of the workout type.*/
    public String getCategory() {
        return mCategory;
    }

    /** Set the category for the workout type. */
    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    /** Get the URL link of the home weightlifting video. */
    public String getUrl() {

        return mUrl;
    }

    /** Get the URL for the workout video. */
    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /** Get the title for the workout. */
    public String getTitle() {
        return mTitle;
    }

    /** Set the title for the workout. */
    public void setTitle(String theTitle) {
        mTitle = theTitle;
    }
}
