package com.example.sj971.score;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sj971 on 2018-05-29.
 */

public class ScoreItemView extends LinearLayout {

    TextView subject;
    TextView score;

    public ScoreItemView(Context context) {
        super(context);

        init(context);
    }

    public ScoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.score_item, this, true);

        subject = (TextView) findViewById(R.id.subject);
        score = (TextView) findViewById(R.id.score);
    }

    public void setSubject(String subject_value) {
        subject.setText(subject_value);
    }

    public void setScore(String score_value) {
        score.setText(score_value);
    }

}