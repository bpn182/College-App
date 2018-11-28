package com.example.bpn18.cosmos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by bpn18 on 10/28/2017.
 */

public class HomeActivity extends AppCompatActivity {
    ImageView imageview;
    Animation scale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        imageview =(ImageView)findViewById(R.id.imageview);
        scale= AnimationUtils.loadAnimation(this,R.anim.scale);
        imageview.startAnimation(scale);
       Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                startActivity(intent);
                finish();

            }
        },2500);
    }

}
