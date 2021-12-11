package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// title画面

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new StartClickListener());
    }

    class StartClickListener implements View.OnClickListener{
        public void onClick(View view) {
            Intent it = new Intent(getApplicationContext(), AreaActivity.class);
            startActivityForResult(it, 1);
        }
    }
}