package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


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
    }

    public void login(final String theEmail, final String thePassword) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

}