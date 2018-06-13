package com.example.sj971.score;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    int pos = -1;

    String changed, studentID, studentName, studentScore, number_value, subject_value, professor_id, year, semester;

    FirebaseDatabase database;
    DatabaseReference databaseReference, databaseReference2;

    ArrayList<String> items;
    ArrayAdapter<String> adapter2;

    int num = 0;
    String[] value = new String[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grade);

        list = (TextView) findViewById(R.id.listName);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        number_value = bundle.getString("Number"); //학수번호
        subject_value = bundle.getString("Subject"); //과목 이름
        professor_id = bundle.getString("professor_id"); //교수 ID
        year = bundle.getString("Year");
        semester = bundle.getString("Semester");

        storeButton = (Button) findViewById(R.id.store);
        list.setText(number_value + "  " + subject_value);

        //학수번호 Number의 강의를 듣는 학생들 리스트 뽑아서 출력
        listView2 = (ListView) findViewById(R.id.listView2);

        items = new ArrayList<String>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Mobile/subject/" + year + "/" + semester + "/subjectName/" + number_value+"/student/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                while (userList.hasNext()) {
                    DataSnapshot data = userList.next();

                    studentID = data.getKey(); //과목 ID 값

                    studentName = (String) data.child(studentID).child("studentName").getValue(); //과목 이름 가져옴
                    studentScore = (String) data.child(studentID).child("studentScore").getValue(); //과목 성적 가져옴

                    // adapter.addItem(new ListItem(professorID, professorName));
                    value[num] = " " + studentName + " " + studentScore;
                    items.add("" + value[num]);
                    num++;
                }

                adapter2 = new ArrayAdapter<String>(InputGradeActivity.this,
                        android.R.layout.simple_list_item_1, items);

                // 어댑터 설정
                listView2.setAdapter(adapter);
                listView2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView2.setOnItemLongClickListener(new ListViewItemLongClickListener());

        /*
        adapter = new StudentAdapter();

        databaseReference = database.getReference("Mobile/users/professor/" + professor_id + "/subject/" + year + "/" + semester);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                while (userList.hasNext()) {
                    DataSnapshot data = userList.next();

                    subjectID = data.getKey(); //과목 ID 값

                    subjectName = (String) data.child(subjectID).child("subjectName").getValue(); //과목 이름 가져옴
                    subjectScore = (String) data.child(subjectID).child("subjectScore").getValue(); //과목 성적 가져옴

                     adapter.addItem(new StudentItem(subjectID, subjectName, subjectScore));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView2.setAdapter(adapter);

        listView2.setOnItemLongClickListener(new ListViewItemLongClickListener());
        String name = adapter.getID(pos);

        databaseReference = database.getReference("Mobile/users/professor/" + professor_id + "/subject/" + year + "/" + semester+"/"+name);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.child("Score").setValue(result);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView2.setOnItemClickListener(this);

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        */
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
                        changed = result;
                    }
                }).setNegativeButton("", null).show();

        // Toast.makeText(getApplication(), i + result, Toast.LENGTH_LONG).show();
        pos = view.getId();
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

        public String getID(int position) {
            StudentItemView view = new StudentItemView(getApplicationContext());

            StudentItem item = items.get(position);

            return item.getStudent_ID();
        }
    }

    private class ListViewItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


            int pos = listView2.getCheckedItemPosition(); // 현재 선택된 항목의 첨자(위치값) 얻기
            if (pos != ListView.INVALID_POSITION) {      // 선택된 항목이 있으면
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
                alertDlg.setTitle("성적을 입력하세요")
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
                                changed = result;
                            }
                        }).setNegativeButton("", null).show();
                items.set(pos, result);

                String line = (String) items.get(pos);
                final String student_number = line.substring(1, 9);

                databaseReference2 = database.getReference("Mobile/subject/" + year + "/" + semester + "/" + number_value);

                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        databaseReference2.setValue(result);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                adapter2.notifyDataSetChanged();

            }
            return false;
        }
    }
}