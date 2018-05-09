package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SigninFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SigninFragment extends Fragment{


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



    public void login(final String theEmail, final String thePassword) {



        Intent i = new Intent(getActivity(), HomeActivity.class);
        startActivity(i);
        getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signin, container, false);

        mEmail = (EditText) getActivity().findViewById(R.id.email);
        mPassword = (EditText) getActivity().findViewById(R.id.password);
        mSignIn = (Button) getActivity().findViewById(R.id.sign_in);

//        mSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = mEmail.getText().toString();
//                String password = mPassword.getText().toString();
//
//                if (TextUtils.isEmpty(email) || !email.contains("@")) {
//                    Toast.makeText(v.getContext(), "Enter valid email address",
//                            Toast.LENGTH_SHORT).show();
//
//                    mEmail.requestFocus();
//                } else if (TextUtils.isEmpty(password) ||
//                        password.length() < 6) {
//
//                    Toast.makeText(v.getContext(), "Enter valid password"
//                                    + " (at leaste 6 characters)",
//                            Toast.LENGTH_SHORT).show();
//
//                    mEmail.requestFocus();
//                } else {
//                    login(email, password);
//                }
//            }
//        });

        mCreateNewAccount = (Button) v.findViewById(R.id.create_new_account);
        mCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegister(v);
            }
        });

        return v;
    }

    public void launchRegister(View v){
        FragmentManager fragmentManager = getFragmentManager();
        RegistrationFragment rf = new RegistrationFragment();
        fragmentManager.beginTransaction().replace(R.id.main_activity, rf, null).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public void addUser(String url){
        AddUserTask task = new AddUserTask();
        task.execute(new String[]{url.toString()});

        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private class AddUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls){
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls){
                try{
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while((s = buffer.readLine()) != null){
                        response += s;
                    }
                }catch(Exception e){
                    response = "Unable to register. Reason: "
                            + e.getMessage();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if(status.equals("success")){
                    Toast.makeText(getActivity().getApplicationContext(), "Successfully Registered!",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Failed to register",
                            Toast.LENGTH_LONG).show();
                }
            }catch(JSONException e){
                Toast.makeText(getActivity().getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
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
        void onFragmentInteraction(Uri uri);
    }

}
