package com.example.sj971.score;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by sj971 on 2018-05-29.
 */

public class StudentItemView extends LinearLayout {

    TextView id;
    TextView name;
    TextView score;
    //Spinner grade;

    public StudentItemView(Context context) {
        super(context);

        init(context);
    }

    public StudentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.student_item, this, true);

        name = (TextView) findViewById(R.id.name);
        score=(TextView)findViewById(R.id.score);
        id=(TextView)findViewById(R.id.ID);
    }

    public void setName(String student_name) {
        name.setText(student_name);
    }

    public void setScore(String student_score) {
        score.setText(student_score);
    }

    public void setID(String ID){
        id.setText(ID);
    }
}