package com.shithanshu.train;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class checkerSystem extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


//        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
//        PackageManager pm = context.getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String message="Seats "+System.currentTimeMillis();
        Intent intent1=new Intent(context,Alert.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_train_black_24dp)
                .setContentTitle("Low Seat Availability")
                .setContentText(message)
                .setAutoCancel(false);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            String channelId="Train_Channel_id";
            NotificationChannel channel=new NotificationChannel(channelId,"Train Alert",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        notificationManager.notify(100,builder.build());
    }
}
