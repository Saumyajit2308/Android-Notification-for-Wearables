package com.example.h.cs6312lab7;

import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MobileActivity extends AppCompatActivity {
    private NotificationCompat.Builder notification_builder;
    private NotificationManagerCompat notification_manager;
    private int notification_id=1;
    private Button btn;
    private String message="Wear Notification Received";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        btn=(Button)findViewById(R.id.button);
        Intent open_activity_intent= new
                Intent(MobileActivity.this,NotificationActivity.class);
        open_activity_intent.putExtra("message", message);
        PendingIntent pending_intent
                =PendingIntent.getActivity(MobileActivity.this,0,open_activity_intent,PendingIntent
                .FLAG_CANCEL_CURRENT);
        notification_builder=new NotificationCompat.Builder(MobileActivity.this)
                .setSmallIcon(R.drawable.ic_check_circle_black_24dp)
                .setContentTitle("Happy Days")
                .setContentText(message)
                .setAutoCancel(true)
                .setLights(0xff00ff00, 300, 100)
                .setContentIntent(pending_intent)
                .addAction(R.drawable.ic_map, getString(R.string.map),
                        pending_intent)
                .setVibrate(new long[]{500,500,500,500})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        notification_manager =NotificationManagerCompat.from(MobileActivity.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notification_manager.notify(notification_id,notification_builder.build());
            }
        });
    }
}