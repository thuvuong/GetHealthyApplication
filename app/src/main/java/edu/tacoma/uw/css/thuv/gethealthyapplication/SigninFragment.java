/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

/**
 * The user log in fragment.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class SigninFragment extends Fragment{

    private final static String TAG = "SigninFragment";

    /**
     * This specific url allows us to check if a email and
     * password exists in the database through the php file.
     */
    private final static String USER_LOG_IN_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/login.php?";

    /** Input space for a user's to place their email into.*/
    private EditText mEmail;

    /** Input space for a user's to place their email into.*/
    private EditText mPassword;

    /** Sign in button.*/
    private Button mSignIn;

    /**
     * Create an account button which leads the use to register
     * for a new account.
     */
    private Button mCreateNewAccount;

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

    /** Required empty public constructor.*/
    public SigninFragment() {

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
     * Validates the email and password and makes sure it exist.
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
        View v = inflater.inflate(R.layout.fragment_signin, container, false);

        mEmail = (EditText) v.findViewById(R.id.email);
        mPassword = (EditText) v.findViewById(R.id.password);
        mSignIn = (Button) v.findViewById(R.id.sign_in);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();


                if (TextUtils.isEmpty(email) || !email.contains("@")) {
                    Toast.makeText(v.getContext(), "Enter valid email address",
                            Toast.LENGTH_SHORT).show();

                    mEmail.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(v.getContext(), "Enter a password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String url = buildUserURL(v);
                    mListener.logInUser(url);
                }
            }
        });

        mCreateNewAccount = (Button) v.findViewById(R.id.create_new_account);
        mCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegister(v);
            }
        });

        return v;
    }

    /**
     * Builds a url based on the input values that the user provided.
     *
     * @param v The layout of this page.
     * @return A url which contains the user log in information.
     */
    private String buildUserURL(View v){
        StringBuilder sb = new StringBuilder(USER_LOG_IN_URL);

        try{

            String email = mEmail.getText().toString();
            sb.append("email=");
            sb.append(URLEncoder.encode(email, "UTF-8"));

            String password = mPassword.getText().toString();
            sb.append("&password=");
            sb.append(URLEncoder.encode(password, "UTF-8"));

            Log.i(TAG, sb.toString());
        }catch (Exception e){
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return sb.toString();
    }

    /**
     * Launches the registeration fagment
     *
     * @param v The given layout.
     */
    public void launchRegister(View v){
        FragmentManager fragmentManager = getFragmentManager();
        RegistrationFragment rf = new RegistrationFragment();
        fragmentManager.beginTransaction().replace(R.id.main_activity, rf, null).commit();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void logInUser(String uri);
    }
}