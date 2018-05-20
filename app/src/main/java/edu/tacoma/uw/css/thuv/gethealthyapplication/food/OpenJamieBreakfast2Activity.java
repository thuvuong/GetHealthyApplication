package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

/**
 * A activity which launches the Jamie Oliver video.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class OpenJamieBreakfast2Activity extends AppCompatActivity {

    /**
     * Gathering how the activity should be oriented.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_jamie_breakfast2);

        String url = "https://www.youtube.com/watch?v=Q1x4H2av5f8";
        // Capture the layout's ImageView and set the string as its text
        WebView webView = (WebView) findViewById(R.id.jamie2_webView);

        webView = (WebView) findViewById(R.id.jamie2_webView);
        webView.loadUrl(url);
    }
}