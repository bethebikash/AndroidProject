package com.bhattaraibikash.erepair.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.notification.CreateChannel;

public class BroadcastReceiverClass extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadcastReceiverClass(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;
        notificationManagerCompat = NotificationManagerCompat.from(context);

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );

            if (noConnectivity) {

                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                ConnectionNotification("No Connection", "Please check your connection");
            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                ConnectionNotification("Connected", "You are now connected");

            }
        }
    }

    private  void ConnectionNotification(String title, String message) {
        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_wifi_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }
}
