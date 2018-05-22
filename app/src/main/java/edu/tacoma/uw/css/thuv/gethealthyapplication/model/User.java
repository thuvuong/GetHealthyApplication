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

    private static final int BMI_EQUATION_CONSTANT = 703;

    /*
     * BMR is the Basal Metabolic Rate which is a formula which
     * is used to calculate the user's calories value.
     */
    private static final int BMR_FIRST_COEFFICIENT = 10;
    private static final Double BMR_SECOND_COEFFICIENT = 6.25;
    private static final int BMR_THIRD_COEFFICIENT = 5;
    private static final int BMR_LAST_COEFFICIENT_IF_MALE = 5;
    private static final int BMR_LAST_COEFFICIENT_IF_FEMALE = 161;

    private static final Double PERCENTAGE_CALORIES_TO_CONSUME_TO_LOSE_WEIGHT = 0.8;

    private static final String BMI_HEALTHY_RANGE = "18.5 - 24.9 lb/(in)^2";

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
     * Calculates the user's BMI (Body Mass Index).
     *
     * @return The user's current BMI in pounds per inches squared.
     */
    public String currentBMI() {
        double result = 0;

        double weight = Double.parseDouble(mWeight);
        double height = Double.parseDouble(mHeight);

        result = weight / Math.pow(height, 2) * BMI_EQUATION_CONSTANT;

        String stringResult = "" + result + " lb/(in)^2";

        return stringResult;
    }

    public String healthyBMIRange() {
        return BMI_HEALTHY_RANGE;
    }

    /**
     * Uses Mifflin St Jeor Equation, which is a Basal Metric Rate
     * formula, which is how this method calculates the expected
     * calories the user should consume daily.
     *
     * @return The amount of calories the user should consume daily;
     *              the units are Calories per day.
     */
    public String currentCaloriesIntake() {
        return "" + caloriesCalulator() + " Calories/day";
    }

    public String caloriesToConsumeToLoseWeight() {
        double result = caloriesCalulator() *
                PERCENTAGE_CALORIES_TO_CONSUME_TO_LOSE_WEIGHT;

        String stringResult = "" + result + " Calories/day";

        return stringResult;
    }

    private double caloriesCalulator() {
        double result = 0;

        double weight = Double.parseDouble(mWeight);
        double height = Double.parseDouble(mHeight);
        int age = Integer.parseInt(mAge);

        /*
         * If the user is not specified to be a female, then we
         * assume that the user is male.
         */
        boolean isFemale = mSex.equalsIgnoreCase("f");


        result = BMR_FIRST_COEFFICIENT * weight * CONVERSION_RATIO_LB_TO_KG
                + BMR_SECOND_COEFFICIENT * height * CONVERSION_RATIO_IN_TO_CM
                - (BMR_THIRD_COEFFICIENT * age);

        if (isFemale) {
            result -= BMR_LAST_COEFFICIENT_IF_FEMALE;
        } else {
            result += BMR_LAST_COEFFICIENT_IF_MALE;
        }

        return result;
    }

    /**
     * Calculates the expected amount of a user should consume.
     *
     * @return The amount of water a user should consume in ounces.
     */
    public String expectedWaterConsumption() {
        int result = (int) Math.ceil(Double.parseDouble(mWeight) / 2);
        String stringResult = "" + result + " oz";

        return stringResult;
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