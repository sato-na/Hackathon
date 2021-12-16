package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

// seat画面

public class SeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        // 値を受け取る
        Intent it = getIntent();
        final String selectArea = it.getStringExtra("AREA");

        // シート画像とシェア画像の初期値を変える
        ImageView seatNumIV = findViewById(R.id.seat_num_iv);
        int areaId = getResources().getIdentifier("com.example.hackathon:drawable/seat_" + selectArea + "_num", null, null);
        seatNumIV.setImageResource(areaId);
        ImageView shareIV = findViewById(R.id.share_iv);
        int shareId = getResources().getIdentifier("com.example.hackathon:drawable/share_" + selectArea + "0", null, null);
        shareIV.setImageResource(shareId);

        // スピナーを変える
        Spinner seatSp = findViewById(R.id.seat_sp);
        String[] r_list = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        String[] l2_list = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8"};
        String[] l1_list = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8","B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "E1", "E2", "E3", "E4", "E5", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, r_list);
        switch (selectArea) {
            case "l2":
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, l2_list);
                break;
            case "l1":
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, l1_list);
                break;
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatSp.setAdapter(adapter);

        // シェア画像の更新
        seatSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String)seatSp.getSelectedItem();
                int shareId = getResources().getIdentifier("com.example.hackathon:drawable/share_" + selectArea + item.toLowerCase(), null, null);
                shareIV.setImageResource(shareId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // ヘッダーの設定
        Toolbar areaTB = findViewById(R.id.app_tb);
        setSupportActionBar(areaTB);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 次へボタンの設定
        Button shareBtn = findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView image = findViewById(R.id.share_iv);

                BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                Uri uri = getmageToShare(bitmap);
                Intent shareIntent = new Intent(Intent.ACTION_VIEW);
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/png");
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    // bitmapからuriを生成
    private Uri getmageToShare(Bitmap bitmap) {
        File imagefolder = new File(this.getFilesDir(), "images");
        Uri uri = null;
        try {
            imagefolder.mkdirs();
            File file = new File(imagefolder, "shared_image.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(this, "com.example.hackathon.fileprovider", file);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return uri;
    }
}