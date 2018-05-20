package edu.tacoma.uw.css.thuv.gethealthyapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the characteristics which define a user in out application.
 *
 * @author Team 11
 * @version May 19, 2018
 */
public class User implements Serializable {

    /*
     * These constants are the same as the JSON names used
     * in the web service.
     */
    private static final String EMAIL  = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String SEX = "sex";

    /** The user's email.*/
    private String mEmail;

    /** The user's password.*/
    private String mPassword;

    /** The user's first name.*/
    private String mFirstName;

    /** The user's last name.*/
    private String mLastName;

    /** The user's height.*/
    private String mHeight;

    /** The user's weight.*/
    private String mWeight;

    /** Specify if the user is a man or woman.*/
    private String mSex;

    /** Initializes the fields with the given parameters.*/
    public User(final String theEmail, final String thePassword,
                final String theFirstName, final String theLastName,
                final String theHeight, final String theWeight,
                final String theSexFemale) {

        mEmail = theEmail;
        mPassword = thePassword;
        mFirstName = theFirstName;
        mLastName = theLastName;
        mHeight = theHeight;
        mWeight = theWeight;
        mSex = theSexFemale;
    }

    /**
     * Extracting useful information from the JSON string.
     *
     * @param theUserJSON The list of users as a JSON code.
     * @return A list of User objects.
     * @throws JSONException
     */
    public static List<User> parseUserJSON(final String theUserJSON)
                                                throws JSONException {

        List<User> userList = new ArrayList<User>();

        if (theUserJSON != null) {
            JSONArray jArray = new JSONArray(theUserJSON);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);
                User user = new User(jObj.getString(User.EMAIL),
                        jObj.getString(User.PASSWORD),
                        jObj.getString(User.FIRST_NAME),
                        jObj.getString(User.LAST_NAME),
                        jObj.getString(User.HEIGHT),
                        jObj.getString(User.WEIGHT),
                        jObj.getString(User.SEX));

                userList.add(user);
            }
        }

        return userList;
    }


    protected String getEmail() {
        return mEmail;
    }

    private String getPassword() {
        return mPassword;
    }

    protected String getFirstName() {
        return mFirstName;
    }

    protected String getLastName() {
        return mLastName;
    }

    protected String getHeight() {
        return mHeight;
    }

    protected String getWeight() {
        return mWeight;
    }

    protected String getSex() {
        return mSex;
    }

    private void setEmail(final String theEmail) {
        mEmail = theEmail;
    }

    private void setPassword(final String thePassword) {
        mPassword = thePassword;
    }

    protected void setFirstName(final String theFirstName) {
        mFirstName = theFirstName;
    }

    protected void setLastName(final String theLastName) {
        mLastName = theLastName;
    }

    protected void setHeight(final String theHeight) {
        mHeight = theHeight;
    }

    protected void setWeight(final String theWeight) {
        mWeight = theWeight;
    }

    protected void setSexFemale(final String theSexFemale) {
        mSex = theSexFemale;
    }
}