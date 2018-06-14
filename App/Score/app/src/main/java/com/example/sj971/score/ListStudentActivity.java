package com.example.sj971.score;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ListStudentActivity extends AppCompatActivity {

    FirebaseDatabase database,database2;
    DatabaseReference databaseReference, databaseReference2;

    ListView listView5;

    String studentID, studentName;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    String[] value = new String[1000];

    Button delete;

    String student_number;

    int num=0, pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        listView5 = (ListView) findViewById(R.id.listView5);
        delete = (Button) findViewById(R.id.delete);

        items = new ArrayList<String>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("WEBusers/totalstudent/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                while (userList.hasNext()) {
                    DataSnapshot data = userList.next();

                    //교수 아이디 읽고 그 안에 name을 가져와서 출력
                    studentName = data.getKey();

                    studentID = (String)dataSnapshot.child(studentName).child("studentnum").getValue();

                    // adapter.addItem(new ListItem(professorID, professorName));
                    value[num] = " " + studentID + " " + studentName;
                    items.add("" + value[num]);
                    num++;
                }

                adapter = new ArrayAdapter<String>(ListStudentActivity.this,
                        android.R.layout.simple_list_item_single_choice, items);

                // 어댑터 설정
                listView5.setAdapter(adapter);
                listView5.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new ArrayAdapter<String>(ListStudentActivity.this,
                android.R.layout.simple_list_item_single_choice, items);

        // 어댑터 설정
        listView5.setAdapter(adapter);
        listView5.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        //선택된 학생의 정보를 삭제해주는 부분
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pos = listView5.getCheckedItemPosition(); // 현재 선택된 항목의 첨자(위치값) 얻기
                if (pos != ListView.INVALID_POSITION) {      // 선택된 항목이 있으면

                    //디비 값도 삭제해 줘야함
                    String line = (String)items.get(pos);

                    student_number = line.substring(1,9);

                    database2 = FirebaseDatabase.getInstance();
                    database2.getReference("Mobile/users/student/"+student_number).removeValue();
                    databaseReference2=database2.getReference();

                    databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.i("in", student_number);
                            Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();

                            while (userList.hasNext()) {
                                DataSnapshot data = userList.next();
                                Log.i("123rororor", String.valueOf(data));
                                if (data.getKey().equals(student_number)) {
                                    databaseReference2.child(student_number).removeValue();
                                    Log.i("remove", student_number);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    items.remove(pos);                       // items 리스트에서 해당 위치의 요소 제거
                    listView5.clearChoices();                 // 선택 해제
                    adapter.notifyDataSetChanged();
                    // 어답터와 연결된 원본데이터의 값이 변경된을 알려 리스트뷰 목록 갱신
                }
            }
        });

    }
}

