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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SigninFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SigninFragment extends Fragment{

    private final static String TAG = "SigninFragment";


    private final static String USER_LOG_IN_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/login.php?";

    private EditText mEmail;
    private EditText mPassword;
    private Button mSignIn;
    private Button mCreateNewAccount;

    private OnFragmentInteractionListener mListener;

    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Validates the email and password and makes sure it exist
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
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




    public void launchRegister(View v){
        FragmentManager fragmentManager = getFragmentManager();
        RegistrationFragment rf = new RegistrationFragment();
        fragmentManager.beginTransaction().replace(R.id.main_activity, rf, null).commit();
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void logInUser(String uri);
    }
}