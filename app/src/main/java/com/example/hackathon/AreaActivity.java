package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

// area画面

public class AreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        // ヘッダーの設定
        Toolbar areaTB = findViewById(R.id.seat_tb);
        setSupportActionBar(areaTB);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 次へボタンの設定
        Button nextBtn = findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new AreaActivity.NextClickListener());
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    class NextClickListener implements View.OnClickListener {
        public void onClick(View v) {
            Intent it = new Intent(getApplicationContext(), SeatActivity.class);
            startActivityForResult(it, 1);
        }
    }
}