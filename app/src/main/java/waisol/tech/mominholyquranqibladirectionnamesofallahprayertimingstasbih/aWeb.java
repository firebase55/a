package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class aWeb extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_web);

        webView = findViewById(R.id.a_webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){




            public void onReceivedError(WebView webView, String errorCode, String description, String failingUrl){

                Toast.makeText(aWeb.this, description, Toast.LENGTH_SHORT).show();
            }
        });webView.loadUrl("https://quranicaudio.com/quran/1");
    }
}
