package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void manual(View view) {
        startActivity(new Intent(this, PrayerNextActivity.class));
        finish();
    }

    public void auto(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
