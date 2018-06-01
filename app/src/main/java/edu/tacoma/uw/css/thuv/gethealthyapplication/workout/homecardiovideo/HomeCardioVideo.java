package edu.tacoma.uw.css.thuv.gethealthyapplication.workout.homecardiovideo;

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
 * @version May 31, 2018
 */
public class HomeCardioVideo implements Serializable{


    /** Public static field for this class */
    public static final String TITLE = "title";
    public static final String URL = "url";

    /** Private field for this class */
    private String title;
    private String url;


    /**
     * Simple constructor for the class
     *
     * @param title the tile of the video
     * @param url the url of the video
     */
    public HomeCardioVideo(String title, String url) {
        this.title = title;
        this.url = url;
    }


    /**
     * Retrieve data from the server and parse that to list of videos
     *
     * @param courseJSON JSON value that needs to be parsed and store in this class
     * @return the list contains the HomeCardioVideo items
     * @throws JSONException exception
     */
    public static List<HomeCardioVideo> parseCourseJSON(String courseJSON) throws JSONException {
        List<HomeCardioVideo> homeCardioVideoList = new ArrayList<HomeCardioVideo>();
        if (courseJSON != null) {

            JSONArray arr = new JSONArray(courseJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                HomeCardioVideo homeCardioVideo = new HomeCardioVideo(obj.getString(HomeCardioVideo.TITLE), obj.getString(HomeCardioVideo.URL));
                homeCardioVideoList.add(homeCardioVideo);
            }

        }

        return homeCardioVideoList;
    }


    /** Getters and Setters for this class */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {

        return this.title;
    }
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
