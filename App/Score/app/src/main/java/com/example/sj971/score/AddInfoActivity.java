package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class AddInfoActivity extends AppCompatActivity {

    TextView textYear;
    TextView textSemester;

    EditText numberEdit;
    EditText subjectEdit;
    EditText professorEdit;

    Button storeButton;

    FirebaseDatabase database;
    DatabaseReference databaseReference, databaseReference1;

    int Index;
    String idx;

    String professorName,subjectNum,subjectName, year_value, semester_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        storeButton = (Button) findViewById(R.id.store);
        numberEdit = (EditText) findViewById(R.id.subject_number);
        subjectEdit = (EditText) findViewById(R.id.subject_name);
        professorEdit = (EditText) findViewById(R.id.professor_name);

        professorName = (String)professorEdit.getText().toString();
        subjectName = (String)subjectEdit.getText().toString();
        subjectNum = (String)numberEdit.getText().toString();
        Log.i("SUBJECTNUM",""+subjectNum);

        database = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        year_value = bundle.getString("Year"); //학수번호
        semester_value = bundle.getString("Semester"); //과목 이름


        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),""+subjectNum,Toast.LENGTH_SHORT).show();
                //databaseReference = database.getReference("Mobile/subject/" + year_value + "/" + semester_value + "/"+subjectNum);
                databaseReference = database.getReference("Mobile/subject/"+year_value+"/"+semester_value);
                Log.i("HERE", "Mobile/subject/" + year_value + "/" + semester_value + "/"+subjectNum);

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.i("fff", subjectNum+subjectName+professorName);
                        /*
                        databaseReference.child(subjectNum).child("Number").setValue(""+subjectNum);
                        databaseReference.child(subjectNum).child("subjectName").setValue(""+subjectName);
                        databaseReference.child(subjectNum).child("professorNum").setValue(""+professorName);
                        finish();
                        */

                        databaseReference.child(subjectNum).child("Number").setValue(subjectNum);
                        databaseReference.child(subjectNum).child("subjectName").setValue(subjectName);
                        databaseReference.child(subjectNum).child("professorNum").setValue(professorName);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();

                        while (userList.hasNext()) {
                            DataSnapshot data = userList.next();

                            if (data.getKey().equals(number)) {
                                databaseReference1.child(number).child("subject").child(semester).child(number_value).child("id").setValue(subjectNum);
                                databaseReference1.child(number).child("subject").child(semester).child(number_value).child("name").setValue(subjectName);

                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Index = (int) dataSnapshot.getChildrenCount();
                                        idx = String.valueOf(Index + 1);

                                        number_value = (String) numberEdit.getText().toString();
                                        subject_value = (String) subjectEdit.getText().toString();
                                        professor_value = (String) professorEdit.getText().toString();

                                        databaseReference.child(year).child(semester).child(number_value).child("number").setValue(number_value);
                                        databaseReference.child(year).child(semester).child(number_value).child("subject").setValue(subject_value);
                                        databaseReference.child(year).child(semester).child(number_value).child("professor").setValue(professor_value);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"교수정보를 잘 못 입력하셨습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                */

            }
        });

        /*
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */
    }
}