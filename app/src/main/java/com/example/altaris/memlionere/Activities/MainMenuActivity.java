package com.example.altaris.memlionere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.altaris.memlionere.R;

public class MainMenuActivity extends AppCompatActivity {

    Button startPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        startPlay = (Button) findViewById(R.id.startBtn);

        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, PlayActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
