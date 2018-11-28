package com.example.bpn18.cosmos;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by bpn18 on 2/24/2018.
 */
// APA91bGi9xpx2e7bxjCs4l67O9lHjvWS8kulN10sinHL1JuVfXGHn0b8jndqpnuL-n28727QHrccZ7s1sGNAjh_n2rUnIeRynGBu2DwVWrmTMZTuZObqwfHGcnwvV51dU8JFdPUHUy45
public class FireNotification extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = "REG_TOKEN ";

    public void onTokenRefresh(){
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);


    }
}
