package edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Class define each component of a video
 *
 * @author Team 11
 * @version May 31/2018
 */
public class FoodVideo implements Serializable {
    public static final String CATEGORY = "category";
    public static final String TITLE = "title";
    public static final String URL = "url";

    private String mTitle;
    private String mUrl;
    private String mCategory;


    /**
     * Initializes the fields.
     *
     * @param title The title of the workout.
     * @param url The URL video link
     * @param category the category for diffent meal: breakfast, lunch, or dinner
     */
    public FoodVideo(String title, String url, String category) {
        this.mTitle = title;
        this.mUrl = url;
        this.mCategory = category;
    }

    /**
     * Method return the list of videos
     *
     * @param videoJSON a JSON String object
     */
    public static List<FoodVideo> parseCourseJSON(String videoJSON) throws JSONException {
        List<FoodVideo> foodVideoList = new ArrayList<FoodVideo>();
        if (videoJSON != null) {

            JSONArray arr = new JSONArray(videoJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                FoodVideo foodVideo = new FoodVideo(obj.getString(FoodVideo.TITLE), obj.getString(FoodVideo.URL),
                        obj.getString(FoodVideo.CATEGORY));
                foodVideoList.add(foodVideo);
            }

        }

        return foodVideoList;
    }

    /**
     * Initializes the fields.
     *
     * @param category the category for diffent meal: breakfast, lunch, or dinner
     * @param list the list of food videos
     */
    public static List<FoodVideo> getVideosByCategory(String category, List<FoodVideo> list) {
        List<FoodVideo> customList = new ArrayList<FoodVideo>();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getCategory().equals(category)) {
                customList.add(list.get(i));
            }
        }
        return customList;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getTitle() {

        return this.mTitle;
    }
    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
