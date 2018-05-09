package edu.tacoma.uw.css.thuv.gethealthyapplication.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
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


    private String mEmail;
    private String mPassword;
    private String mFirstName;
    private String mLastName;
    private int mHeight;
    private int mWeight;
    private boolean mSexFemale;

    public User(final String theEmail, final String thePassword,
                final String theFirstName, final String theLastName,
                final int theHeight, final int theWeight,
                final boolean theSexFemale) {

        // The values must be checked before being assigned here.
        mEmail = theEmail;
        mPassword = thePassword;
        mFirstName = theFirstName;
        mLastName = theLastName;
        mHeight = theHeight;
        mWeight = theWeight;
        mSexFemale = theSexFemale;
    }

    /**
     * Extracting useful information from the JSON string.
     *
     * @param theUserJSON
     * @return
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
                        jObj.getInt(User.HEIGHT),
                        jObj.getInt(User.WEIGHT),
                        jObj.getBoolean(User.SEX));

                userList.add(user);
            }
        }

        return userList;
    }












    // *** Getters and setters below. ***

    protected String getEmail() {
        return mEmail;
    }

    protected String getFirstName() {
        return mFirstName;
    }

    protected int getHeight() {
        return mHeight;
    }

    protected boolean isSexFemale() {
        return mSexFemale;
    }

    protected String getLastName() {
        return mLastName;
    }

    private String getPassword() {
        return mPassword;
    }

    protected int getWeight() {
        return mWeight;
    }

    private void setEmail(final String theEmail) {
        mEmail = theEmail;
    }

    protected void setFirstName(final String theFirstName) {
        mFirstName = theFirstName;
    }

    protected void setHeight(final int theHeight) {
        mHeight = theHeight;
    }

    protected void setSexFemale(final boolean theSexFemale) {
        mSexFemale = theSexFemale;
    }

    protected void setLastName(final String theLastName) {
        mLastName = theLastName;
    }

    private void setPassword(final String thePassword) {
        mPassword = thePassword;
    }

    protected void setWeight(final int theWeight) {
        mWeight = theWeight;
    }
}