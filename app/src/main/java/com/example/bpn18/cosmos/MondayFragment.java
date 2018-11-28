package com.example.bpn18.cosmos;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by bpn18 on 2/16/2018.
 */

public class MondayFragment extends Fragment {
    DatabaseHelper databaseHelper;
    TextView day, faculty, subject1, subject2, subject3, subject4, teacher1, teacher2, teacher3, teacher4,
            room1, room2, room3, room4, starts1, starts2, starts3, starts4, ends1, ends2, ends3, ends4;
    Button update;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_detail, null);
        databaseHelper = new DatabaseHelper(getActivity());
        update = (Button) view.findViewById(R.id.update);
        faculty = (TextView) view.findViewById(R.id.faculty);
        day = (TextView) view.findViewById(R.id.day);
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

        populateData();

        return view;
    }
    public void populateData() {
        UserInfo info = databaseHelper.getRoutine("select * from routine123 where day='Monday'");
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