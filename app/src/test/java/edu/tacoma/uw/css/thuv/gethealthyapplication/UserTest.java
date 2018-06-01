package edu.tacoma.uw.css.thuv.gethealthyapplication;

import org.junit.Test;

import edu.tacoma.uw.css.thuv.gethealthyapplication.model.User;

import static junit.framework.Assert.assertNotNull;

public class UserTest {

    @Test
    public void User_ValidUserInputToConstructor_True() {
        assertNotNull(new User("mabraham@uw.edu", "testing123",
                "Menaka", "Abraham", "65",
                "120", "Female", "30"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidEmailToConstructor_Exception() {
        new User("mabrahamuw.edu", "testing123",
                "Menaka", "Abraham", "65",
                "120", "Female", "30");
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidPasswordToConstructor_Exception() {
        new User("mabraham@uw.edu", "t",
                "Menaka", "Abraham", "65",
                "120", "Female", "30");
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidFirstNameToConstructor_Exception() {
        new User("mabraham@uw.edu", "testing123",
                "", "Abraham", "65",
                "120", "Female", "30");
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidLastNameToConstructor_Exception() {
        new User("mabraham@uw.edu", "testing123",
                "Menaka", "", "65",
                "120", "Female", "30");
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidHeightToConstructor_Exception() {
        new User("mabraham@uw.edu", "testing123",
                "Menaka", "Abraham", "-15",
                "120", "Female", "30");
    }

    @Test (expected = IllegalArgumentException.class)
    public void User_InvalidWeightToConstructor_Exception() {
        new User("mabraham@uw.edu", "testing123",
                "Menaka", "Abraham", "65",
                "", "Female", "30");
    }

}