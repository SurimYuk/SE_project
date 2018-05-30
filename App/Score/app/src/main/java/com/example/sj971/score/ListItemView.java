package com.example.sj971.score;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sj971 on 2018-05-30.
 */

public class ListItemView extends LinearLayout {

    TextView number;
    TextView name;

    public ListItemView(Context context) {
        super(context);

        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);

        number = (TextView) findViewById(R.id.number);
        name = (TextView) findViewById(R.id.name);
    }

    public void setNumber(String number_value) {
        number.setText(number_value);
    }

    public void setName(String name_value) {
        name.setText(name_value);
    }

}