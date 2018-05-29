package com.example.sj971.score;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by sj971 on 2018-05-30.
 */

public class ManageItemView extends LinearLayout {

    TextView number;
    TextView subject;
    TextView professor;

    public ManageItemView(Context context) {
        super(context);

        init(context);
    }

    public ManageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.manage_item, this, true);

        number = (TextView) findViewById(R.id.number);
        subject = (TextView) findViewById(R.id.subject);
        professor = (TextView) findViewById(R.id.professor);
    }

    public void setNumber(String num) {
        number.setText(num);
    }

    public void setSubject(String subjectName) {
        subject.setText(subjectName);
    }

    public void setProfessor(String professorName){
        professor.setText(professorName);
    }
}

