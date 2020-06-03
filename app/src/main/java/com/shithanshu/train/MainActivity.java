package com.shithanshu.train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button startBackgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBackgroundTask=findViewById(R.id.startBut);



//
//        // Set the alarm to start at approximately 2:00 p.m.
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//
//// With setInexactRepeating(), you have to use one of the AlarmManager interval
//// constants--in this case, AlarmManager.INTERVAL_DAY.
//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, alarmIntent);


//
//        Intent notificationIntent=new Intent(MainActivity.this,checkerSystem.class);
//        PendingIntent contentIntent=PendingIntent.getBroadcast(getApplicationContext(),100,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        if(contentIntent!=null && am !=null)
//        am.cancel(contentIntent);
//    am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),300,contentIntent);
//    System.out.println("printin 1"+AlarmManager.INTERVAL_FIFTEEN_MINUTES/60);


        startBackgroundTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final TimePicker tp=(TimePicker) findViewById(R.id.timePicker);
                String s=""+tp.getHour()+" "+tp.getMinute();

                String message="Seats "+s;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, tp.getHour());
                calendar.set(Calendar.MINUTE, tp.getMinute());



                Intent notificationIntent=new Intent(MainActivity.this,checkerSystem.class);
                PendingIntent contentIntent=PendingIntent.getBroadcast(getApplicationContext(),100,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                if(contentIntent!=null && am !=null)
                    am.cancel(contentIntent);
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_HALF_HOUR/30 ,contentIntent);
                System.out.println("printin 1"+AlarmManager.INTERVAL_FIFTEEN_MINUTES/60);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(
                        MainActivity.this
                ).setSmallIcon(R.drawable.ic_train_black_24dp )
                        .setContentTitle("Low Seat Availability")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent intent=new Intent(MainActivity.this,Alert.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);
                PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);
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
        });
    }
}
