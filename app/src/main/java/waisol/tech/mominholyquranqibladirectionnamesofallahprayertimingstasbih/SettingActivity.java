package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = findViewById(R.id.settingToolbar);
        toolbar.setVisibility(View.VISIBLE);
    }

    public void about_next(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }
    public void terms_next(View view) {
        startActivity(new Intent(this, TermsActivity.class));
    }
    public void privacy_next(View view) {
        startActivity(new Intent(this, PrivacyActivity.class));
    }
    public void share_next(View view) {
        startActivity(new Intent(this, ShareActivity.class));
    }
    public void more_next(View view) {
        startActivity(new Intent(this, MoreAppsActivity.class));
    }
    public void contact_next(View view) {
        startActivity(new Intent(this, ContactActivity.class));
    }
    public void rate_next(View view) {
        startActivity(new Intent(this, FeedbackActivity.class));
    }

    public void back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
