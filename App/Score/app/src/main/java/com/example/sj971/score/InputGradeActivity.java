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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class InputGradeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView list;

    ListView listView;
    StudentAdapter adapter;

    int line = 20; //디비 속 row 개수
    String[] student_name = new String[line];
    String[] student_grade = new String[line];

    String[] grade = new String[]{"A+", "A", "B+", "B", "C+", "C", "D", "F"};

    Button storeButton;
    String result;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

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
        String year_value = bundle.getString("Year");
        String semester_value = bundle.getString("Semester");

        list.setText(number_value+"  "+subject_value);

        //학수번호 Number의 강의를 듣는 학생들 리스트 뽑아서 출력
        listView = (ListView) findViewById(R.id.listView2);

        adapter = new StudentAdapter();

        //선택한 과목의 학생 목록 출력
        databaseReference = database.getReference("subject/"+year_value+"/"+semester_value+"/"+number_value+"/student/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                while (userList.hasNext()) {
                    DataSnapshot data = userList.next();

                    //학생의 아이디를 읽어옴
                    //그 후 그 학생의 아이디->subject항목 안에 있는 해당 과목의 점수를 가져와서 보여줌

                    // adapter.addItem(new ScoreItem(subjectName, subjectScore));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(adapter);

        /*
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                // listview 갱신
                adapter.notifyDataSetChanged();
            }

        });
*/
        listView.setOnItemClickListener(this);
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