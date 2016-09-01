package com.handsignature.secuve.secuvehandsignature;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Woori on 2016-08-14.
 * 직접적인 서명을 하는 activity
 */

public class DrawActivity extends Activity {
    private Button ok, retry, cancel;
    private SignView sv;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        name = getIntent().getStringExtra("name"); // 앞의 activity에서 extra로 전달된 name이라는 이름의 값을 가져온다.
        Toast.makeText(this, "이름이 넘어온다: "+name, Toast.LENGTH_LONG).show(); // name이 넘어 오는지 넘어오지 않는지 확인하려는 용도

        sv = (SignView)findViewById(R.id.signView);

        ok = (Button) findViewById(R.id.button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(DrawActivity.this, "bitmap 저장하는 기능 아직 없음", Toast.LENGTH_LONG).show();

                // 매 runtime마다 permission을 확인해야 한다.
                try {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (ContextCompat.checkSelfPermission(DrawActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(DrawActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(DrawActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        } else {
                            Toast.makeText(DrawActivity.this, "1", Toast.LENGTH_SHORT).show();
                            SaveSignature(sv, name+"_signature"+System.currentTimeMillis()+".jpg");
                        }
                    } else {
                        Toast.makeText(DrawActivity.this, "2", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    Log.d("???", e.toString());
                }

                sv.invalidate();
            }
        });

        retry = (Button) findViewById(R.id.button_retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawActivity.this, "다시 서명하세요.", Toast.LENGTH_LONG).show();
                sv.mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                // Color.TRANSPARENT or 0
                sv.invalidate();
            }
        });

        cancel = (Button) findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }


    // view를 jpg로 저장하는 애

    public void SaveSignature(View view, String filename) throws IOException {

        if (!view.isEnabled()) Toast.makeText(this, "view 없저", Toast.LENGTH_SHORT).show();
        if (!checkExternalStorage()) Toast.makeText(this, "sd 없저", Toast.LENGTH_SHORT).show();

        String dir;

        String sdcard = Environment.getExternalStorageState();
        if (sdcard.equals(Environment.MEDIA_MOUNTED)) dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        else dir = Environment.getRootDirectory().getAbsolutePath();

        File directory = new File(dir, "woori");
        //  if(!directory.exists()) directory.mkdirs();
        if(!directory.exists()) {
            boolean checker = directory.mkdirs();
            if(checker) Log.d(">>>>>>>>>", "dir만들어짐"+checker);
            else Log.d(">>>>>>>>>", "dir안만들어짐"+checker);
        } else {
            Log.d(">>>>>>>>>", "dir가 원래 있어: "+directory.toString());
        }

        if(directory.isDirectory()) {
            File file = new File(directory.getAbsolutePath(), filename);
            file.createNewFile();
            // Log.d(">>>>>>>>>", "그래서 최종 dir이 뭐냐면: "+directory.getAbsolutePath().toString()+"/////"+file.getAbsolutePath().toString());

            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();

            try {
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("!!!!!", e.toString());
                Toast.makeText(this, "저장에 뭔가 문제가 있다", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // sd card 상태랑 permission 확인하는 애
    private String state;
    boolean checkExternalStorage() {
        state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            Log.d("test", "읽기쓰기모두가능");
            return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Log.d("test", "읽기가능");
            return true;
        } else {
            Log.d("test", "불가능/"+state);
            return false;
        }
    }
}
