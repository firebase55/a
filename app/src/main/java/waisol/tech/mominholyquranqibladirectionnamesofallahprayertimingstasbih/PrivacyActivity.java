package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        Toolbar toolbar = findViewById(R.id.privacyToolbar);
        toolbar.setVisibility(View.VISIBLE);

        PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("privacypolicy.pdf").load();
    }

    public void back(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }
}
