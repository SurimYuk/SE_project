package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    ListView listView3;
    ManageAdapter adapter;

    int line = 4; //디비 속 row 개수
    String[] number = new String[line];
    String[] subject = new String[line];
    String[] professor = new String[line];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        listView3 = (ListView) findViewById(R.id.listView3);

        adapter = new ManageAdapter();

        number[0] = "201515";
        number[1] = "201516";
        number[2] = "201517";
        number[3] = "201518";

        subject[0] = "소프트웨어 공학";
        subject[1] = "컴퓨터 그래픽스";
        subject[2] = "모바일 프로그래밍";
        subject[3] = "경영학원론";

        professor[0] = "정옥란";
        professor[1] = "최진우";
        professor[2] = "이주형";
        professor[3] = "김학진";


        //여기서 디비 값 읽어서 출력하면 됨
        for (int i = 0; i < 4; i++) {
            adapter.addItem(new ManageItem(number[i], subject[i],professor[i]));
        }

        listView3.setAdapter(adapter);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                ManageItem item = (ManageItem) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ModifyInfoActivity.class);

                Bundle number_value = new Bundle();
                number_value.putString("Number", item.getManage_Number());
                intent.putExtras(number_value);

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
