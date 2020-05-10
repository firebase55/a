package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toolbar;

import waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Database.ReminderDatabase;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d("tehrim", new ReminderDatabase(SplashActivity.this).getAllReminders().size() + "");
        Log.d("tehrim", AppController.getInstance().getIsAlarmSet(SplashActivity.this)+ "");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SelectionActivity.class));
                finish();

            }
        }, 2000);
    }


}
