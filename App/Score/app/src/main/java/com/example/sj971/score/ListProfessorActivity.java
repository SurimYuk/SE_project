package com.example.sj971.score;

import android.content.DialogInterface;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ListProfessorActivity extends AppCompatActivity {

    FirebaseDatabase database, database2;
    DatabaseReference databaseReference,databaseReference2;

   // ProfessorAdapter adapter;
    ListView listView4;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    String[] value = new String[1000];

    Button delete;

    int num=0;

    String[] grade = new String[]{"확인"};

    String professorID, professorName, professor_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_professor);

        listView4 = (ListView) findViewById(R.id.listView4);
        delete = (Button) findViewById(R.id.delete);

        //adapter = new ProfessorAdapter();

        items = new ArrayList<String>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("WEBusers/totalprofessor/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                while (userList.hasNext()) {
                    DataSnapshot data = userList.next();

                    //교수 아이디 읽고 그 안에 name을 가져와서 출력
                    professorName = data.getKey();

                    professorID = (String)dataSnapshot.child(professorName).child("professornum").getValue();

                   // adapter.addItem(new ListItem(professorID, professorName));
                    value[num] = " " + professorID + " " + professorName;
                    items.add("" + value[num]);
                    num++;
                }

                adapter = new ArrayAdapter<String>(ListProfessorActivity.this,
                        android.R.layout.simple_list_item_single_choice, items);

                // 어댑터 설정
                listView4.setAdapter(adapter);
                listView4.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int pos = listView4.getCheckedItemPosition(); // 현재 선택된 항목의 첨자(위치값) 얻기
                if (pos != ListView.INVALID_POSITION) {      // 선택된 항목이 있으면

                    //디비 값도 삭제해 줘야함
                    String line = (String)items.get(pos);

                    professor_number = line.substring(1,9);

                    database2 = FirebaseDatabase.getInstance();
                    databaseReference2=database2.getReference("WEBusers/totalprofessor/");


                    databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                            while (userList.hasNext()) {
                                DataSnapshot data = userList.next();
                                if (data.getKey().equals(professor_number)) {
                                    databaseReference2.child(professor_number).removeValue();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    items.remove(pos);                       // items 리스트에서 해당 위치의 요소 제거
                    listView4.clearChoices();                 // 선택 해제
                    adapter.notifyDataSetChanged();
                    // 어답터와 연결된 원본데이터의 값이 변경된을 알려 리스트뷰 목록 갱신
                }
            }
        });
    }
}