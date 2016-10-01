package com.handsignature.secuve.secuvehandsignature;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
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

/**
 * Created by Woori on 2016-09-22.
 */

public class SignActivity extends Activity{
    private Button ok, retry, cancel;
    private SignView sv;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        name = "Test";
        sv = (SignView) findViewById(R.id.signView);

        ok = (Button) findViewById(R.id.button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (ContextCompat.checkSelfPermission(SignActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(SignActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(SignActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        } else {
                            Toast.makeText(SignActivity.this, "1", Toast.LENGTH_SHORT).show();
                            String filename = name + "_signature" + System.currentTimeMillis() + ".jpg";
                            String filename1 = name + "_signature2" + System.currentTimeMillis() + ".jpg";
                            SaveSignature(sv, filename, filename1);

                            Intent intent = new Intent(SignActivity.this, MainActivity.class); // MainActivity 이동한다.
                            startActivity(intent);
                            finish(); // 지금 이 activity 종료

                        }
                    } else {
                        Toast.makeText(SignActivity.this, "2", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    Log.d("???", e.toString());
                }

                sv.invalidate();
            }
        });
    }

    public void SaveSignature(View view, String filename, String filename1) throws IOException {

        if (!view.isEnabled()) Toast.makeText(this, "view 없저", Toast.LENGTH_SHORT).show();
        //if (!checkExternalStorage()) Toast.makeText(this, "sd 없저", Toast.LENGTH_SHORT).show();

        String dir;

        String sdcard = Environment.getExternalStorageState();
        if (sdcard.equals(Environment.MEDIA_MOUNTED))
            dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        else dir = Environment.getRootDirectory().getAbsolutePath();

        File directory = new File(dir, "woori");
        //  if(!directory.exists()) directory.mkdirs();
        if (!directory.exists()) {
            boolean checker = directory.mkdirs();
            if (checker) Log.d(">>>>>>>>>", "dir만들어짐" + checker);
            else Log.d(">>>>>>>>>", "dir안만들어짐" + checker);
        } else {
            Log.d(">>>>>>>>>", "dir가 원래 있어: " + directory.toString());
        }

        if (directory.isDirectory()) {
            File file = new File(directory.getAbsolutePath(), filename);
            file.createNewFile();
            // Log.d(">>>>>>>>>", "그래서 최종 dir이 뭐냐면: "+directory.getAbsolutePath().toString()+"/////"+file.getAbsolutePath().toString());

            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();

            // 세선화 전 저장
            File file1 = new File(directory.getAbsolutePath(), filename1);
            file1.createNewFile();
            try {
                FileOutputStream fos = new FileOutputStream(file1);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(this, "세선화 전 저장 성공", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("!!!!!", e.toString());
                Toast.makeText(this, "세선화 전 저장에 뭔가 문제가 있다", Toast.LENGTH_SHORT).show();
            }

            // 세선화 작업
            ZhangSuen zs = new ZhangSuen(bitmap);
            zs.thinImage();
            bitmap = zs.getBitmap();

            Uri imageUri = Uri.fromFile(file);

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
        sv.mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        // Color.TRANSPARENT or 0
        sv.invalidate();
    }

    public void SaveToText(String filename) {

    }
}
