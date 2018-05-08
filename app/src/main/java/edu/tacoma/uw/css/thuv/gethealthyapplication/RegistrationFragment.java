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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationFragment.UserAddListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    private final static String USER_ADD_URL
            = "http://tcssandroidthuv.000webhostapp.com/addUser.php?";

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mUsername;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mWeight;
    private EditText mHeight;
    private EditText mSex;

    private UserAddListener mListener;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
                String password = mPassword.getText().toString();

                if (TextUtils.isEmpty(email) || !email.contains("@")) {
                    Toast.makeText(v.getContext(), "Enter valid email address",
                            Toast.LENGTH_SHORT).show();

                    mEmail.requestFocus();
                } else if (TextUtils.isEmpty(password) ||
                        password.length() < 6) {

                    Toast.makeText(v.getContext(), "Enter valid password"
                                    + " (at leaste 6 characters)",
                            Toast.LENGTH_SHORT).show();

                    mEmail.requestFocus();
                } else {
                    login(email, password);
                }
            }
        });

        return v;
    }

    public void login(final String theEmail, final String thePassword) {
        Intent i = new Intent(getActivity(), HomeActivity.class);
        startActivity(i);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String url) {
        if (mListener != null) {
            mListener.addUser(url);
        }
    }

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface UserAddListener {
        void addUser(String url);
    }

    private String buildUserURL(View v){
        StringBuilder sb = new StringBuilder(USER_ADD_URL);

        try{
            String fname = mFirstName.getText().toString();
            sb.append("first name=");
            sb.append(URLEncoder.encode(fname, "UTF-8"));

            String lname = mLastName.getText().toString();
            sb.append("last name=");
            sb.append(URLEncoder.encode(lname, "UTF-8"));

            String username = mUsername.getText().toString();
            sb.append("username=");
            sb.append(URLEncoder.encode(username, "UTF-8"));

            String email = mEmail.getText().toString();
            sb.append("email=");
            sb.append(URLEncoder.encode(email, "UTF-8"));

            String password = mPassword.getText().toString();
            sb.append("password=");
            sb.append(URLEncoder.encode(password, "UTF-8"));

            String height = mHeight.getText().toString();
            sb.append("height=");
            sb.append(URLEncoder.encode(height, "UTF-8"));

            String weight = mWeight.getText().toString();
            sb.append("weight=");
            sb.append(URLEncoder.encode(weight, "UTF-8"));

            String sex = mSex.getText().toString();
            sb.append("sex=");
            sb.append(URLEncoder.encode(sex, "UTF-8"));

            Log.i(TAG, sb.toString());
        }catch (Exception e){
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return sb.toString();
    }
}