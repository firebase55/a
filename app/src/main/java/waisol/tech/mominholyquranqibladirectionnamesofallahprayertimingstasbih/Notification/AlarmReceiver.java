/*
 * Copyright 2015 Blanyal D'Souza.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Notification;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.Date;

import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.AppController;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Database.ReminderDatabase;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.MainActivity;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model.Reminder;
import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.R;

import static android.content.Context.ALARM_SERVICE;


public class AlarmReceiver extends BroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        int mReceivedID = Integer.parseInt(intent.getStringExtra(AppController.EXTRA_REMINDER_ID));
//        intent.setClass(context,Calling.class);
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);

        // Get notification title from Reminder Database
        ReminderDatabase rb = new ReminderDatabase(context);
        Reminder reminder = rb.getReminder(mReceivedID);
        String mTitle = reminder.getTitle();
        rb.deleteReminder(reminder);


        Log.d("response", mTitle);


        // Create intent to open ReminderEditActivity on notification click

        //Intent editIntent = new Intent(context, ReminderEditActivity.class);

        if (AppController.getInstance().getData(context, "notification").equals("0")) {

            Log.d("response", "Notification Enabled");

            Intent editIntent = new Intent(context, MainActivity.class);


            PendingIntent mClick = PendingIntent.getActivity(context, mReceivedID, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri alarmSound = Uri.parse("android.resource://waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih/" + R.raw.azanone);

            //  Create Notification


            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(mTitle)
                    .setTicker(mTitle)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setLights(Color.GREEN, 3000, 3000)
                    .setSound(alarmSound)
                    .setContentIntent(mClick)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true);
            NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nManager.notify(mReceivedID, mBuilder.build());
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "ALARAM_MUSLIM");
            wakeLock.acquire(15000);
        } else {
            Log.d("response", "Notification disabled");

        }

    }


    public void setAlarm(Context context, Calendar calendar, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(AppController.EXTRA_REMINDER_ID, Integer.toString(ID));
        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Calculate notification time
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long diffTime = calendar.getTimeInMillis() - currentTime;



        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+diffTime,mPendingIntent);

        // Restart alarm if device is rebooted
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        // Cancel Alarm using Reminder ID
        mPendingIntent = PendingIntent.getBroadcast(context, ID, new Intent(context, AlarmReceiver.class), 0);
        mAlarmManager.cancel(mPendingIntent);

        // Disable alarm
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
    public void alramshow()
    {

    }
}