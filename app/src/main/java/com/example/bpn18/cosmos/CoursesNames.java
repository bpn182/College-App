package com.example.bpn18.cosmos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CoursesNames extends AppCompatActivity {
    TextView beit,comp,elex,civil,bba,arch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses);

        beit = (TextView)findViewById(R.id.beit);
        comp = (TextView)findViewById(R.id.comp);
        elex = (TextView)findViewById(R.id.elex);
        civil = (TextView)findViewById(R.id.civil);
        bba = (TextView)findViewById(R.id.bba);
        arch = (TextView)findViewById(R.id.arch);

        beit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoursesNames.this, BeitSyllabus.class));

            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoursesNames.this, CompSyllabus.class));

            }
        });
        elex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoursesNames.this, ElexSyllabus.class));
            }
        });

        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoursesNames.this, CivilSyllabus.class));
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
