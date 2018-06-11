package com.example.sj971.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ListView listView3;
    //ManageAdapter adapter;

    TextView textYear;
    TextView textSemester;

    Button selectButton;
    Spinner year_spinner;
    Spinner semester_spinner;

    String[] year_value = {"2015", "2016", "2017", "2018"};
    String[] semester_value = {"1학기", "여름학기", "2학기", "겨울학기"};

    String year;
    String semester;

    Button addButton;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    String[] value = new String[1000];

    Button delete, add, addStudent;

    int num = 0;

    int line = 5; //디비 속 row 개수
    String[] number = new String[line];
    String[] subject = new String[line];
    String[] professor = new String[line];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        number[0] = "0939209";
        number[1] = "0939248";
        number[2] = "0939262";
        number[3] = "0939202";
        number[4] = "0000000";

        subject[0] = "소프트웨어 공학";
        subject[1] = "컴퓨터 그래픽스";
        subject[2] = "모바일 프로그래밍";
        subject[3] = "경영학원론";
        subject[4] = "졸업작품";

        professor[0] = "홍길동";
        professor[1] = "홍길동";
        professor[2] = "홍길동";
        professor[3] = "홍길동";
        professor[4] = "홍길동";

        textYear = (TextView) findViewById(R.id.year);
        textSemester = (TextView) findViewById(R.id.semester);

        selectButton = (Button) findViewById(R.id.select);
        add = (Button) findViewById(R.id.add);
        //addStudent = (Button) findViewById(R.id.studentadd);
        delete = (Button) findViewById(R.id.delete);

        year_spinner = (Spinner) findViewById(R.id.select_year);
        semester_spinner = (Spinner) findViewById(R.id.select_semester);

        //addButton = (Button) findViewById(R.id.addButton);

        listView3 = (ListView) findViewById(R.id.listView3);

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

        items = new ArrayList<String>();

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (semester.equals("1학기") && year.equals("2018")) {
                    for (int i = 0; i < line; i++) {
                        items.add("" + number[i] + " " + subject[i] + " " + professor[i]);
                        num++;
                    }
                }
                adapter = new ArrayAdapter<String>(TestActivity.this,
                        android.R.layout.simple_list_item_single_choice, items);

                // 어댑터 설정
                listView3.setAdapter(adapter);
                listView3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int pos = listView3.getCheckedItemPosition(); // 현재 선택된 항목의 첨자(위치값) 얻기
                if (pos != ListView.INVALID_POSITION) {      // 선택된 항목이 있으면

                    //디비 값도 삭제해 줘야함


                    items.remove(pos);                       // items 리스트에서 해당 위치의 요소 제거
                    listView3.clearChoices();                 // 선택 해제
                    adapter.notifyDataSetChanged();
                    // 어답터와 연결된 원본데이터의 값이 변경된을 알려 리스트뷰 목록 갱신
                }
            }
        });
    }
}
