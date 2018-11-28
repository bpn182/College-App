package com.example.bpn18.cosmos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView day, faculty, subject1, subject2, subject3, subject4, teacher1, teacher2, teacher3, teacher4,
            room1, room2, room3, room4, starts1, starts2, starts3, starts4, ends1, ends2, ends3, ends4;
    Button update;
    String id;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        databaseHelper = new DatabaseHelper(this);
        id = getIntent().getStringExtra("id");
        faculty = (EditText) findViewById(R.id.faculty);
        day = (EditText) findViewById(R.id.day);
        subject1 = (TextView) findViewById(R.id.subject1);
        subject2 = (TextView) findViewById(R.id.subject2);
        subject3 = (TextView) findViewById(R.id.subject3);
        subject4 = (TextView) findViewById(R.id.subject4);
        teacher1 = (TextView) findViewById(R.id.teacher1);
        teacher2 = (TextView) findViewById(R.id.teacher2);
        teacher3 = (TextView) findViewById(R.id.teacher3);
        teacher4 = (TextView) findViewById(R.id.teacher4);
        room1 = (TextView) findViewById(R.id.room1);
        room2 = (TextView) findViewById(R.id.room2);
        room3 = (TextView) findViewById(R.id.room3);
        room4 = (TextView) findViewById(R.id.room4);
        starts1 = (TextView) findViewById(R.id.starts1);
        starts2 = (TextView) findViewById(R.id.starts2);
        starts3 = (TextView) findViewById(R.id.starts3);
        starts4 = (TextView) findViewById(R.id.starts4);
        ends1 = (TextView) findViewById(R.id.ends1);
        ends2 = (TextView) findViewById(R.id.ends2);
        ends3 = (TextView) findViewById(R.id.ends3);
        ends4 = (TextView) findViewById(R.id.ends4);
        update = (Button) findViewById(R.id.update);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, RegisterActivity.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);
            }

        });

    }

    public void populateData() {
        UserInfo info = databaseHelper.getUserInfo(id);
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
        starts1.setText(info.starts1+"am - ");
        starts2.setText(info.starts2+"am - ");
        starts3.setText(info.starts3+"am -");
        starts4.setText(info.starts4+"pm -");
        ends1.setText(info.ends1+"am");
        ends2.setText(info.ends2+"am");
        ends3.setText(info.ends3+"pm");
        ends4.setText(info.ends4+"pm");
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void showAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Delete User");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteUser(id);
                finish();
            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        dialog.show();
    }
}
