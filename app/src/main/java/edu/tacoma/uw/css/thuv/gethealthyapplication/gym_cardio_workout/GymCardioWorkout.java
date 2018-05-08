package edu.tacoma.uw.css.thuv.gethealthyapplication.gym_cardio_workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GymCardioWorkout implements Serializable {
    public static final String ID = "0";
    public static final String TITLE = "title";

    int mId;
    String mTitle;


    public GymCardioWorkout(int mId, String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;

    }


    public static List<GymCardioWorkout> parseWorkoutJSON(String courseJSON) throws JSONException {
        List<GymCardioWorkout> workoutList = new ArrayList<GymCardioWorkout>();
        if (courseJSON != null) {

            JSONArray arr = new JSONArray(courseJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                GymCardioWorkout workout = new GymCardioWorkout(obj.getInt(GymCardioWorkout.ID), obj.getString(GymCardioWorkout.TITLE));
                workoutList.add(workout);
            }

        }

        return workoutList;
    }


    public String getTitle() {
        return this.mTitle;
    }

    public int getId() {
        return this.mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

}
