package com.example.bpn18.cosmos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
// import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.bpn18.cosmos.R.id.fri;

public class TabnFragment extends AppCompatActivity {
    SundayFragment sundayFragment;
    MondayFragment mondayFragment;
    TuesdayFragment tuesdayFragment;
    WednesdayFragment wednesdayFragment;
    ThursdayFragment thursdayFragment;
    FridayFragment fridayFragment;
    ViewPager pager;




    DatabaseHelper databaseHelper;
    ProgressDialog progressDialog;
    AQuery aquery;


    String fetchUrl = "https://sastopustak.000webhostapp.com/selectroutineIT7.php";
    //String fetchUrl="http://192.168.0.102/www/selectroutineIT7.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_n_fragment);
        aquery = new AQuery(this);
        databaseHelper = new DatabaseHelper(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        sundayFragment = new SundayFragment();
        mondayFragment = new MondayFragment();
        tuesdayFragment = new TuesdayFragment();
        wednesdayFragment = new WednesdayFragment();
        thursdayFragment = new ThursdayFragment();
        fridayFragment = new FridayFragment();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final ViewPager pager = (ViewPager) findViewById(R.id.container);

        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);

       // pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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

            }else if(v.getId() == fri){
                pager.setCurrentItem(5);

            }

        }
    };

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;
        public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return new SundayFragment();
            } else if (position == 1) {

                return new MondayFragment();
            } else if (position == 2) {
                return new TuesdayFragment();
            } else if (position == 3) {
                return new WednesdayFragment();
            } else if (position == 4) {
                return new ThursdayFragment();
            }
            else if (position == 5) {
                return new FridayFragment();
            }
            return new FridayFragment();
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sync_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        int id=item.getItemId();

        switch (id){
            case R.id.sync:
                if (!isConnected(TabnFragment.this)){ buildDialog(TabnFragment.this).show();}
                else {

                    databaseHelper.deleteUserfrag();
                    //fetchData();
                    new StoreJSonDataInToSQLiteClass(TabnFragment.this).execute();

                }

                break;
        }
        switch (id){
            case R.id.update:
                startActivity(new Intent(TabnFragment.this,TabnFragment.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class StoreJSonDataInToSQLiteClass extends AsyncTask<Void, Void, Void> {
        public Context context;

        public StoreJSonDataInToSQLiteClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {



            progressDialog = new ProgressDialog(TabnFragment.this);
            progressDialog.setTitle("LOADING");
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {


            aquery.ajax(fetchUrl, JSONArray.class, new AjaxCallback<JSONArray>() {
                @Override
                public void callback(String url, JSONArray array, AjaxStatus status) {
                    super.callback(url, array, status);
                    Log.i("response", url + "response:" + array);

                    databaseHelper.deleteUserfrag();
                    ArrayList<UserInfoss> list = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            JSONObject object = array.getJSONObject(i);
                            UserInfoss info = new UserInfoss();
                            info.id = object.getString("id");
                            info.day = object.getString("day");
                            info.faculty = object.getString("faculty");
                            info.teacher1 = object.getString("teacher1");
                            info.teacher2 = object.getString("teacher2");
                            info.teacher3 = object.getString("teacher3");
                            info.teacher4 = object.getString("teacher4");
                            info.subject1 = object.getString("subject1");
                            info.subject2 = object.getString("subject2");
                            info.subject3 = object.getString("subject3");
                            info.subject4 = object.getString("subject4");
                            info.room1 = object.getString("room1");
                            info.room2 = object.getString("room2");
                            info.room3 = object.getString("room3");
                            info.room4 = object.getString("room4");
                            info.starts1 = object.getString("starts1");
                            info.starts2 = object.getString("starts2");
                            info.starts3 = object.getString("starts3");
                            info.starts4 = object.getString("starts4");
                            info.ends1 = object.getString("ends1");
                            info.ends2 = object.getString("ends2");
                            info.ends3 = object.getString("ends3");
                            info.ends4 = object.getString("ends4");

                            ContentValues cv = new ContentValues();
                            cv.put("id",object.getString("id"));
                            cv.put("faculty",object.getString("faculty"));
                            cv.put("day",object.getString("day"));
                            cv.put("subject1",object.getString("subject1"));
                            cv.put("subject2",object.getString("subject2"));
                            cv.put("subject3",object.getString("subject3"));
                            cv.put("subject4",object.getString("subject4"));
                            cv.put("teacher1",object.getString("teacher1"));
                            cv.put("teacher2",object.getString("teacher2"));
                            cv.put("teacher3",object.getString("teacher3"));
                            cv.put("teacher4",object.getString("teacher4"));
                            cv.put("room1",object.getString("room1"));
                            cv.put("room2",object.getString("room2"));
                            cv.put("room3",object.getString("room3"));
                            cv.put("room4",object.getString("room4"));
                            cv.put("starts1", object.getString("starts1"));
                            cv.put("starts2", object.getString("starts2"));
                            cv.put("starts3",  object.getString("starts3"));
                            cv.put("starts4",  object.getString("starts4"));
                            cv.put("ends1",object.getString("ends1"));
                            cv.put("ends2", object.getString("ends2"));
                            cv.put("ends3", object.getString("ends3"));
                            cv.put("ends4",object.getString("ends4"));
                            databaseHelper.insertRoutine(cv);

                            list.add(info);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       // sundayFragment.populateData();
                    }

                    progressDialog.dismiss();
                }
            });

          //  startActivity( getIntent()) ;
            return null;

        }

        @Override
        protected void onPostExecute(Void result)

        {
            super.onPostExecute(result);
            //sundayFragment.populateData();
        }

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mIntent = getIntent();
                finish();
                startActivity(mIntent);
            }
        });

        return builder;
    }

    public void fetchData(){
        aquery.ajax(fetchUrl, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                super.callback(url, array, status);
                Log.i("response", url + "response:" + array);

                databaseHelper.deleteUserfrag();
                ArrayList<UserInfoss> list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    try {

                        JSONObject object = array.getJSONObject(i);
                        UserInfoss info = new UserInfoss();
                        info.id = object.getString("id");
                        info.day = object.getString("day");
                        info.faculty = object.getString("faculty");
                        info.teacher1 = object.getString("teacher1");
                        info.teacher2 = object.getString("teacher2");
                        info.teacher3 = object.getString("teacher3");
                        info.teacher4 = object.getString("teacher4");
                        info.subject1 = object.getString("subject1");
                        info.subject2 = object.getString("subject2");
                        info.subject3 = object.getString("subject3");
                        info.subject4 = object.getString("subject4");
                        info.room1 = object.getString("room1");
                        info.room2 = object.getString("room2");
                        info.room3 = object.getString("room3");
                        info.room4 = object.getString("room4");
                        info.starts1 = object.getString("starts1");
                        info.starts2 = object.getString("starts2");
                        info.starts3 = object.getString("starts3");
                        info.starts4 = object.getString("starts4");
                        info.ends1 = object.getString("ends1");
                        info.ends2 = object.getString("ends2");
                        info.ends3 = object.getString("ends3");
                        info.ends4 = object.getString("ends4");

                        ContentValues cv = new ContentValues();
                        cv.put("id",object.getString("id"));
                        cv.put("faculty",object.getString("faculty"));
                        cv.put("day",object.getString("day"));
                        cv.put("subject1",object.getString("subject1"));
                        cv.put("subject2",object.getString("subject2"));
                        cv.put("subject3",object.getString("subject3"));
                        cv.put("subject4",object.getString("subject4"));
                        cv.put("teacher1",object.getString("teacher1"));
                        cv.put("teacher2",object.getString("teacher2"));
                        cv.put("teacher3",object.getString("teacher3"));
                        cv.put("teacher4",object.getString("teacher4"));
                        cv.put("room1",object.getString("room1"));
                        cv.put("room2",object.getString("room2"));
                        cv.put("room3",object.getString("room3"));
                        cv.put("room4",object.getString("room4"));
                        cv.put("starts1", object.getString("starts1"));
                        cv.put("starts2", object.getString("starts2"));
                        cv.put("starts3",  object.getString("starts3"));
                        cv.put("starts4",  object.getString("starts4"));
                        cv.put("ends1",object.getString("ends1"));
                        cv.put("ends2", object.getString("ends2"));
                        cv.put("ends3", object.getString("ends3"));
                        cv.put("ends4",object.getString("ends4"));
                        databaseHelper.insertRoutine(cv);

                        list.add(info);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // sundayFragment.populateData();

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(TabnFragment.this, MainSlider12345.class));
        finish();

    }
}



