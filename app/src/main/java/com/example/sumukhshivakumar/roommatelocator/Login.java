package com.example.sumukhshivakumar.roommatelocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etLoginUsername = (EditText) findViewById(R.id.etLoginUsername);
        final EditText etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        Button mainBackButton = (Button) findViewById(R.id.login_to_main);
        mainBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(Login.this, MainActivity.class);
                startActivity(toHome);

            }
        });
    }
}
