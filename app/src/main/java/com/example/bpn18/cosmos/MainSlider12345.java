package com.example.bpn18.cosmos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bpn18 on 1/3/2018.
 */

public class MainSlider12345 extends AppCompatActivity {
    ViewPager viewPager;

    LinearLayout glayout,vlayout,elayout,dlayout,nlayout,aulayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainslider12345);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTimerTask(),2000,4000);

        glayout = (LinearLayout)findViewById(R.id.glayout);
        vlayout = (LinearLayout)findViewById(R.id.vlayout);
        elayout = (LinearLayout)findViewById(R.id.elayout);
        dlayout = (LinearLayout)findViewById(R.id.dlayout);
        nlayout = (LinearLayout)findViewById(R.id.nlayout);
        aulayout = (LinearLayout)findViewById(R.id.aulayout);

        glayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSlider12345.this, gallary.class);
                startActivity(intent);
            }
        });


        vlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainSlider12345.this, routineFaculty.class));
            }
        });

        elayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSlider12345.this, events.class));
            }
        });


        dlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSlider12345.this, CoursesNames.class));
            }
        });

        nlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSlider12345.this, notice.class));
            }
        });


        aulayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSlider12345.this, MapsActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        switch (id){
            case R.id.menu1:
                startActivity(new Intent(MainSlider12345.this,loginActivity.class));
                break;
        }
      /*  switch (id){
            case R.id.menu2:
                startActivity(new Intent(MainSlider12345.this,RegisterActivity.class));
                break;
        }*/

        return super.onOptionsItemSelected(item);
    }
    public class myTimerTask extends TimerTask {

        @Override
        public void run() {
            MainSlider12345.this.runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}

