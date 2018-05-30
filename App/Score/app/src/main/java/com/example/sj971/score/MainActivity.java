package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    EditText idText;
    EditText pwText;
    Button loginBtn;
    Button signupBtn;


    String inputId, inputPw;
    String user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = (EditText) findViewById(R.id.loginId);
        pwText = (EditText) findViewById(R.id.loginPw);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

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

                else {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                            while (userList.hasNext()) {
                                DataSnapshot data = userList.next();

                                if (data.getKey().equals(inputId)) {
                                    String userID = (String) data.getKey();
                                    String storedPw = (String) data.child("PW").getValue();

                                    if (storedPw.equals(inputPw)) {
                                        String userName = (String) data.child("NAME").getValue();
                                        Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();

                                        user= (String) data.child("type").getValue();; //디비 연결 전 임시로

                                        if(user.equals("student")){
                                            Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                        else if(user.equals("professor")){
                                            Intent intent = new Intent(getApplicationContext(), ProfessorActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                        //매니저일 때
                                        else{
                                            Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                        finish();

                                        return;
                                    } else {
                                        Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }
                            Toast.makeText(getApplicationContext(), "존재하지 않는 아이디", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
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