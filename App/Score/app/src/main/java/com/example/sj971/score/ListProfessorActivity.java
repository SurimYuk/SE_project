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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class ListProfessorActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    ProfessorAdapter adapter;
    ListView listView4;

    String[] grade = new String[]{"확인"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_professor);

        listView4 = (ListView) findViewById(R.id.listView4);

        adapter = new ProfessorAdapter();

        databaseReference = database.getReference("users/professor/");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> userList = dataSnapshot.getChildren().iterator();
                while (userList.hasNext()) {
                    DataSnapshot data = userList.next();

                    //교수 아이디 읽고 그 안에 name을 가져와서 출력

                    // adapter.addItem(new ScoreItem(subjectName, subjectScore));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView4.setAdapter(adapter);

        listView4.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        new AlertDialog.Builder(this).setTitle("삭제하시겠습니까?")
                .setItems(grade, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int num) {


                    }
                }).setNegativeButton("", null).show();
    }

    private class ProfessorAdapter extends BaseAdapter{
        ArrayList<ListItem> items = new ArrayList<ListItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ListItem item) {
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

            ListItemView view = new ListItemView(getApplicationContext());

            ListItem item = items.get(position);

            view.setNumber(item.getNumber());
            view.setName(item.getName());

            return view;
        }
    }
}