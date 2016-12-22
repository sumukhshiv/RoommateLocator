package com.example.sumukhshivakumar.roommatelocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        Button mainBackButton = (Button) findViewById(R.id.home_to_main);
        mainBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(NewHome.this, MainActivity.class);
                startActivity(toHome);

            }
        });
    }
}
