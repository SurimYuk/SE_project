package com.example.sj971.score;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InputGradeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView2;
    StudentAdapter adapter;

    int line = 4; //디비 속 row 개수
    String[] student_name = new String[line];
    String[] student_grade = new String[line];

    String[] grade = new String[]{"A+", "A", "B+", "B", "C+", "C", "D", "F"};

    Button storeButton;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade);

        storeButton = (Button) findViewById(R.id.store);

        student_name[0] = "이수정";
        student_name[1] = "육수림";
        student_name[2] = "김해하나";
        student_name[3] = "장윤지";

        student_grade[0] = "A+";
        student_grade[1] = "A";
        student_grade[2] = "B+";
        student_grade[3] = "B";

        //학수번호 Number의 강의를 듣는 학생들 리스트 뽑아서 출력
        listView2 = (ListView) findViewById(R.id.listView2);

        adapter = new StudentAdapter();

        //여기서 디비 값 읽어서 출력하면 됨
        for (int i = 0; i < 4; i++) {
            adapter.addItem(new StudentItem(student_name[i], student_grade[i]));
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
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        new AlertDialog.Builder(this).setTitle("성적을 입력해 주세요")
                .setItems(grade, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int num) {

                        switch (num) {
                            case 1:
                                result = "A+";
                                break;

                            case 2:
                                result = "A";
                                break;
                            case 3:
                                result = "B+";
                                break;

                            case 4:
                                result = "B";
                                break;
                            case 5:
                                result = "C+";
                                break;

                            case 6:
                                result = "C";
                                break;
                            case 7:
                                result = "D";
                                break;

                            case 8:
                                result = "F";
                                break;
                        }

                    }
                }).setNegativeButton("", null).show();
        Toast.makeText(getApplication(), i + result, Toast.LENGTH_LONG).show();

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

            return view;
        }
    }
}