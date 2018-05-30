package com.example.sj971.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ModifyInfoActivity extends AppCompatActivity {

    EditText numberEdit;
    EditText subjectEdit;
    EditText professorEdit;

    Button storeButton;

    String number_value;
    String subject_value;
    String professor_value;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    int Index;
    String idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        storeButton=(Button)findViewById(R.id.store);
        numberEdit = (EditText) findViewById(R.id.number);
        subjectEdit = (EditText) findViewById(R.id.subject_name);
        professorEdit = (EditText) findViewById(R.id.professor_name);

        Intent intent = getIntent();

        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        number_value = bundle.getString("Number");
        subject_value = bundle.getString("Subject");
        professor_value = bundle.getString("Professor");

        numberEdit.setText(number_value);
        subjectEdit.setText(subject_value);
        professorEdit.setText(professor_value);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("subject");

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Index = (int) dataSnapshot.getChildrenCount();
                        idx = String.valueOf(Index + 1);

                        number_value=(String)numberEdit.getText().toString();
                        subject_value=(String)subjectEdit.getText().toString();
                        professor_value=(String)professorEdit.getText().toString();

                        databaseReference.child(idx).child("number").setValue(number_value);
                        databaseReference.child(idx).child("subject").setValue(subject_value);
                        databaseReference.child(idx).child("professor").setValue(professor_value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
