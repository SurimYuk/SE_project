package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class StudentActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    TextView textYear;
    TextView textSemester;

    Button selectButton;
    Spinner year_spinner;
    Spinner semester_spinner;

    String[] year_value = {"2015", "2016", "2017", "2018"};
    String[] semester_value = {"spring", "summer", "fall", "winter"};

    String year;
    String semester;

    private final int DYNAMIC_VIEW_ID = 0x8000;
    private LinearLayout dynamicLayout;

    String id;

    int line = 4; //디비 속 row 개수
    String[] subject = new String[line];
    String[] score = new String[line];

    String[] subject2 = new String[line];
    String[] score2 = new String[line];

    ListView listView;
    ScoreAdapter adapter;

    String path;
    int subjectNum=0;

    String subjectID;
    String subjectName;
    String subjectScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        textYear = (TextView) findViewById(R.id.year);
        textSemester = (TextView) findViewById(R.id.semester);

        selectButton = (Button) findViewById(R.id.select);
        year_spinner = (Spinner) findViewById(R.id.select_year);
        semester_spinner = (Spinner) findViewById(R.id.select_semester);

        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("ID");

        database = FirebaseDatabase.getInstance();

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year_value);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(adapter_year);

        year_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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

        semester_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long id) {
                semester = semester_value[i];
                textSemester.setText(semester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                adapter = new ScoreAdapter();

                //사용자가 선택한 연도와 학기에 따른 디비 값을 읽어서 출력
                databaseReference = database.getReference("WEBusers/" + id +"/"+year+"/"+semester+"/");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                        while (userList.hasNext()) {
                            DataSnapshot data = userList.next();

                            String subjectName = (String)data.getKey();
                            Log.i("subjectID",subjectID);

                            String score = (String)data.child("score").getValue();
                            Log.i("subjectName, score",subjectName+score);

                            adapter.addItem(new ScoreItem(subjectName, score));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                listView.setAdapter(adapter);
            }
        });

    }


    class ScoreAdapter extends BaseAdapter {
        ArrayList<ScoreItem> items = new ArrayList<ScoreItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ScoreItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            ScoreItemView view = new ScoreItemView(getApplicationContext());

            ScoreItem item = items.get(position);

            view.setSubject(item.getSubject());
            view.setScore(item.getScore());


            return view;
        }

    }
}