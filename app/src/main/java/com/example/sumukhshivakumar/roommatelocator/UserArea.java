package com.example.sumukhshivakumar.roommatelocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        final EditText etWelcomeUsername = (EditText) findViewById(R.id.etWelcomeUsername);
        final TextView etWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

    }
}
