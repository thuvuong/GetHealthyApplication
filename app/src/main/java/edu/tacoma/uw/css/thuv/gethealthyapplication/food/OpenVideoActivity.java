package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.MainActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.foodvideo.FoodVideo;

public class OpenVideoActivity extends AppCompatActivity {


    private FoodVideo mVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_video);
        mVideo = (FoodVideo) getIntent().getExtras().getSerializable(MainActivity.VIDEO_OBJECT);
        // Capture the layout's ImageView and set the string as its text
        WebView webView = (WebView) findViewById(R.id.webView);

        // webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(mVideo.getUrl());
    }
}
