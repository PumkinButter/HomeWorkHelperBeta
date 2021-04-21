package com.example.homeworkhelperbeta;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.homeworkhelperbeta.Adapters.ToDoAdapter;
import com.example.homeworkhelperbeta.Model.ToDoModel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AlarmSetter extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notify")
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle("Homework Helper Reminder")
                .setContentText("Looks like you have unfinished tasks to do...")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notManager = NotificationManagerCompat.from(context);

        notManager.notify(200, builder.build());




    }

}