package com.handsignature.secuve.secuvehandsignature;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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
                Toast.makeText(DrawActivity.this, "bitmap 저장하는 기능 아직 없음", Toast.LENGTH_LONG).show();
                // 아마 redo undo에서 쓰는 cache 기능을 쓰면 path도 파악이 가능할 것 같다.
            }
        });

        retry = (Button) findViewById(R.id.button_retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawActivity.this, "retry하는 기능 아직 없음", Toast.LENGTH_LONG).show();
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
}
