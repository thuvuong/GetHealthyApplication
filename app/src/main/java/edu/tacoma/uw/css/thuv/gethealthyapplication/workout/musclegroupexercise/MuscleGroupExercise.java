package edu.tacoma.uw.css.thuv.gethealthyapplication.workout.musclegroupexercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MuscleGroupExercise implements Serializable {

    private static final String ID = "id";
    private static final String INSTRUCTIONS = "instructions";
    private static final String MUSCLE_GROUP = "muscle_group";
    private static final String FIRST_EXERCISE = "first_exercise";
    private static final String SECOND_EXERCISE = "second_exercise";
    private static final String THIRD_EXERCISE = "third_exercise";
    private static final String FOURTH_EXERCISE = "fourth_exercise";

    private String mId;
    private String mInstructions;
    private String mMuscleGroup;
    private String mFirstExercise;
    private String mSecondExercise;
    private String mThirdExercise;
    private String mFourthExercise;

    public MuscleGroupExercise(String theId, String theInstructions, String theMuscleGroup, String theFirstExercise,
                               String theSecondExercise, String theThirdExercise,
                               String theFourthExercise) {

        mId = theId;
        mInstructions = theInstructions;
        mMuscleGroup = theMuscleGroup;
        mFirstExercise = theFirstExercise;
        mSecondExercise = theSecondExercise;
        mThirdExercise = theThirdExercise;
        mFourthExercise = theFourthExercise;
    }

    public static List<MuscleGroupExercise> parseCourseJSON(String courseJSON) throws JSONException {
        List<MuscleGroupExercise> muscleGroupExerciseList = new ArrayList<MuscleGroupExercise>();
        if (courseJSON != null) {

            JSONArray arr = new JSONArray(courseJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                MuscleGroupExercise muscleGroupExercise = new
                        MuscleGroupExercise(obj.getString(MuscleGroupExercise.ID),
                        obj.getString(MuscleGroupExercise.INSTRUCTIONS),
                        obj.getString(MuscleGroupExercise.MUSCLE_GROUP),
                        obj.getString(MuscleGroupExercise.FIRST_EXERCISE),
                        obj.getString(MuscleGroupExercise.SECOND_EXERCISE),
                        obj.getString(MuscleGroupExercise.THIRD_EXERCISE),
                        obj.getString(MuscleGroupExercise.FOURTH_EXERCISE));

                muscleGroupExerciseList.add(muscleGroupExercise);
            }

        }

        return muscleGroupExerciseList;
    }








    /* Getters and setters below.*/

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String instructions) {
        mInstructions = instructions;
    }

    public String getMuscleGroup() {
        return mMuscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.mMuscleGroup = muscleGroup;
    }

    public String getFirstExercise() {
        return mFirstExercise;
    }

    public void setFirstExercise(String firstExercise) {
        this.mFirstExercise = firstExercise;
    }

    public String getSecondExercise() {
        return mSecondExercise;
    }

    public void setSecondExercise(String secondExercise) {
        this.mSecondExercise = secondExercise;
    }

    public String getThirdExercise() {
        return mThirdExercise;
    }

    public void setThirdExercise(String thirdExercise) {
        this.mThirdExercise = thirdExercise;
    }

    public String getFourthExercise() {
        return mFourthExercise;
    }

    public void setFourthExercise(String forthExercise) {
        this.mFourthExercise = forthExercise;
    }
}