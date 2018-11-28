package com.example.bpn18.cosmos;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.bpn18.cosmos.R.id.date;
import static com.example.bpn18.cosmos.R.id.detail;

/**
 * Created by bpn18 on 10/27/2017.
 */

public class events extends AppCompatActivity {
    AQuery aquery;
    LinearLayout container;
    DatabaseHelper databaseHelper;
    ProgressDialog progressDialog;

    String fetchUrl ="https://sastopustak.000webhostapp.com/selectevents.php";
    //String fetchUrl ="http://192.168.0.102/www/selectevents.php";
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  if(!isConnected(events.this)) buildDialog(events.this).show();
        setContentView(R.layout.events_jason);
        aquery = new AQuery(this);
        databaseHelper = new DatabaseHelper(this);
        container = (LinearLayout) findViewById(R.id.container);
        populateData();

    }


    private class StoreJSonDataInToSQLiteClass extends AsyncTask<Void, Void, Void> {
        public Context context;

        public StoreJSonDataInToSQLiteClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(events.this);
            progressDialog.setTitle("LOADING");
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            aquery.ajax(fetchUrl, JSONArray.class, new AjaxCallback<JSONArray>(){
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                super.callback(url, array, status);

                databaseHelper.deleteEvents();
                Log.i("response", url +" response: "+array);
                ArrayList<EventInfos> list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++){
                    try {
                        JSONObject object = array.getJSONObject(i);
                        EventInfos einfo = new EventInfos();
                        einfo.id = object.getString("id");
                        einfo.date = object.getString("date");
                        einfo.detail = object.getString("detail");

                        ContentValues cv = new ContentValues();

                        cv.put("id",object.getString("id"));
                        cv.put("date",object.getString("date"));
                        cv.put("detail",object.getString("detail"));

                        databaseHelper.insertEvents(cv);


                        list.add(einfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                populateData();
                progressDialog.dismiss();

            }
        });
        return null;
    }

        @Override
        protected void onPostExecute(Void result)

        {
            super.onPostExecute(result);


        }
    }

    public void populateData() {
        ArrayList<EventInfos> list = databaseHelper.getEventList();
        container.removeAllViews();
        for (int i = 0; i < list.size(); i++) {

        }
        for (EventInfos einfo : list
                ) {
            View view = LayoutInflater.from(this).inflate(R.layout.events, null);
            TextView date  = (TextView) view.findViewById(R.id.date);
            TextView detail= (TextView) view.findViewById(R.id.detail);

            date.setText(einfo.date);
            detail.setText(einfo.detail);

            container.addView(view);

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

                finish();
            }
        });

        return builder;
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
                if(!isConnected(events.this)){buildDialog(events.this).show();}
                else {
                    new StoreJSonDataInToSQLiteClass(events.this).execute();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}



