package com.example.sumukhshivakumar.roommatelocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.CYAN;
import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class NewHome extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegisterUser;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewPasswordStrengthIndiactor;
    private TextView textViewSignin;
    private Button mainBackButton;
    private TextView textViewPasswordGraphic;


    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        buttonRegisterUser = (Button) findViewById(R.id.buttonRegisterUser);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        mainBackButton = (Button) findViewById(R.id.home_to_main);
        buttonRegisterUser.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        mainBackButton.setOnClickListener(this);

        textViewPasswordStrengthIndiactor = (TextView) findViewById(R.id.textViewPasswordStrengthIndicator);
        textViewPasswordGraphic = (TextView) findViewById(R.id.textViewPasswordGraphic);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        editTextPassword.addTextChangedListener(mTextEditorWatcher);

//        afterTextChanged((Editable) editTextPassword);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // When No Password Entered
            textViewPasswordStrengthIndiactor.setText("Not Entered");
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        public void afterTextChanged(Editable s)
        {
            if(s.length()==0) {
                textViewPasswordStrengthIndiactor.setText("Not Entered");
                textViewPasswordStrengthIndiactor.setTextColor(DKGRAY);
                textViewPasswordGraphic.setBackgroundColor(DKGRAY);
                textViewPasswordGraphic.setWidth(0);
                textViewPasswordGraphic.setHeight(12);
            }
            else if(s.length()<6) {
                textViewPasswordStrengthIndiactor.setText("Must be at least 6 digits");
                textViewPasswordStrengthIndiactor.setTextColor(DKGRAY);
                textViewPasswordGraphic.setBackgroundColor(DKGRAY);
                textViewPasswordGraphic.setWidth(50);
                textViewPasswordGraphic.setHeight(12);
            }

            else if(s.length()<8) {
                textViewPasswordStrengthIndiactor.setText("Easy");
//                textViewPasswordStrengthIndiactor.setTextColor(RED);
                textViewPasswordGraphic.setBackgroundColor(RED);
                textViewPasswordGraphic.setWidth(200);
                textViewPasswordGraphic.setHeight(12);
            }

            else if(s.length()<11) {
                textViewPasswordStrengthIndiactor.setText("Easy");
//                textViewPasswordStrengthIndiactor.setTextColor(RED);
                textViewPasswordGraphic.setBackgroundColor(RED);
                textViewPasswordGraphic.setWidth(300);
                textViewPasswordGraphic.setHeight(12);
            }

            else if(s.length()<13) {
                textViewPasswordStrengthIndiactor.setText("Medium");
//                textViewPasswordStrengthIndiactor.setTextColor(YELLOW);
                textViewPasswordGraphic.setBackgroundColor(YELLOW);
                textViewPasswordGraphic.setWidth(400);
                textViewPasswordGraphic.setHeight(12);
            }

            else if(s.length()<15) {
                textViewPasswordStrengthIndiactor.setText("Medium");
//                textViewPasswordStrengthIndiactor.setTextColor(YELLOW);
                textViewPasswordGraphic.setBackgroundColor(YELLOW);
                textViewPasswordGraphic.setWidth(500);
                textViewPasswordGraphic.setHeight(12);
            }

            else if(s.length()<17) {
                textViewPasswordStrengthIndiactor.setText("Strong");
//                textViewPasswordStrengthIndiactor.setTextColor(YELLOW);
                textViewPasswordGraphic.setBackgroundColor(GREEN);
                textViewPasswordGraphic.setWidth(850);
                textViewPasswordGraphic.setHeight(12);

            }

            else {
                textViewPasswordStrengthIndiactor.setText("Strong");
//                textViewPasswordStrengthIndiactor.setTextColor(CYAN);
                textViewPasswordGraphic.setBackgroundColor(GREEN);
                textViewPasswordGraphic.setWidth(1150);
                textViewPasswordGraphic.setHeight(12);
            }


            if(s.length()==20) {
                textViewPasswordStrengthIndiactor.setText("Password Max Length Reached");
                textViewPasswordGraphic.setBackgroundColor(DKGRAY);
            }
            if(s.length()>=20)
                textViewPasswordStrengthIndiactor.setText("Password Max Length Exceeded");
        }
    };


    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {

            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();


//        firebaseAuth.createUserWithEmailAndPassword(email, password);

//        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(NewHome.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(NewHome.this, "Uh oh! Please try again.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (mAuth.signInWithEmailAndPassword(email, password) != null) {
                                Toast.makeText(NewHome.this, "An account with this email already exists.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(NewHome.this, "Authentication Failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        } else if (task.isSuccessful()) {
                            Toast.makeText(NewHome.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }


                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegisterUser) {
            registerUser();
        }

        else if (view == textViewSignin) {
            Intent toLogin = new Intent(NewHome.this, Login.class);
            startActivity(toLogin);

        }

        else if (view == mainBackButton) {
            Intent toHome = new Intent(NewHome.this, MainActivity.class);
            startActivity(toHome);
        }
    }
}
