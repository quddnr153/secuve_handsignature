package com.handsignature.secuve.secuvehandsignature;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by quddn on 2016-08-12.
 */
public class RegisterActivity extends AppCompatActivity{

    private EditText nameentry;
    private Button login;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registername);

        nameentry = (EditText)findViewById(R.id.urnameEntry);
        login = (Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameentry.getText().toString();
                Intent intent = new Intent(RegisterActivity.this, DrawActivity.class); // DrawActivity로 이동한다.
                intent.putExtra("name", name); // name이라는 이름으로 String name을 전달한다.
                intent.putExtra("plags", "insert");
                startActivity(intent);
                finish(); // 지금 이 activity 종료
            }
        });
    }
}
