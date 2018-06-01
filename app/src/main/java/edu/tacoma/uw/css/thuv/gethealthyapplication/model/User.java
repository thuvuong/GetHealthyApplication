package edu.tacoma.uw.css.thuv.gethealthyapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Defines the characteristics which define a user in out application.
 *
 * @author Team 11
 * @version May 19, 2018
 */
public class User implements Serializable {

    /**
     * Email validation pattern.
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private final static int PASSWORD_LEN = 6;



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

    private static final int MIN_HEIGHT_IN_INCHES = 12;
    private static final int MIN_WEIGHT_STRING_LENGTH = 2;

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

    /**
     * Initializes the fields with the given parameters.
     *
     * @throws IllegalArgumentException If any of the input values are invalid.
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param height
     * @param weight
     * @param sex
     * @param age
     */
    public User(final String email, final String password,
                final String firstName, final String lastName,
                final String height, final String weight,
                final String sex, final String age) {

        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setHeight(height);
        setWeight(weight);
        setSex(sex);
        setAge(age);
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

    /**
     * Sets the User's email address.
     *
     * @throws IllegalArgumentException if provided an invalid email.
     * @param email The given email.
     */
    public void setEmail(final String email) {
        if (isValidEmail(email)) {
            mEmail = email;
        } else {
            throw new IllegalArgumentException("Invalid email provided.");
        }
    }

    /**
     * Sets the User's password.
     *
     * @throws IllegalArgumentException If provided a length less
     *                                  than the specified PASSWORD_LEN.
     * @param password
     */
    public void setPassword(final String password) {
        if (isValidPassword(password)) {
            mPassword = password;
        } else {
            throw new IllegalArgumentException("Invalid password provided.");
        }
    }

    /**
     * Sets the User's first name.
     *
     * @throws IllegalArgumentException If not provided with a string
     *                                  which at least contains one letter.
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        if (firstName != null && firstName.length() >= 1) {
            mFirstName = firstName;
        } else {
            throw new IllegalArgumentException("Invalid first name provided.");
        }
    }

    /**
     * Sets the User's last name.
     *
     * @throws IllegalArgumentException If not provided with a string
     *                                  which at least contains one letter.
     * @param lastName
     */
    public void setLastName(final String lastName) {
        if (lastName != null && lastName.length() >= 1) {
            mLastName = lastName;
        } else {
            throw new IllegalArgumentException("Invalid last name provided.");
        }
    }

    /**
     * Sets the User's height.
     *
     * @throws IllegalArgumentException If not provided with a string
     *                                  which at least contains one letter.
     * @param height
     */
    public void setHeight(final String height) {
        if (height != null && isValidHeight(height)) {
            mHeight = height;
        } else {
            throw new IllegalArgumentException("Invalid height provided.");
        }
    }

    /**
     * Sets the User's weight.
     *
     * @throws IllegalArgumentException If not provided with a string
     *                                  which at least contains MIN_WEIGHT_STRING_LENGTH.
     * @param weight
     */
    public void setWeight(final String weight) {
        if (weight != null && weight.length() >= MIN_WEIGHT_STRING_LENGTH) {
            mWeight = weight;
        } else {
            throw new IllegalArgumentException("Invalid weight provided.");
        }
    }

    /**
     * Sets the User's sex.
     *
     * @throws IllegalArgumentException If not provided with a string
     *                                  which at least contains one letter.
     * @param sex
     */
    public void setSex(final String sex) {
        if (sex != null && sex.length() >= 1) {
            mSex = sex;
        } else {
            throw new IllegalArgumentException("No input provided for sex.");
        }
    }

    /**
     * Sets the User's age.
     *
     * @throws IllegalArgumentException If not provided with a string
     *                                  which at least contains one letter.
     * @param age
     */
    public void setAge(final String age) {
        if (age != null && age.length() >= 1) {
            mAge = age;
        } else {
            throw new IllegalArgumentException("Invalid age provided.");
        }
    }



    /**
     * Validates if the given input is a valid email address.
     *
     * @param email The email to validate.
     * @return True if the input is a valid email else false.
     */
    public static boolean isValidEmail(final String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates if the given password is valid.
     * Valid password must be at last 6 characters long
     * with at least one digit and one symbol.
     *
     * @param password The password to validate.
     * @return True if the input is a valid password else false.
     */
    public static boolean isValidPassword(String password) {
        boolean result = false;
        if (password != null && password.length() >= PASSWORD_LEN) {
            return true;
        }
        return result;
    }

    public boolean isValidHeight(final String height) {
        return Integer.parseInt(height) >= MIN_HEIGHT_IN_INCHES;
    }
}