package com.example.sumukhshivakumar.roommatelocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmailLogin;
    private EditText editTextPasswordLogin;
    private Button buttonSignIn;
    private TextView textViewRegister;
    private Button buttonLoginToMain;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmailLogin = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextPasswordLogin = (EditText) findViewById(R.id.editTextPasswordLogin);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        buttonLoginToMain = (Button) findViewById(R.id.login_to_main);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = firebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null) {
//            finish();
//            startActivity(new Intent(Login.this, UserArea.class));
//        }

        buttonSignIn.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
        buttonLoginToMain.setOnClickListener(this);

//        final EditText etLoginUsername = (EditText) findViewById(R.id.etLoginUsername);
//        final EditText etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
//        final Button bLogin = (Button) findViewById(R.id.bLogin);
//
//        Button mainBackButton = (Button) findViewById(R.id.login_to_main);
//        mainBackButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent toHome = new Intent(Login.this, MainActivity.class);
//                startActivity(toHome);
//
//            }
//        });
    }

    public void userLogin() {
        String email = editTextEmailLogin.getText().toString().trim();
        String password = editTextPasswordLogin.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Logging in User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Login.this, UserArea.class));
                        } else if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Email/Password combination is not correct. Please Try Again.", Toast.LENGTH_LONG).show();
                        }



                    }
                });

    }


    @Override
    public void onClick(View view) {
        if (view == buttonSignIn) {
            userLogin();
        }

        else if (view == buttonLoginToMain) {
            Intent toHome = new Intent(this, MainActivity.class);
                startActivity(toHome);

        }

        else if (view == textViewRegister) {
            Intent toHome = new Intent(Login.this, NewHome.class);
            startActivity(toHome);

        }
    }
}
