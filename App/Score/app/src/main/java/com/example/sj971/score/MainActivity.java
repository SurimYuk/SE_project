package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText idText;
    EditText pwText;
    Button loginBtn;
    Button signupBtn;


    String inputId, inputPw;

    //private FirebaseDatabase database;
    //private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = (EditText) findViewById(R.id.loginId);
        pwText = (EditText) findViewById(R.id.loginPw);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("users");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputId = idText.getText().toString();
                inputPw = pwText.getText().toString();

                if (inputId.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter your ID", Toast.LENGTH_SHORT).show();
                } else if (inputPw.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();
                }
                //로그인 성공시
                else {
                    Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}