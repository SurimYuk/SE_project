package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class ManagerActivity extends AppCompatActivity {

    ListView listView3;
    //ManageAdapter adapter;

    TextView textYear;
    TextView textSemester;

    Button selectButton;
    Spinner year_spinner;
    Spinner semester_spinner;

    String[] year_value = {"2015", "2016", "2017", "2018"};
    String[] semester_value = {"spring", "summer", "fall", "winter"};

    String year;
    String semester;

    Button addButton;

    int line = 4; //디비 속 row 개수
    String[] number = new String[line];
    String[] subject = new String[line];
    String[] professor = new String[line];

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    String path, courseID, courseProfessor, courseName;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    //String[] value = new String[1000];

    Button delete, add;

    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        textYear = (TextView) findViewById(R.id.year);
        textSemester = (TextView) findViewById(R.id.semester);

        selectButton = (Button) findViewById(R.id.select);
        add = (Button) findViewById(R.id.add);
        //addStudent = (Button) findViewById(R.id.studentadd);
        delete= (Button) findViewById(R.id.delete);

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

        selectButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                if(semester.equals("1학기") && year.equals("2018")){
                    for(int i=0; i<line; i++){
                        items.add("" + number[i]+" "+subject[i]+" "+professor[i]);
                        num++;
                    }
                }

                adapter = new ArrayAdapter<String>(ManagerActivity.this,
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
                    //items.get(i);

                    items.remove(pos);                       // items 리스트에서 해당 위치의 요소 제거
                    listView3.clearChoices();                 // 선택 해제
                    adapter.notifyDataSetChanged();
                    // 어답터와 연결된 원본데이터의 값이 변경된을 알려 리스트뷰 목록 갱신
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AddInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Year", year);
                bundle.putString("Semester", semester);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

}
