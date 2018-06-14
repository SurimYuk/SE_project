package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import javax.security.auth.Subject;

public class ProfessorActivity extends AppCompatActivity {

    ListView listView;
    SubjectAdapter adapter;

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

    String professor_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        database = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        professor_id = bundle.getString("ID"); //로그인 화면에서 넘어온

        textYear=(TextView)findViewById(R.id.year);
        textSemester=(TextView)findViewById(R.id.semester);

        selectButton = (Button) findViewById(R.id.select);
        year_spinner = (Spinner) findViewById(R.id.select_year);
        semester_spinner = (Spinner) findViewById(R.id.select_semester);

        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year_value);
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


        adapter = new SubjectAdapter();

        selectButton=(Button)findViewById(R.id.select);

        selectButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i("-----", year + semester);
                adapter = new SubjectAdapter();

                //사용자가 선택한 연도와 학기에 따른 디비 값을 읽어서 출력
                databaseReference = database.getReference("WEBusers/"+ professor_id + "/" + year + "/" + semester + "/");

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                        while (userList.hasNext()) {
                            DataSnapshot data = userList.next();

                            String subjectID = data.getKey().toString(); //과목 ID 값

                            String subjectName = (String)data.child("name").getValue(); //과목 이름 가져옴

                            adapter.addItem(new SubjectItem(subjectID, subjectName));
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                SubjectItem item = (SubjectItem)adapter.getItem(position);


                Intent intent = new Intent(getApplicationContext(), InputGradeActivity.class);

                Bundle select_number = new Bundle();

                select_number.putString("ID", professor_id);
                select_number.putString("Number", item.getNumber());
                select_number.putString("Subject", item.getSubject());
                select_number.putString("Year", year);
                select_number.putString("Semester", semester);

                intent.putExtras(select_number);

                startActivity(intent);
            }
        });

    }

    class SubjectAdapter extends BaseAdapter {
        ArrayList<SubjectItem> items = new ArrayList<SubjectItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SubjectItem item) {
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

            SubjectItemView view = new SubjectItemView(getApplicationContext());

            SubjectItem item = items.get(position);

            view.setScore(item.getNumber());  //과목 id
            view.setSubject(item.getSubject()); // 과목명

            return view;
        }

    }
}