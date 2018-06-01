package edu.tacoma.uw.css.thuv.gethealthyapplication;

import org.junit.Before;
import org.junit.Test;

import edu.tacoma.uw.css.thuv.gethealthyapplication.model.User;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class UserTest {

    private static final int BMI_EQUATION_CONSTANT = 703;
    private static final String BMI_HEALTHY_RANGE = "18.5 - 24.9 lb/(in)^2";
    private static final int BODY_WEIGHT_WATER_CONSUMPTION_DIVISOR = 2;

    private static final int TEMP_USER_HEIGHT = 65;
    private static final int TEMP_USER_WEIGHT = 120;
    private static final int TEMP_USER_AGE = 20;

    private static final String TEMP_USER_FIRST_NAME = "Jane";
    private static final String TEMP_USER_LAST_NAME = "Doe";
    private static final String TEMP_USER_EMAIL = "janedoe@uw.edu";
    private static final String TEMP_USER_PASSWORD = "testing123";
    private static final String TEMP_USER_HEIGHT_STRING = "" + TEMP_USER_HEIGHT;
    private static final String TEMP_USER_WEIGHT_STRING = "" + TEMP_USER_WEIGHT;
    private static final String TEMP_USER_SEX = "Female";
    private static final String TEMP_USER_AGE_STRING = "" + TEMP_USER_AGE;



    private User mTempUser;

    @Before
    public void setup() {
        mTempUser = new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test
    public void User_ValidUserInputToConstructor_True() {
        assertNotNull(mTempUser);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidEmailToConstructor_Exception() {
        new User("janedoe.edu", TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidPasswordToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, "t",
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidFirstNameToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                "", TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidLastNameToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, "", TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidHeightToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, "-15",
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidWeightToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                "0", TEMP_USER_SEX, TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidSexToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, "", TEMP_USER_AGE_STRING);
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidAgeToConstructor_Exception() {
        new User(TEMP_USER_EMAIL, TEMP_USER_PASSWORD,
                TEMP_USER_FIRST_NAME, TEMP_USER_LAST_NAME, TEMP_USER_HEIGHT_STRING,
                TEMP_USER_WEIGHT_STRING, TEMP_USER_SEX, "-8");
    }

    @Test
    public void getCurrentBMI_ReceivingExpectedResult_True() {
        double tempHeight = 65;
        double tempWeight = 120;

        String tempHeightString = "" + tempHeight;
        String tempWeightString = "" + tempWeight;


        String givenBMI = mTempUser.getCurrentBMI();

        double result = tempWeight / Math.pow(tempHeight, 2) * BMI_EQUATION_CONSTANT;

        result = Math.round(result);

        String tempStringResult = "" + result + " lb/(in)^2";

        assertEquals(givenBMI, tempStringResult);
    }

    @Test
    public void getHealthyBMIRange_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getHealthyBMIRange(), BMI_HEALTHY_RANGE);
    }

    @Test
    public void getCurrentCaloriesIntake_ReceivingValidResult_True() {
        assertNotNull(mTempUser.getCurrentCaloriesIntake());
    }

    @Test
    public void getCaloriesToConsumeToLoseWeight_ReceivingValidResult_True() {
        assertNotNull(mTempUser.getCaloriesToConsumeToLoseWeight());
    }

    @Test
    public void getExpectedWaterConsumption_ReceivingExpectedResult_True() {
        int result = (int) Math.ceil(Double.parseDouble(TEMP_USER_WEIGHT_STRING)
                / BODY_WEIGHT_WATER_CONSUMPTION_DIVISOR);

        String stringResult = "" + result + " oz";

        assertEquals(mTempUser.getExpectedWaterConsumption(), stringResult);
    }

    @Test
    public void getEmail_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getEmail(), TEMP_USER_EMAIL);
    }

    @Test
    public void getPassword_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getPassword(), TEMP_USER_PASSWORD);
    }

    @Test
    public void getFirstName_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getFirstName(), TEMP_USER_FIRST_NAME);
    }

    @Test
    public void getLastName_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getLastName(), TEMP_USER_LAST_NAME);
    }

    @Test
    public void getHeight_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getHeight(), TEMP_USER_HEIGHT_STRING);
    }

    @Test
    public void getWeight_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getWeight(), TEMP_USER_WEIGHT_STRING);
    }

    @Test
    public void getSex_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getSex(), TEMP_USER_SEX);
    }

    @Test
    public void getAge_ReceivingExpectedResult_True() {
        assertEquals(mTempUser.getAge(), TEMP_USER_AGE_STRING);
    }

    @Test
    public void setEmail_ReceivingExpectedResult_True() {
        String newTempEmail = "jill@uw.edu";

        mTempUser.setEmail(newTempEmail);

        assertEquals(mTempUser.getEmail(), newTempEmail);
    }

    @Test
    public void setPassword_ReceivingExpectedResult_True() {
        String newTempPassword = "testing456";

        mTempUser.setPassword(newTempPassword);

        assertEquals(mTempUser.getPassword(), newTempPassword);
    }

    @Test
    public void setFirstName_ReceivingExpectedResult_True() {
        String newTempFirstName = "Jill";

        mTempUser.setFirstName(newTempFirstName);

        assertEquals(mTempUser.getFirstName(), newTempFirstName);
    }

    @Test
    public void setLastName_ReceivingExpectedResult_True() {
        String newTempLastName = "Sky";

        mTempUser.setLastName(newTempLastName);

        assertEquals(mTempUser.getLastName(), newTempLastName);
    }

    @Test
    public void setHeight_ReceivingExpectedResult_True() {
        String newTempHeight = "70";

        mTempUser.setHeight(newTempHeight);

        assertEquals(mTempUser.getHeight(), newTempHeight);
    }

    @Test
    public void setWeight_ReceivingExpectedResult_True() {
        String newTempWeight = "130";

        mTempUser.setWeight(newTempWeight);

        assertEquals(mTempUser.getWeight(), newTempWeight);
    }

    @Test
    public void setSex_ReceivingExpectedResult_True() {
        String newTempSex = "Male";

        mTempUser.setSex(newTempSex);

        assertEquals(mTempUser.getSex(), newTempSex);
    }

    @Test
    public void setAge_ReceivingExpectedResult_True() {
        String newTempAge = "45";

        mTempUser.setAge(newTempAge);

        assertEquals(mTempUser.getAge(), newTempAge);
    }
}