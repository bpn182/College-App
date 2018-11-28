package com.example.bpn18.cosmos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class routineFaculty extends AppCompatActivity {
    TextView beit,comp,elex,civil,bba,arch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_routine);

        beit = (TextView)findViewById(R.id.beit);
        comp = (TextView)findViewById(R.id.comp);
        elex = (TextView)findViewById(R.id.elex);
        civil = (TextView)findViewById(R.id.civil);
        bba = (TextView)findViewById(R.id.bba);
        arch = (TextView)findViewById(R.id.arch);

        beit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(routineFaculty.this, TabnFragment.class));

            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(routineFaculty.this, TabnFragment.class));

            }
        });
        elex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(routineFaculty.this, TabnFragment.class));
            }
        });

        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(routineFaculty.this, TabnFragment.class));
            }
        });
        bba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        arch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



}
