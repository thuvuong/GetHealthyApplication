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

    /* 1 pound is approximately to 0.45 kilograms.*/
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
    private static final String PASSWORD = "pwd";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
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
    public static User parseUserJSON(final String theUserJSON)
                                                throws JSONException {

        User current = null;

        if (theUserJSON != null) {
            JSONArray jArray = new JSONArray(theUserJSON);

            JSONObject jObj = jArray.getJSONObject(0);
            current = new User(jObj.getString(User.EMAIL),
                    jObj.getString(User.PASSWORD),
                    jObj.getString(User.FIRST_NAME),
                    jObj.getString(User.LAST_NAME),
                    jObj.getString(User.HEIGHT),
                    jObj.getString(User.WEIGHT),
                    jObj.getString(User.SEX),
                    jObj.getString(User.AGE));
        }

        return current;
    }

    /**
     * Calculates the user's BMI (Body Mass Index).
     *
     * @return The user's current BMI in pounds per inches squared.
     */
    public String getCurrentBMI() {
        double result = 0;

        double weight = Double.parseDouble(mWeight);
        double height = Double.parseDouble(mHeight);

        result = weight / Math.pow(height, 2) * BMI_EQUATION_CONSTANT;

        result = (double) Math.round(result);

        String stringResult = "" + result + " lb/(in)^2";

        return stringResult;
    }

    public String getHealthyBMIRange() {
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
    public String getCurrentCaloriesIntake() {
        return "" + caloriesCalculator() + " Calories/day";
    }

    public String GetCaloriesToConsumeToLoseWeight() {
        double result = caloriesCalculator() *
                PERCENTAGE_CALORIES_TO_CONSUME_TO_LOSE_WEIGHT;

        result = (double) Math.round(result);

        String stringResult = "" + result + " Calories/day";

        return stringResult;
    }

    private double caloriesCalculator() {
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

        result = (double) Math.round(result);

        return result;
    }

    /**
     * Calculates the expected amount of a user should consume.
     *
     * @return The amount of water a user should consume in ounces.
     */
    public String getExpectedWaterConsumption() {
        int result = (int) Math.ceil(Double.parseDouble(mWeight) / 2);
        String stringResult = "" + result + " oz";

        return stringResult;
    }


    /* Getters and Setters below.*/

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getSex() {
        return mSex;
    }

    public String getAge() {
        return mAge;
    }

    private void setEmail(final String email) {
        mEmail = email;
    }

    private void setPassword(final String password) {
        mPassword = password;
    }

    protected void setFirstName(final String firstName) {
        mFirstName = firstName;
    }

    protected void setLastName(final String lastName) {
        mLastName = lastName;
    }

    protected void setHeight(final String height) {
        mHeight = height;
    }

    protected void setWeight(final String weight) {
        mWeight = weight;
    }

    protected void setSex(final String sex) {
        mSex = sex;
    }

    protected void setAge(String age) {
        mAge = age;
    }
}