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
    ManageAdapter adapter;

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

    int line=3; //디비 속 row 개수
    String[] number = new String[line];
    String[] subject = new String[line];
    String[] professor = new String[line];

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        textYear = (TextView) findViewById(R.id.year);
        textSemester = (TextView) findViewById(R.id.semester);

        selectButton = (Button) findViewById(R.id.select);

        year_spinner = (Spinner) findViewById(R.id.select_year);
        semester_spinner = (Spinner) findViewById(R.id.select_semester);

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

        listView3 = (ListView) findViewById(R.id.listView3);

        addButton = (Button) findViewById(R.id.addButton);

        adapter = new ManageAdapter();

        database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("users/subject");


        number[0]="20180000";
        subject[0]="소프트웨어 공학";
        professor[0]="홍길동";

        number[1]="20180001";
        subject[1]="컴퓨터 프로그래밍";
        professor[1]="홍길동";

        number[2]="20180002";
        subject[2]="모바일 프로그래밍";
        professor[2]="홍길동";

        for(int i=0; i<3; i++){
            adapter.addItem(new ManageItem(number[i],subject[i],professor[i]));
        }

        /*
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0; i<3; i++){
                    adapter.addItem(new ManageItem(number[i],subject[i],professor[i]));
                }
            }
        });
*/
        listView3.setAdapter(adapter);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                ManageItem item = (ManageItem) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ModifyInfoActivity.class);

                Bundle number_value = new Bundle();
                number_value.putString("Number", item.getManage_Number());
                intent.putExtras(number_value);

                Bundle subject_value = new Bundle();
                subject_value.putString("Subject", item.getManage_Subject());
                intent.putExtras(subject_value);

                Bundle professor_value = new Bundle();
                professor_value.putString("Professor", item.getManage_Professor());
                intent.putExtras(professor_value);

                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ManageAdapter extends BaseAdapter {
        ArrayList<ManageItem> items = new ArrayList<ManageItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ManageItem item) {
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

            ManageItemView view = new ManageItemView(getApplicationContext());

            ManageItem item = items.get(position);

            view.setNumber(item.getManage_Number());
            view.setSubject(item.getManage_Subject());
            view.setProfessor(item.getManage_Professor());

            return view;
        }
    }

}
