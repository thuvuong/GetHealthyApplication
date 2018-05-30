package edu.tacoma.uw.css.thuv.gethealthyapplication.workout.homecardiovideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeCardioVideo implements Serializable{


    public static final String TITLE = "title";
    public static final String URL = "url";

    String title;
    String url;


    /**
     * Simple constructor for the class
     *
     * @param title
     * @param url
     */
    public HomeCardioVideo(String title, String url) {
        this.title = title;
        this.url = url;
    }


    /**
     * Retrieve data from the server and parse that to list of videos
     *
     * @param courseJSON
     * @return
     * @throws JSONException
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
