package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

// seat画面

public class SeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        // ヘッダーの設定
        Toolbar areaTB = findViewById(R.id.seat_tb);
        setSupportActionBar(areaTB);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 次へボタンの設定
        Button shareBtn = findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(new SeatActivity.ShareClickListener());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    // 次へボタンを押したときの処理
    class ShareClickListener implements View.OnClickListener {
        public void onClick(View v) {
            ImageView image = findViewById(R.id.image_im);

            BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();

            Uri uri = getmageToShare(bitmap);
            Intent shareIntent = new Intent(Intent.ACTION_VIEW);
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/png");
            startActivity(Intent.createChooser(shareIntent, "Share Via"));
        }
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