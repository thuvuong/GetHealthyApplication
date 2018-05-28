package edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodVideo implements Serializable{
    public static final String CATEGORY = "category";
    public static final String TITLE = "title";
    public static final String URL = "url";

    String title;
    String url;
    String category;



    public FoodVideo(String title, String url, String category) {
        this.title = title;
        this.url = url;
        this.category = category;
    }

    public static List<FoodVideo> parseCourseJSON(String courseJSON) throws JSONException {
        List<FoodVideo> foodVideoList = new ArrayList<FoodVideo>();
        if (courseJSON != null) {

            JSONArray arr = new JSONArray(courseJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                FoodVideo foodVideo = new FoodVideo(obj.getString(FoodVideo.TITLE), obj.getString(FoodVideo.URL),
                        obj.getString(FoodVideo.CATEGORY));
                foodVideoList.add(foodVideo);
            }

        }

        return foodVideoList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
