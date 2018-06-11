package com.example.sj971.score;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InputGradeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView2;
    StudentAdapter adapter;

    int line = 6; //디비 속 row 개수
    String[] student_id = new String[line];
    String[] student_name = new String[line];
    String[] student_grade = new String[line];

    String[] grade = new String[]{"A+", "A", "B+", "B", "C+", "C", "D", "F"};
    TextView list;
    Button storeButton;
    String result;

    String changed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade);

        list=(TextView)findViewById(R.id.listName);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        String number_value = bundle.getString("Number"); //학수번호
        String subject_value = bundle.getString("Subject"); //과목 이름


        storeButton = (Button) findViewById(R.id.store);
        list.setText(number_value+"  "+subject_value);

        student_id[0] = "201835835";
        student_id[1] = "201835845";
        student_id[2] = "201835865";
        student_id[3] = "201835824";
        student_id[4] = "201835837";
        student_id[5] = "201835838";

        student_name[0] = "student1";
        student_name[1] = "student2";
        student_name[2] = "student3";
        student_name[3] = "student4";
        student_name[4] = "student5";
        student_name[5] = "student6";

        student_grade[0] = "";
        student_grade[1] = "";
        student_grade[2] = "";
        student_grade[3] = "";
        student_grade[4] = "";
        student_grade[5] = "";


        //학수번호 Number의 강의를 듣는 학생들 리스트 뽑아서 출력
        listView2 = (ListView) findViewById(R.id.listView2);

        adapter = new StudentAdapter();

        //여기서 디비 값 읽어서 출력하면 됨
        for (int i = 0; i < 4; i++) {
            adapter.addItem(new StudentItem(student_id[i],student_name[i], student_grade[i]));
        }

        listView2.setAdapter(adapter);

        /*
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // listview 갱신
                adapter.notifyDataSetChanged();
            }
        });
*/
        listView2.setOnItemClickListener(this);

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        new AlertDialog.Builder(this).setTitle("성적을 입력해 주세요")
                .setItems(grade, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int num) {

                        switch (num) {
                            case 0:
                                result = "A+";
                                break;

                            case 1:
                                result = "A";
                                break;
                            case 2:
                                result = "B+";
                                break;

                            case 3:
                                result = "B";
                                break;
                            case 4:
                                result = "C+";
                                break;

                            case 5:
                                result = "C";
                                break;
                            case 6:
                                result = "D";
                                break;

                            case 7:
                                result = "F";
                                break;
                        }
                        Toast.makeText(getApplication(), result, Toast.LENGTH_LONG).show();
                        changed=result;
                    }
                }).setNegativeButton("", null).show();
       // Toast.makeText(getApplication(), i + result, Toast.LENGTH_LONG).show();
        student_grade[i]=changed;

        adapter = new StudentAdapter();

        //여기서 디비 값 읽어서 출력하면 됨
        for ( i = 0; i < 4; i++) {
            adapter.addItem(new StudentItem(student_id[i],student_name[i], student_grade[i]));
        }

        listView2.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    private class StudentAdapter extends BaseAdapter {
        ArrayList<StudentItem> items = new ArrayList<StudentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(StudentItem item) {
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

            StudentItemView view = new StudentItemView(getApplicationContext());

            StudentItem item = items.get(position);

            view.setName(item.getStudent_name());
            view.setScore(item.getStudent_score());
            view.setID(item.getStudent_ID());

            return view;
        }
    }
}