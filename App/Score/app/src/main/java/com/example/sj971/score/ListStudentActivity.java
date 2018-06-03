package com.example.sj971.score;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListStudentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    int line=20; //디비 속 row 개수
    String[] number = new String[line];
    String[] student = new String[line];

    StudentAdapter adapter;
    ListView listView5;

    String[] grade = new String[]{"확인"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        listView5 = (ListView) findViewById(R.id.listView5);

        adapter = new StudentAdapter();

        student[0] = "김서연";
        student[1] = "김민준";
        student[2] = "박서현";
        student[3] = "박민서";
        student[4] = "배준서";
        student[5] = "신서윤";
        student[6] = "모하은";
        student[7] = "이예준";
        student[8] = "이시우";
        student[9] = "장지민";
        student[10] = "장지후";
        student[11] = "장현우";
        student[12]= "정현우";
        student[13] = "정지윤";
        student[14] = "차수빈";
        student[15] = "차지훈";
        student[16] = "하서준";
        student[17] = "하건우";
        student[18] = "황건우";
        student[19] = "황하은";

        number[0] = "201635801";
        number[1] = "201635802";
        number[2] = "201635803";
        number[3] = "201635804";
        number[4] = "201635805";
        number[5] = "201635806";
        number[6] = "201635807";
        number[7] = "201635808";
        number[8] = "201635809";
        number[9] = "201635810";
        number[10] = "201635811";
        number[11] = "201635812";
        number[12]= "201635813";
        number[13] = "201635814";
        number[14] = "201635815";
        number[15] = "201635816";
        number[16] = "201635817";
        number[17] = "201635818";
        number[18] = "201635819";
        number[19] = "201635820";


        for(int i=0; i<20; i++){
            adapter.addItem(new ListItem(number[i],student[i]));
        }

        listView5.setAdapter(adapter);
        listView5.setOnItemClickListener(this);
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
