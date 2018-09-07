package com.example.android.infinitymusicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button buttonBackToLibrary= (Button) findViewById(R.id.buttonToLibrary);
        buttonBackToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLibrary = new Intent(NowPlaying.this, MainActivity.class);
                startActivity(intentLibrary);
            }
        });
    }

    //choose what the back button on the action bar does
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
