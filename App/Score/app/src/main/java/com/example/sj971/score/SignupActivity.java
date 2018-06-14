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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    FirebaseDatabase database;
    DatabaseReference databaseReference;

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

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Mobile/");

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

                String id_num = id.getText().toString();

                databaseReference.child("users").child(user).child(id_num).child("ID").setValue(id.getText().toString());
                databaseReference.child("users").child(user).child(id_num).child("PW").setValue(pw.getText().toString());
                databaseReference.child("users").child(user).child(id_num).child("NAME").setValue(name.getText().toString());
                databaseReference.child("users").child(user).child(id_num).child("type").setValue(user);

                if(user.equals("student")) {
                    //추가 기능이 없어 임의로 값을 넣어줌
                    databaseReference.child("users").child(user).child(id_num).child("subject").child("2018").child("1학기").child("00000").child("subjectName").setValue("과목1");
                    databaseReference.child("users").child(user).child(id_num).child("subject").child("2018").child("1학기").child("00000").child("subjectNumber").setValue("00000");
                    databaseReference.child("users").child(user).child(id_num).child("subject").child("2018").child("1학기").child("00000").child("subjectScore").setValue("A");
                }

                if(user.equals("professor")) {
                    //추가 기능이 없어 임의로 값을 넣어줌
                    databaseReference.child("users").child(user).child(id.getText().toString()).child("subject").child("2018").child("1학기").child("00001000").setValue("00001000");
                    databaseReference.child("users").child(user).child(id.getText().toString()).child("subject").child("2018").child("1학기").child("00001000").child("subjectName").setValue("과목1");

                    databaseReference.child("subject").child("2018").child("1학기").child("subjectName").child("00001000").child("subject_name").setValue("과목1");
                    databaseReference.child("subject").child("2018").child("1학기").child("subjectName").child("00001000").child("student").child("1").child("name").setValue("홍길동");
                    databaseReference.child("subject").child("2018").child("1학기").child("subjectName").child("00001000").child("student").child("1").child("score").setValue("A+");

                    databaseReference.child("subject").child("2018").child("1학기").child("subjectName").child("00001000").child("student").child("2").child("name").setValue("홍길동");
                    databaseReference.child("subject").child("2018").child("1학기").child("subjectName").child("00001000").child("student").child("2").child("score").setValue("A+");
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "signup success, please login", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}