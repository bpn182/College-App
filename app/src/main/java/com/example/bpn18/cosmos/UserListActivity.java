package com.example.bpn18.cosmos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bpn18 on 12/6/2017.
 */
public class UserListActivity extends AppCompatActivity {
    LinearLayout container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        databaseHelper = new DatabaseHelper(this);
        container = (LinearLayout)findViewById(R.id.container);
        populateUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateUser();
    }

    public void populateUser(){
        ArrayList<UserInfo> list = databaseHelper.getUserList();
        container.removeAllViews();
        for(final UserInfo info :list){

            View view = LayoutInflater.from(this).inflate(R.layout.list_item_layout,null);
            TextView day,subject1;
            day =(TextView) view.findViewById(R.id.day);
            subject1=(TextView) view.findViewById(R.id.subject1);
            day.setText("Day : "+ info.day);
            subject1.setText("Subject : "+ info.subject1);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserListActivity.this,DetailActivity.class);
                    intent.putExtra("id",info.id);
                    startActivity(intent);
                }
            });

            container.addView(view);
        }
    }
}

