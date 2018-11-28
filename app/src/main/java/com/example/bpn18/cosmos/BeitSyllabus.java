package com.example.bpn18.cosmos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;



public class BeitSyllabus extends AppCompatActivity implements PhotoViewAttacher.OnPhotoTapListener{
 PhotoView Beit1,Beit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beit_syllabus);
        Beit1 = (PhotoView)findViewById(R.id.beit1);
        Beit1.setMaximumScale(5);
        Beit1.setOnPhotoTapListener(this);
        Beit2 = (PhotoView)findViewById(R.id.beit2);
        Beit2.setMaximumScale(5);
        Beit2.setOnPhotoTapListener(this);





    }

    @Override
    public void onPhotoTap(View view, float v, float v1) {
        Toast.makeText(this,"Tapping Image", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOutsidePhotoTap() {
        Toast.makeText(this,"Tapping outside", Toast.LENGTH_SHORT).show();

    }
}
