package edu.tacoma.uw.css.thuv.gethealthyapplication.food.breakfastvideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BreakfastVideo implements Serializable{
    public static final String TITLE = "title";
    public static final String URL = "url";

    String title;
    String url;



    public BreakfastVideo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public static List<BreakfastVideo> parseCourseJSON(String courseJSON) throws JSONException {
        List<BreakfastVideo> breakfastVideoList = new ArrayList<BreakfastVideo>();
        if (courseJSON != null) {

            JSONArray arr = new JSONArray(courseJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                BreakfastVideo breakfastVideo = new BreakfastVideo(obj.getString(BreakfastVideo.TITLE), obj.getString(BreakfastVideo.URL));
                breakfastVideoList.add(breakfastVideo);
            }

        }

        return breakfastVideoList;
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
