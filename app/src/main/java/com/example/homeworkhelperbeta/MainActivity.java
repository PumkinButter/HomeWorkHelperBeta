package com.example.homeworkhelperbeta;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.homeworkhelperbeta.Adapters.ToDoAdapter;
import com.example.homeworkhelperbeta.Model.ToDoModel;
import com.example.homeworkhelperbeta.Utils.DatabaseHandler;
import com.example.homeworkhelperbeta.interfaces.DialogCloseListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private FloatingActionButton fab2;


    private List<ToDoModel> taskList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        (MyProperties.getInstance().hour) = 8;
        (MyProperties.getInstance().minute)= 30;

        if (MyProperties.getInstance().checkBox>0); {



            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, (MyProperties.getInstance().hour));
            calendar.set(Calendar.MINUTE, (MyProperties.getInstance().minute));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Intent intent=new Intent(MainActivity.this, AlarmSetter.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

            AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }




     //   getSupportActionBar().hide();


        db = new DatabaseHandler(this);
        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter =new ToDoAdapter(db,MainActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

       ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);
        fab2 =findViewById(R.id.fab2);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }

        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }

        });


    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){
        CharSequence name= "HomeworkHelper";
        NotificationChannel channel = new NotificationChannel("notify", "HomeworkHelper", NotificationManager.IMPORTANCE_DEFAULT);
    }

}