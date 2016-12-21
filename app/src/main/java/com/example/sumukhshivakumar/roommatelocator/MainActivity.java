package com.example.sumukhshivakumar.roommatelocator;

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

        Button firstButton = (Button) findViewById(R.id.button);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstTextView.getText() != "You Clicked Me!") {

                    firstTextView.setText("You Clicked Me!");
                    firstTextView.setAllCaps(true);

                } else {
                    firstTextView.setText("Hello Sumukh!");
                    firstTextView.setAllCaps(false);
                }

            }
        });
    }
}
