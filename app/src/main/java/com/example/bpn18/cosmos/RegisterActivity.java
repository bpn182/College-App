package com.example.bpn18.cosmos;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText day,faculty,subject1,subject2,subject3,subject4,teacher1,teacher2,teacher3,teacher4,
            room1,room2,room3,room4,starts1,starts2,starts3,starts4,ends1,ends2,ends3,ends4;
    Button register,cancel;
    DatabaseHelper databaseHelper;
int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new DatabaseHelper(this);
        id= getIntent().getIntExtra("id", 0);
        faculty =(EditText)findViewById(R.id.faculty);
        day =(EditText)findViewById(R.id.day);
        subject1=(EditText)findViewById(R.id.subject1);
        subject2=(EditText)findViewById(R.id.subject2);
        subject3=(EditText)findViewById(R.id.subject3);
        subject4=(EditText)findViewById(R.id.subject4);
        teacher1=(EditText)findViewById(R.id.teacher1);
        teacher2=(EditText)findViewById(R.id.teacher2);
        teacher3=(EditText)findViewById(R.id.teacher3);
        teacher4=(EditText)findViewById(R.id.teacher4);
        room1=(EditText)findViewById(R.id.room1);
        room2=(EditText)findViewById(R.id.room2);
        room3=(EditText)findViewById(R.id.room3);
        room4=(EditText)findViewById(R.id.room4);
        starts1=(EditText)findViewById(R.id.starts1);
        starts2=(EditText)findViewById(R.id.starts2);
        starts3=(EditText)findViewById(R.id.starts3);
        starts4=(EditText)findViewById(R.id.starts4);
        ends1=(EditText)findViewById(R.id.ends1);
        ends2=(EditText)findViewById(R.id.ends2);
        ends3=(EditText)findViewById(R.id.ends3);
        ends4=(EditText)findViewById(R.id.ends4);
        register=(Button)findViewById(R.id.register);
        cancel=(Button)findViewById(R.id.cancel);

        if(id!=0){
            UserInfo info = databaseHelper.getUserInfo(id + "");
            day.setText(info.day);
            faculty.setText(info.faculty);
            subject1.setText(info.subject1);
            subject2.setText(info.subject2);
            subject3.setText(info.subject3);
            subject4.setText(info.subject4);
            teacher1.setText(info.teacher1);
            teacher2.setText(info.teacher2);
            teacher3.setText(info.teacher3);
            teacher4.setText(info.teacher4);
            room1.setText(info.room1);
            room2.setText(info.room2);
            room3.setText(info.room3);
            room4.setText(info.room4);
            starts1.setText(info.starts1);
            starts2.setText(info.starts2);
            starts3.setText(info.starts3);
            starts4.setText(info.starts4);
            ends1.setText(info.ends1);
            ends2.setText(info.ends2);
            ends3.setText(info.ends3);
            ends4.setText(info.ends4);

        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,UserListActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facultyValue=faculty.getText().toString();
                String dayValue=day.getText().toString();
                String subject1Value=subject1.getText().toString();
                String subject2Value=subject2.getText().toString();
                String subject3Value=subject3.getText().toString();
                String subject4Value=subject4.getText().toString();
                String teacher1Value=teacher1.getText().toString();
                String teacher2Value=teacher2.getText().toString();
                String teacher3Value=teacher3.getText().toString();
                String teacher4Value=teacher4.getText().toString();
                String room1Value=room1.getText().toString();
                String room2Value=room2.getText().toString();
                String room3Value=room3.getText().toString();
                String room4Value=room4.getText().toString();
                String starts1Value=starts1.getText().toString();
                String starts2Value=starts2.getText().toString();
                String starts3Value=starts3.getText().toString();
                String starts4Value=starts4.getText().toString();
                String ends1Value=ends1.getText().toString();
                String ends2Value=ends2.getText().toString();
                String ends3Value=ends3.getText().toString();
                String ends4Value=ends4.getText().toString();


                ContentValues cv = new ContentValues();
                cv.put("faculty", facultyValue);
                cv.put("day", dayValue);
                cv.put("subject1", subject1Value);
                cv.put("subject2", subject2Value);
                cv.put("subject3", subject3Value);
                cv.put("subject4", subject4Value);
                cv.put("teacher1", teacher1Value);
                cv.put("teacher2", teacher2Value);
                cv.put("teacher3", teacher3Value);
                cv.put("teacher4", teacher4Value);
                cv.put("room1", room1Value);
                cv.put("room2", room2Value);
                cv.put("room3", room3Value);
                cv.put("room4", room4Value);
                cv.put("starts1", starts1Value);
                cv.put("starts2", starts2Value);
                cv.put("starts3", starts3Value);
                cv.put("starts4", starts4Value);
                cv.put("ends1", ends1Value);
                cv.put("ends2", ends2Value);
                cv.put("ends3", ends3Value);
                cv.put("ends4", ends4Value);

                if(id==0) {
                    databaseHelper.insertRoutine(cv);
                    Toast.makeText(RegisterActivity.this, "user inserted", Toast.LENGTH_SHORT).show();
                }else{
                    databaseHelper.updateUser(String.valueOf(id),cv);
                    Toast.makeText(RegisterActivity.this, "user updated", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }
}
