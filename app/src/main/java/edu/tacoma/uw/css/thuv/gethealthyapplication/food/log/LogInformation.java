package edu.tacoma.uw.css.thuv.gethealthyapplication.food.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.authenticate.LoginActivity;

public class LogInformation implements Serializable{
    public static final String DATE = "date";
    public static final String TYPE = "type";
    public static final String WATER = "water";
    public static final String OTHER = "other";
    public static final String FOOD = "food";
    public static final String VEGI = "vegi";
    public static final String FRUIT = "fruit";
    public static final String MEAT  = "meat";
    public static final String GRAIN = "grain";
    public static final String DAIRY = "dairy";

    private String mDate;
    private String mType;
    private int mWater;
    private int mOther;
    private String mFood;
    private String mVegi;
    private String mFruit;
    private String mGrain;
    private String mMeat;
    private String mDairy;

    public LogInformation(String logDate, String logType,
                          int logWater, int logOther,
                          String logFood, String logVegi,
                          String logFruit, String logGrain,
                          String logMeat, String logDairy){
        mDate = logDate;
        mType = logType;
        mWater = logWater;
        mOther = logOther;
        mFood = logFood;
        mVegi = logVegi;
        mFruit = logFruit;
        mGrain = logGrain;
        mMeat = logMeat;
        mDairy = logDairy;
    }

    public String getDate(){
        return mDate;
    }

    public String getType(){
        return mType;
    }

    public void setDate(String logDate){
        mDate = logDate;
    }

    public void setType(String logType){
        mType = logType;
    }

    public static List<LogInformation> parseLogJSON(String logJSON) throws JSONException {
        List<LogInformation> logList = new ArrayList<LogInformation>();
        if (logJSON != null) {
            JSONArray arr = new JSONArray(logJSON);
            for(int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                LogInformation logInformation = new LogInformation(obj.getString(LogInformation.DATE),
                                                                   obj.getString(LogInformation.TYPE),
                                                                   obj.getInt(String.valueOf(LogInformation.WATER)),
                                                                   obj.getInt(String.valueOf(LogInformation.OTHER)),
                                                                   obj.getString(LogInformation.FOOD),
                                                                   obj.getString(LogInformation.VEGI),
                                                                   obj.getString(LogInformation.FRUIT),
                                                                   obj.getString(LogInformation.GRAIN),
                                                                   obj.getString(LogInformation.MEAT),
                                                                   obj.getString(LogInformation.DAIRY));
                logList.add(logInformation);
            }
        }
        return logList;
    }

    public int getmWater() {
        return mWater;
    }

    public void setmWater(int mWater) {
        this.mWater = mWater;
    }

    public int getmOther() {
        return mOther;
    }

    public void setmOther(int mOther) {
        this.mOther = mOther;
    }

    public String getmFood() {
        return mFood;
    }

    public void setmFood(String mFood) {
        this.mFood = mFood;
    }

    public String getmVegi() {
        return mVegi;
    }

    public void setmVegi(String mVegi) {
        this.mVegi = mVegi;
    }

    public String getmFruit() {
        return mFruit;
    }

    public void setmFruit(String mFruit) {
        this.mFruit = mFruit;
    }

    public String getmGrain() {
        return mGrain;
    }

    public void setmGrain(String mGrain) {
        this.mGrain = mGrain;
    }

    public String getmMeat() {
        return mMeat;
    }

    public void setmMeat(String mMeat) {
        this.mMeat = mMeat;
    }

    public String getmDairy() {
        return mDairy;
    }

    public void setmDairy(String mDairy) {
        this.mDairy = mDairy;
    }
}
