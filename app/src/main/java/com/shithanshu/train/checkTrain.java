package com.shithanshu.train;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class checkTrain extends Service {

    private final static String TAG ="ShowNotification";


    @Override
    public void onCreate()
    {
        System.out.println("printing 25");
        System.out.println(AlarmManager.INTERVAL_FIFTEEN_MINUTES);
        super.onCreate();
        String message="Seats "+System.currentTimeMillis();
        NotificationCompat.Builder builder=new NotificationCompat.Builder(
                this
        ).setSmallIcon(R.drawable.ic_train_black_24dp )
                .setContentTitle("Low Seat Availability 10")
                .setContentText(message)
                .setAutoCancel(false);
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message",message);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            String channelId="Train_Channel_id";
            NotificationChannel channel=new NotificationChannel(channelId,"Train Alert",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        notificationManager.notify(0,builder.build());

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




//    @Override
//    public void onDestroy()
//    {
//        Intent notificationIntent=new Intent(this,checkTrain.class);
//        PendingIntent contentIntent=PendingIntent.getService(this,0,notificationIntent,0);
//        AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        if(contentIntent!=null && am !=null)
//            am.cancel(contentIntent);
//        am.setInexactRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime()+3000,3000,contentIntent);
//
//    }
//    @Override
//    public void onTaskRemoved(Intent rootIntent) {
//        Intent notificationIntent=new Intent(this,checkTrain.class);
//        PendingIntent contentIntent=PendingIntent.getService(this,0,notificationIntent,0);
//        AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        if(contentIntent!=null && am !=null)
//            am.cancel(contentIntent);
//        am.setInexactRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime()+3000,3000,contentIntent);
//
//    }
}
