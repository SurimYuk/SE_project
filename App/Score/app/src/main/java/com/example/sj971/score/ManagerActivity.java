package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    ListView listView3;
    ManageAdapter adapter;

    Button addButton;

    int line = 4; //디비 속 row 개수
    String[] number = new String[line];
    String[] subject = new String[line];
    String[] professor = new String[line];

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        listView3 = (ListView) findViewById(R.id.listView3);

        addButton = (Button)findViewById(R.id.addButton);

        adapter = new ManageAdapter();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("subject");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int num = (int)dataSnapshot.getChildrenCount();
                String idx = String.valueOf(num);

                for(int i=1; i<=num;i++){
                    String Index = String.valueOf(i);
                    String number = (String)dataSnapshot.child(Index).child("number").getValue();
                    String subject = (String)dataSnapshot.child(Index).child("subject").getValue();
                    String professor = (String)dataSnapshot.child(Index).child("professor").getValue();

                    adapter.addItem(new ManageItem(number, subject,professor));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

        addButton.setOnClickListener(new View.OnClickListener(){

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
