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

    /* 1 pound is approximately to 0.45 killograms.*/
    private static final double CONVERSION_RATIO_LB_TO_KG = 1 / 2.2;

    /* 1 inch is equal to 2.54 centimeters.*/
    private static final double CONVERSION_RATIO_IN_TO_CM = 2.54 / 1;

    /*
     * BMR is the Basal Metabolic Rate which is an equation which
     * is used to calculate the user's calories value.
     */
    private static final int BMR_FIRST_COEFFICIENT = 10;
    private static final Double BMR_SECOND_COEFFICIENT = 6.25;
    private static final int BMR_THIRD_COEFFICIENT = 5;


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
    private static final String AGE = "age";

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

    private String mAge;

    /** Initializes the fields with the given parameters.*/
    public User(final String theEmail, final String thePassword,
                final String theFirstName, final String theLastName,
                final String theHeight, final String theWeight,
                final String theSex, final String theAge) {

        mEmail = theEmail;
        mPassword = thePassword;
        mFirstName = theFirstName;
        mLastName = theLastName;
        mHeight = theHeight;
        mWeight = theWeight;
        mSex = theSex;
        mAge = theAge;
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
                        jObj.getString(User.SEX),
                        jObj.getString(User.AGE));

                userList.add(user);
            }
        }

        return userList;
    }


    /**
     *
     *
     * @return
     */
    private double currentBMI() {
        double result = 0;

        double weight = Double.parseDouble(mWeight);
        double height = Double.parseDouble(mHeight);

        /*
         * If the user is not specified to be a female, then we
         * assume that the user is male.
         */
        boolean isFemale = mSex.equalsIgnoreCase("f");


        result = BMR_FIRST_COEFFICIENT * weight * CONVERSION_RATIO_LB_TO_KG
                + BMR_SECOND_COEFFICIENT * height * CONVERSION_RATIO_IN_TO_CM
                - (BMR_THIRD_COEFFICIENT );


        return result;
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