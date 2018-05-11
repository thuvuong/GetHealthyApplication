/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * A activity which launches the Jamie Oliver video.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class OpenJamieBreakfast1Activity extends AppCompatActivity {

    /**
     * Gathering how the activity should be oriented.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_jamie_breakfast1);

        String url = "https://www.youtube.com/watch?v=t0Mr88d9eK4";

        // Capture the layout's ImageView and set the string as its text
        WebView webView = (WebView) findViewById(R.id.jamie1_webView);

        webView = (WebView) findViewById(R.id.jamie1_webView);
        webView.loadUrl(url);
    }
}