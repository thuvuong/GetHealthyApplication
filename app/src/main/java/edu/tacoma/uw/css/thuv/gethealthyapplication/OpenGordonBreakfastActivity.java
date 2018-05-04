package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class OpenGordonBreakfastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gordon_breakfast);

        String url = "https://www.youtube.com/watch?v=PUP7U5vTMM0";
        // Capture the layout's ImageView and set the string as its text
        WebView webView = (WebView) findViewById(R.id.gordon_webView);

        webView = (WebView) findViewById(R.id.gordon_webView);
        webView.loadUrl(url);
    }
}
