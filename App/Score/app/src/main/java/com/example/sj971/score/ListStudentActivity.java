package com.example.sj971.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListStudentActivity extends AppCompatActivity {

    int line=4; //디비 속 row 개수
    String[] number = new String[line];
    String[] professor = new String[line];

    StudentAdapter adapter;
    ListView listView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        listView5 = (ListView) findViewById(R.id.listView5);

        adapter = new StudentAdapter();

        number[0]="201635801";
        professor[0]="강민선";

        number[1]="201635802";
        professor[1]="박민지";

        number[2]="201635803";
        professor[2]="홍길동";

        for(int i=0; i<3; i++){
            adapter.addItem(new ListItem(number[i],professor[i]));
        }

        listView5.setAdapter(adapter);
    }

    private class StudentAdapter extends BaseAdapter {
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
