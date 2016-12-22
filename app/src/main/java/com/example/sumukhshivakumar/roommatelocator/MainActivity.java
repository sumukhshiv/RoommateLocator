package com.example.sumukhshivakumar.roommatelocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView firstTextView = (TextView) findViewById(R.id.firstTextView);

        Button newHomeButton = (Button) findViewById(R.id.new_home_button);
        Button loginButton = (Button) findViewById(R.id.login_button);

        newHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(MainActivity.this, NewHome.class);
                startActivity(toHome);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(MainActivity.this, Login.class);
                startActivity(toHome);
            }
        });



//        firstButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (firstTextView.getText() != "You Clicked Me!") {
//
//                    firstTextView.setText("You Clicked Me!");
//                    firstTextView.setAllCaps(true);
//
//                } else {
//                    firstTextView.setText("Hello Sumukh!");
//                    firstTextView.setAllCaps(false);
//                }
//
//            }
//        });
    }
}
