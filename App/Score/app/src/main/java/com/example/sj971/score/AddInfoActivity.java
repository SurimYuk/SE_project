package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    Button selectButton;
    Spinner year_spinner;
    Spinner semester_spinner;

    String[] year_value = {"2015","2016","2017","2018"};
    String[] semester_value={"1학기", "여름학기","2학기","겨울학기"};

    String year;
    String semester;

    EditText numberEdit;
    EditText subjectEdit;
    EditText professorEdit, professorNumber;

    Button storeButton, addStudent;

    String number_value;
    String subject_value;
    String professor_value;

    FirebaseDatabase database;
    DatabaseReference databaseReference, databaseReference1;

    int Index;
    String idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        storeButton = (Button) findViewById(R.id.store);
        numberEdit = (EditText) findViewById(R.id.subject_number);
        subjectEdit = (EditText) findViewById(R.id.subject_name);
        //professorEdit = (EditText) findViewById(R.id.professor_name);
        professorNumber = (EditText) findViewById(R.id.professor_number);

        final String number = professorNumber.getText().toString();
        final String subjectNum = subjectEdit.getText().toString();
        final String subjectName = numberEdit.getText().toString();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Mobile/subject/");
        databaseReference1 = database.getReference("Mobile/users/professor");


        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        String year_value = bundle.getString("Year"); //학수번호
        String semester_value = bundle.getString("Semester"); //과목 이름

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(),"저장되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplication(), TestActivity.class);
                startActivity(intent1);
                finish();

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