package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ModifyInfoActivity extends AppCompatActivity {

    EditText numberEdit;
    Button storeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        storeButton=(Button)findViewById(R.id.store);
        numberEdit = (EditText) findViewById(R.id.number);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        String value = bundle.getString("Number");

        numberEdit.setText(value);
    }
}
