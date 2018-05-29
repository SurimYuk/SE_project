package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfessorActivity extends AppCompatActivity {

    ListView listView;
    SubjectAdapter adapter;

    int line = 4; //디비 속 row 개수
    String[] number = new String[line];
    String[] subject = new String[line];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        subject[0] = "소프트웨어 공학";
        subject[1] = "컴퓨터 그래픽스";
        subject[2] = "모바일 프로그래밍";
        subject[3] = "경영학원론";

        number[0] = "201524";
        number[1] = "201525";
        number[2] = "201526";
        number[3] = "201527";

        listView = (ListView) findViewById(R.id.listView);

        adapter = new SubjectAdapter();

        //여기서 디비 값 읽어서 출력하면 됨
        for (int i = 0; i < 4; i++) {
            adapter.addItem(new SubjectItem(number[i], subject[i]));
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                SubjectItem item = (SubjectItem) adapter.getItem(position);


                Intent intent = new Intent(getApplicationContext(), InputGradeActivity.class);

                Bundle select_number = new Bundle();
                select_number.putString("Number", item.getNumber());
                intent.putExtras(select_number);

                startActivity(intent);
            }
        });
    }

    class SubjectAdapter extends BaseAdapter {
        ArrayList<SubjectItem> items = new ArrayList<SubjectItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SubjectItem item) {
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

            SubjectItemView view = new SubjectItemView(getApplicationContext());

            SubjectItem item = items.get(position);

            view.setScore(item.getNumber());
            view.setSubject(item.getSubject());

            return view;
        }

    }
}
