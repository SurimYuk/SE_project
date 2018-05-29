package com.example.sj971.score;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sj971 on 2018-05-29.
 */

public class SubjectItemView extends LinearLayout {

    TextView number;
    TextView subject;

    public SubjectItemView(Context context) {
        super(context);

        init(context);
    }

    public SubjectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.subject_item, this, true);

        number = (TextView) findViewById(R.id.number);
        subject = (TextView) findViewById(R.id.subject);
    }

    public void setSubject(String subject_value) {
        subject.setText(subject_value);
    }

    public void setScore(String number_value) {
        number.setText(number_value);
    }

}