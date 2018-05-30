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

    TextView list;

    ListView listView2;
    StudentAdapter adapter;

    int line = 20; //디비 속 row 개수
    String[] student_name = new String[line];
    String[] student_grade = new String[line];

    String[] grade = new String[]{"A+", "A", "B+", "B", "C+", "C", "D", "F"};

    Button storeButton;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade);

        list=(TextView)findViewById(R.id.listName);
        storeButton = (Button) findViewById(R.id.store);

        Intent intent = getIntent();

        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        String number_value = bundle.getString("Number");
        String subject_value = bundle.getString("Subject");

        list.setText(number_value+"  "+subject_value);

        student_name[0] = "김서연";
        student_name[1] = "김민준";
        student_name[2] = "박서현";
        student_name[3] = "박민서";
        student_name[4] = "배준서";
        student_name[5] = "신서윤";
        student_name[6] = "모하은";
        student_name[7] = "이예준";
        student_name[8] = "이시우";
        student_name[9] = "장지민";
        student_name[10] = "장지후";
        student_name[11] = "장현우";
        student_name[12]= "정현우";
        student_name[13] = "정지윤";
        student_name[14] = "차수빈";
        student_name[15] = "차지훈";
        student_name[16] = "하서준";
        student_name[17] = "하건우";
        student_name[18] = "황건우";
        student_name[19] = "황하은";

        for(int j=0; j<20; j++)
        {
            if(j/4==0){
                student_grade[j] = "A+";
            }

            else if (j/4==1){
                student_grade[j] = "B+";
            }

            else if (j/4==2){
                student_grade[j] = "B";
            }

            else{
                student_grade[j] = "A";
            }
        }

        student_grade[4] = "C+";
        student_grade[8] = "C+";
        student_grade[10] = "C+";
        student_grade[11] = "C+";
        student_grade[14] = "C+";

        student_grade[3] = "F";
        student_grade[12] = "F";

        //학수번호 Number의 강의를 듣는 학생들 리스트 뽑아서 출력
        listView2 = (ListView) findViewById(R.id.listView2);

        adapter = new StudentAdapter();

        //여기서 디비 값 읽어서 출력하면 됨
        for (int i = 0; i < 20; i++) {
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