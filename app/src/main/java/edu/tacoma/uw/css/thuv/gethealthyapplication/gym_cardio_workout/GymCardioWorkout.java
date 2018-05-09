package edu.tacoma.uw.css.thuv.gethealthyapplication.gym_cardio_workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GymCardioWorkout implements Serializable {
    public static final String ID = "id";
    public static final String TITLE = "title";

    String mId;
    String mTitle;


    public GymCardioWorkout(String mId, String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;

    }


    public static List<GymCardioWorkout> parseWorkoutJSON(String workoutJSON) throws JSONException {
        List<GymCardioWorkout> workoutList = new ArrayList<GymCardioWorkout>();
        if (workoutJSON != null) {

            JSONArray arr = new JSONArray(workoutJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                GymCardioWorkout workout = new GymCardioWorkout(obj.getString(GymCardioWorkout.ID),
                                                                obj.getString(GymCardioWorkout.TITLE));
                workoutList.add(workout);
            }

        }

        return workoutList;
    }


    public String getTitle() {
        return this.mTitle;
    }

    public String getId() {
        return this.mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

}
