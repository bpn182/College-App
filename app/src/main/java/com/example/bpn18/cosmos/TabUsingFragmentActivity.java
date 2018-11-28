package com.example.bpn18.cosmos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TabUsingFragmentActivity extends AppCompatActivity {
    TextView sun,mon,tue,wed,thu,fri;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_using_fragment);
        sun = (TextView) findViewById(R.id.sun);
        mon = (TextView) findViewById(R.id.mon);
        tue = (TextView) findViewById(R.id.tue);
        wed = (TextView) findViewById(R.id.wed);
        thu = (TextView) findViewById(R.id.thu);
        fri = (TextView) findViewById(R.id.fri);

        sun.setOnClickListener(tabclicklistener);
        mon.setOnClickListener(tabclicklistener);
        tue.setOnClickListener(tabclicklistener);
        wed.setOnClickListener(tabclicklistener);
        fri.setOnClickListener(tabclicklistener);
        mon.setOnClickListener(tabclicklistener);

        pager = (ViewPager) findViewById(R.id.container);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


    }
    View.OnClickListener tabclicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.sun) {
                pager.setCurrentItem(0);
                //                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FacultyRoutine()).commit();
            } else if(v.getId() == R.id.mon){
                pager.setCurrentItem(1);
                //                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MondayFragment()).commit();
            }else if(v.getId() == R.id.tue){
                pager.setCurrentItem(2);

            }else if(v.getId() == R.id.wed){
                pager.setCurrentItem(3);

            }else if(v.getId() == R.id.thu){
                pager.setCurrentItem(4);

            }else if(v.getId() == R.id.fri){
                pager.setCurrentItem(5);
            }

        }
    };

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new TopFragment();
            } else if (position == 1) {
                return new BottomFragment();
            } else if (position == 2) {
                return new BottomFragment();
            } else if (position == 3) {
                return new BottomFragment();
            } else if (position == 4) {
                return new BottomFragment();
            }
            return new BottomFragment();
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}



