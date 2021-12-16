package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

// area画面

public class AreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        // ヘッダーの設定
        Toolbar areaTB = findViewById(R.id.app_tb);
        setSupportActionBar(areaTB);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 次へボタンの設定
        Button nextBtn = findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ラジオボタンの処理
                RadioGroup areaRG = findViewById(R.id.area_rg);
                int check = areaRG.getCheckedRadioButtonId();
                String selectArea = "";
                if (check > 0) {
                    switch (check) {
                        case R.id.r_rbtn:
                            selectArea = "r";
                            break;
                        case R.id.l2_rbtn:
                            selectArea = "l2";
                            break;
                        case R.id.l1_rbtn:
                            selectArea = "l1";
                            break;
                    }
                    Intent it = new Intent(getApplicationContext(), SeatActivity.class);
                    it.putExtra("AREA", selectArea);
                    startActivityForResult(it, 1);
                } else {
                    Toast.makeText(AreaActivity.this, "どれか選択してください", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}