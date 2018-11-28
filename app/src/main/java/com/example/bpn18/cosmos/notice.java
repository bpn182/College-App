package com.example.bpn18.cosmos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by bpn18 on 10/27/2017
 */

public class notice extends AppCompatActivity implements PhotoViewAttacher.OnPhotoTapListener {
    // public PhotoView zoomImage;
    AQuery aquery;
    LinearLayout container;

    String fetchUrl = "https://sastopustak.000webhostapp.com/notice.php";
    //String fetchUrl="http://192.168.0.102/www/selectnotice.php";
    private Uri mImageUri;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isConnected(notice.this)) buildDialog(notice.this).show();
        setContentView(R.layout.notice);
        aquery = new AQuery(this);
        container = (LinearLayout) findViewById(R.id.container);
        fetchData();
    }


    public void fetchData() {
        aquery.ajax(fetchUrl, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                super.callback(url, array, status);

                Log.i("response", url + " response: " + array);
                ArrayList<NoticeInfo> list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject object = array.getJSONObject(i);
                       NoticeInfo ninfo = new NoticeInfo();
                        ninfo.id = object.getString("id");
                        ninfo.notice = object.getString("notice");
                        list.add(ninfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                populateData(list);

            }
        });

    }

    public void populateData(ArrayList<NoticeInfo> list) {
        container.removeAllViews();

        for (int i = 0; i < list.size(); i++) {

        }
        for (final NoticeInfo ninfo : list
                ) {

            View view = LayoutInflater.from(this).inflate(R.layout.notice_contained, null);
            PhotoView zoomImage;
            zoomImage = (PhotoView) view.findViewById(R.id.zoomImage);
            zoomImage.setMaximumScale(5);
            aquery.id(zoomImage).image(ninfo.notice, true, true);
            zoomImage.setOnPhotoTapListener(this);

            // name.setText(ginfo.name);
            container.addView(view);


        }
    }


    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


    @Override
    public void onPhotoTap(View view, float v, float v1) {
        Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOutsidePhotoTap() {
        Toast.makeText(this,"Tapping Outside", Toast.LENGTH_SHORT).show();

    }
}
