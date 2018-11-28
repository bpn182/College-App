package com.example.bpn18.cosmos;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by bpn18 on 2/24/2018.
 */

public class FireMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this);

        notificationbuilder.setContentTitle("Notification Alert, Click Me!");
        notificationbuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationbuilder.setSmallIcon(R.drawable.ic_event_black_24dp);
        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationbuilder.setSound(soundUri);
        notificationbuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        notificationManager.notify(0,notificationbuilder.build());




    }
}
