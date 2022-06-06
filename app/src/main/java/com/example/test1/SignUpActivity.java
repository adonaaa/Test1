package com.example.test1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public  class SignUpActivity extends AppCompatActivity {

    private EditText etUsername,etPassword;
    private Utilities utils;
    private FirebaseServices fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            connectComponents();
            Log.i("ggg", "Launch signup successfull");
        }
        catch (Exception ex)
        {
            Log.i("ggg", ex.getMessage());
        }

    }

    private void connectComponents() {
        etUsername = findViewById(R.id.etUsernameSignup);
        etPassword = findViewById(R.id.etPasswordSignup);
        utils = Utilities.getInstance();
        fbs = FirebaseServices.getInstance();

    }

    public void signup(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty())
        {
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!utils.validateEmail(username) || !utils.validatePassword(password))
        {
            Toast.makeText(this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
            return;
        }

        fbs.getAuth().createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "welcome", Toast.LENGTH_SHORT).show();

                        } else {
                            // TODO: what to do if fails
                            Toast.makeText(SignUpActivity.this, "signing up did not succeed ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public static class Utilities {
        private static Utilities instance;

        public Utilities()
        {}

        public static Utilities getInstance()
        {
            if (instance == null)
                instance = new Utilities();

            return instance;
        }

        public boolean validateEmail(String username)
        {
            return true;
        }

        public boolean validatePassword(String password)
        {
            return true;
        }

        public boolean checkTrimEmpty(String text)
        {
            return text.trim().isEmpty();
        }
    }
}
