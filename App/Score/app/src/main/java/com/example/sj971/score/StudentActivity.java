package com.example.sj971.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    // FirebaseDatabase database;
    //DatabaseReference databaseReference;

    TextView textYear;
    TextView textSemester;

    Button selectButton;
    Spinner year_spinner;
    Spinner semester_spinner;

    String[] year_value = {"2014","2015","2016","2017"};
    String[] semester_value={"1학기", "여름학기","2학기","겨울학기"};

    String year;
    String semester;

    private final int DYNAMIC_VIEW_ID = 0x8000;
    private LinearLayout dynamicLayout;

    int line = 4; //디비 속 row 개수
    String[] subject = new String[line];
    String[] score = new String[line];

    ListView listView;
    ScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        textYear=(TextView)findViewById(R.id.year);
        textSemester  =(TextView)findViewById(R.id.semester);

        selectButton = (Button)findViewById(R.id.select);
        year_spinner = (Spinner)findViewById(R.id.select_year);
        semester_spinner = (Spinner)findViewById(R.id.select_semester);

        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("users/"+UserID+"/habits");

        subject[0] = "소프트웨어 공학";
        subject[1] = "컴퓨터 그래픽스";
        subject[2] = "모바일 프로그래밍";
        subject[3] = "경영학원론";

        score[0] = "A+";
        score[1] = "A";
        score[2] = "B+";
        score[3] = "B";

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

        selectButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //여기서 사용자가 선택한 year와 semester에 따라 디비 값 읽도록 설정해주면 됨!

                listView = (ListView) findViewById(R.id.listView);

                adapter = new ScoreAdapter();

                //여기서 디비 값 읽어서 출력하면 됨
                for (int i = 0; i < 4; i++) {
                    adapter.addItem(new ScoreItem(subject[i], score[i]));
                }

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
            view.setSubject(item.getSubject());


            return view;
        }

    }
}