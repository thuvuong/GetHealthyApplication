/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

import static android.content.ContentValues.TAG;

/**
 * The user registration fragment.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class RegistrationFragment extends Fragment {

    /**
     * This specific url allows us to add a new user information
     * through the php file.
     */
    private final static String USER_ADD_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/addUser.php?";

    /** Input space for the new user's to place their email into.*/
    private EditText mEmail;

    /** Input space for the new user's to place their password into.*/
    private EditText mPassword;

    /** Input space for the new user's to place their first name into.*/
    private EditText mFirstName;

    /** Input space for the new user's to place their last name into.*/
    private EditText mLastName;

    /** Input space for the new user's to place their weight into.*/
    private EditText mWeight;

    /** Input space for the new user's to place their height into.*/
    private EditText mHeight;

    /** Input space for the new user's to place their sex into.*/
    private EditText mSex;

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private UserAddListener mListener;

    /** Required empty public constructor.*/
    public RegistrationFragment() {

    }

    /**
     * Figuring how the fragment should be oriented.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Selecting the layout of this fragment.
     *
     * @param inflater Specifies how to display the fragment.
     * @param container The container where this fragment will reside.
     * @param savedInstanceState The given data from an activity.
     * @return The view of how this fragment will be displayed.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        mFirstName = (EditText) v.findViewById(R.id.text_fname);
        mLastName = (EditText) v.findViewById(R.id.text_lname);

        mEmail = (EditText) v.findViewById(R.id.text_user_email);
        mPassword = (EditText) v.findViewById(R.id.text_password);
        mHeight = (EditText) v.findViewById(R.id.text_height);
        mWeight = (EditText) v.findViewById(R.id.text_weight);
        mSex = (EditText) v.findViewById(R.id.text_sex);

        Button signUpButton = (Button) v.findViewById(R.id.btn_sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pwd = mPassword.getText().toString();
                if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".")) {
                    Toast.makeText(v.getContext(), "Enter valid email address which contains an @ and a dot"
                            , Toast.LENGTH_SHORT)
                            .show();
                    mEmail.requestFocus();
                }

                else if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
                    Toast.makeText(v.getContext(), "Enter valid password (at least 6 characters)"
                            , Toast.LENGTH_SHORT)
                            .show();
                    mPassword.requestFocus();
                }
                else {
                    String url = buildUserURL(v);
                     mListener.addUser(url);
                }
            }
        });

        return v;
    }

    /**
     * The listener notifies the activity about the button being pressed.
     *
     * @param url The specifics website to go to.
     */
    public void onButtonPressed(String url) {
        if (mListener != null) {
            mListener.addUser(url);
        }
    }

    /**
     * Setting up listener when the fragment is first attached
     * to the activity.
     *
     * @param context The new activity where this fragment will be placed.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserAddListener) {
            mListener = (UserAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddUserListener");
        }
    }

    /**
     * Disassociating with the current activity and stopping the
     * listener.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface UserAddListener {
        void addUser(String url);
    }

    /**
     * Builds a url based on the input values that the user provided.
     *
     * @param v The layout of this page.
     * @return A url which contains the new user's private information.
     */
    private String buildUserURL(View v){
        StringBuilder sb = new StringBuilder(USER_ADD_URL);

        try{

            String email = mEmail.getText().toString();
            sb.append("email=");
            sb.append(URLEncoder.encode(email, "UTF-8"));

            String password = mPassword.getText().toString();
            sb.append("&password=");
            sb.append(URLEncoder.encode(password, "UTF-8"));

            String fname = mFirstName.getText().toString();
            sb.append("&firstname=");
            sb.append(URLEncoder.encode(fname, "UTF-8"));

            String lname = mLastName.getText().toString();
            sb.append("&lastname=");
            sb.append(URLEncoder.encode(lname, "UTF-8"));

            String height = mHeight.getText().toString();
            sb.append("&height=");
            sb.append(URLEncoder.encode(height, "UTF-8"));

            String weight = mWeight.getText().toString();
            sb.append("&weight=");
            sb.append(URLEncoder.encode(weight, "UTF-8"));

            String sex = mSex.getText().toString();
            sb.append("&sex=");
            sb.append(URLEncoder.encode(sex, "UTF-8"));


            Log.i(TAG, sb.toString());
        }catch (Exception e){
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return sb.toString();
    }
}