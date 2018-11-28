package com.example.bpn18.cosmos;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bpn18 on 1/28/2018.
 */

public class TopFragment  extends Fragment {
    TextView day, faculty, subject1, subject2, subject3, subject4, teacher1, teacher2, teacher3, teacher4,
            room1, room2, room3, room4, starts1, starts2, starts3, starts4, ends1, ends2, ends3, ends4;
DatabaseHelper databaseHelper;
    AQuery aquery;
    String fetchUrl = "https://sastopustak.000webhostapp.com/selectroutineIT7.php";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_detail, null);
        aquery = new AQuery(getActivity(), view);
        databaseHelper = new DatabaseHelper(getActivity());
        faculty = (EditText) view.findViewById(R.id.faculty);
        day = (EditText) view.findViewById(R.id.day);
        subject1 = (TextView) view.findViewById(R.id.subject1);
        subject2 = (TextView) view.findViewById(R.id.subject2);
        subject3 = (TextView) view.findViewById(R.id.subject3);
        subject4 = (TextView) view.findViewById(R.id.subject4);
        teacher1 = (TextView) view.findViewById(R.id.teacher1);
        teacher2 = (TextView) view.findViewById(R.id.teacher2);
        teacher3 = (TextView) view.findViewById(R.id.teacher3);
        teacher4 = (TextView) view.findViewById(R.id.teacher4);
        room1 = (TextView) view.findViewById(R.id.room1);
        room2 = (TextView) view.findViewById(R.id.room2);
        room3 = (TextView) view.findViewById(R.id.room3);
        room4 = (TextView) view.findViewById(R.id.room4);
        starts1 = (TextView) view.findViewById(R.id.starts1);
        starts2 = (TextView) view.findViewById(R.id.starts2);
        starts3 = (TextView) view.findViewById(R.id.starts3);
        starts4 = (TextView) view.findViewById(R.id.starts4);
        ends1 = (TextView) view.findViewById(R.id.ends1);
        ends2 = (TextView) view.findViewById(R.id.ends2);
        ends3 = (TextView) view.findViewById(R.id.ends3);
        ends4 = (TextView) view.findViewById(R.id.ends4);
        fetchData();

        return view;


    }
    public void fetchData() {
        aquery.ajax(fetchUrl, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                super.callback(url, array, status);


                Log.i("response", url + " response: " + array);
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

                        cv.put("faculty", object.getString("faculty"));
                        cv.put("day", object.getString("day"));
                        cv.put("subject1", object.getString("subject1"));
                        cv.put("subject2", object.getString("subject2"));
                        cv.put("subject3", object.getString("subject3"));
                        cv.put("subject4", object.getString("subject4"));
                        cv.put("teacher1", object.getString("teacher1"));
                        cv.put("teacher2", object.getString("teacher2"));
                        cv.put("teacher3", object.getString("teacher3"));
                        cv.put("teacher4", object.getString("teacher4"));
                        cv.put("room1", object.getString("room1"));
                        cv.put("room2", object.getString("room2"));
                        cv.put("room3", object.getString("room3"));
                        cv.put("room4", object.getString("room4"));
                        cv.put("starts1", object.getString("starts1"));
                        cv.put("starts2", object.getString("starts2"));
                        cv.put("starts3",  object.getString("starts3"));
                        cv.put("starts4",  object.getString("starts4"));
                        cv.put("ends1", object.getString("ends1"));
                        cv.put("ends2", object.getString("ends2"));
                        cv.put("ends3", object.getString("ends3"));
                        cv.put("ends4", object.getString("ends4"));
                        databaseHelper.insertRoutine(cv);

                        list.add(info);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                    populateData();


            }
        });

    }
    public void populateData() {
        UserInfo info = databaseHelper.getRoutine("select * from routine123 where day='sunday'");
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

}