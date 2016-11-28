package com.example.victor.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String BIERS_UPDATE = "B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_hw= (Button)findViewById(R.id.Button);


        btn_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,SecondeActivity.class);
                startActivity(intent2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemToast:
                Toast.makeText(getApplicationContext(),getString(R.string.item),Toast.LENGTH_LONG).show();
                break;
            case R.id.itemDialog:
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setMessage(R.string.dialog_msg);
                builder.setTitle(R.string.dialog_title);
                AlertDialog dialog = builder.show();
                break;
            case  R.id.itemNotif:
                Notification.Builder notif = new Notification.Builder(this);
                Intent resultIntent = new Intent(this,MainActivity.class);
                NotificationManager notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                notif.setContentTitle(getString(R.string.notif_title));
                notif.setContentText(getString(R.string.notif_text));
                notif.setContentIntent(pendingIntent);
                notif.setSmallIcon(android.R.drawable.btn_default);
                notifManager.notify(0, notif.build());

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
