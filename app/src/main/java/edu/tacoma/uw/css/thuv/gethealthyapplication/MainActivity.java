package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements
            RegistrationFragment.UserAddListener{

    @Override
    public void addUser(String url){
        AddUserTask task = new AddUserTask();
        task.execute(new String[]{url.toString()});

        getSupportFragmentManager().popBackStackImmediate();
    }

    private EditText mEmail;
    private EditText mPassword;
    private Button mSignIn;
    private Button mCreateNewAccount;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mSignIn = (Button) findViewById(R.id.sign_in);

        mSignIn.setOnClickListener(new View.OnClickListener() {
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

        mCreateNewAccount = (Button) findViewById(R.id.create_new_account);
        mCreateNewAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RegistrationFragment rf = new RegistrationFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_activity, rf, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void login(final String theEmail, final String thePassword) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    private class AddUserTask extends AsyncTask<String, Void, String>{

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
                    Toast.makeText(getApplicationContext(), "Successfully Registered!",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to register",
                            Toast.LENGTH_LONG).show();
                }
            }catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}