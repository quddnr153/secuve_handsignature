/*
 *
 */

package com.handsignature.secuve.secuvehandsignature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button launch = (Button)findViewById(R.id.registerButton);
        launch.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class); // 두번째 액티비티를 실행하기 위한 인텐트
                startActivity(intent); // 두번째 액티비티를 실행합니다.
            }
        });
    }
}
