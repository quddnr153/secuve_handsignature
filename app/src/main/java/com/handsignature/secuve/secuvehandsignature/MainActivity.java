/*
 *
 */

package com.handsignature.secuve.secuvehandsignature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerLaunch = (Button)findViewById(R.id.registerButton);
        registerLaunch.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 서명 등록을 위한 단계
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class); // 두번째 액티비티를 실행하기 위한 인텐트
                startActivity(intent); // 두번째 액티비티를 실행합니다.
            }
        });

        ImageButton setupLaunch = (ImageButton)findViewById(R.id.optionButton);
        setupLaunch.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, SetupActivity.class); // 두번째 액티비티를 실행하기 위한 인텐트
                startActivity(intent); // 두번째 액티비티를 실행합니다.
            }
        });


        Button signLaunch = (Button)findViewById(R.id.signButton);
        signLaunch.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, SetupActivity.class); // 두번째 액티비티를 실행하기 위한 인텐트
                startActivity(intent); // 두번째 액티비티를 실행합니다.
            }
        });
    }
}
