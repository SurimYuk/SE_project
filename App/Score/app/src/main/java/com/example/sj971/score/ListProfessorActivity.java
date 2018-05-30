package com.example.sj971.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListProfessorActivity extends AppCompatActivity {

    int line=4; //디비 속 row 개수
    String[] number = new String[line];
    String[] professor = new String[line];

    ProfessorAdapter adapter;
    ListView listView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_professor);

        listView4 = (ListView) findViewById(R.id.listView4);

        adapter = new ProfessorAdapter();

        number[0]="1009001";
        professor[0]="홍길동";

        number[1]="1009002";
        professor[1]="홍길동";

        number[2]="1009003";
        professor[2]="홍길동";

        for(int i=0; i<3; i++){
            adapter.addItem(new ListItem(number[i],professor[i]));
        }

        listView4.setAdapter(adapter);
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