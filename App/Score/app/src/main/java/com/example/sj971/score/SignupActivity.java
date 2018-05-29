package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText id;
    EditText pw;
    EditText name;
    Button registerBtn;

    RadioGroup user_type;
    RadioButton student;
    RadioButton professor;
    RadioButton manager;

    String user;

    //FirebaseDatabase database;
    //DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id = (EditText)findViewById(R.id.signupId);
        pw = (EditText)findViewById(R.id.signupPw);
        name = (EditText)findViewById(R.id.signupName);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        user_type = (RadioGroup) findViewById(R.id.user_type);
        student = (RadioButton) findViewById(R.id.student);
        professor = (RadioButton) findViewById(R.id.professor);
        manager = (RadioButton) findViewById(R.id.manager);

        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("users");

        user_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.student) {
                    user="student";
                }
                else if(i==R.id.professor){
                    user="professor";
                }
                else {
                    user="manager";
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input값 다 디비에 넣기
                //id/pw 중복조사 넣을건가?
                //이건 기능 다 완성된 다음에 넣어도 괜찮을듯.

                //databaseReference.child(id.getText().toString()).child("ID").setValue(id.getText().toString());
               // databaseReference.child(id.getText().toString()).child("PW").setValue(pw.getText().toString());
                //databaseReference.child(id.getText().toString()).child("NAME").setValue(name.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "signup success, please login", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}