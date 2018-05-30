package com.example.sj971.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    EditText professorEdit;

    Button storeButton;

    String number_value;
    String subject_value;
    String professor_value;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    int Index;
    String idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        storeButton = (Button) findViewById(R.id.store);
        numberEdit = (EditText) findViewById(R.id.number);
        subjectEdit = (EditText) findViewById(R.id.subject_name);
        professorEdit = (EditText) findViewById(R.id.professor_name);

        textYear=(TextView)findViewById(R.id.year);
        textSemester  =(TextView)findViewById(R.id.semester);

        selectButton = (Button)findViewById(R.id.select);

        year_spinner = (Spinner)findViewById(R.id.select_year);
        semester_spinner = (Spinner)findViewById(R.id.select_semester);

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year_value);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(adapter_year);

        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long id) {
                year = year_value[i];
                textYear.setText(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter_semester = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semester_value);
        adapter_semester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester_spinner.setAdapter(adapter_semester);

        semester_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long id) {
                semester = semester_value[i];
                textSemester.setText(semester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("subject/");

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        });
    }
}