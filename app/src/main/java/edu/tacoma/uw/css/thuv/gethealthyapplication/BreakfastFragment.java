/*
 * TCSS 450: Mobile Application Programming
 * Professor: Menaka Abraham
 * Assignment: Project Phase I
 */

package edu.tacoma.uw.css.thuv.gethealthyapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


/**
 * Display a list of healthy breakfast recipe videos.
 *
 * @author Team 11
 * @version May 10, 2018
 */
public class BreakfastFragment extends Fragment {

    /** The button to watch Ramsay Gordon first video.*/
    private Button btn_gordon;

    /** The button to watch Ramsay Gordon second video.*/
    private Button btn_jamie1;

    /** The button to watch Ramsay Gordon third video.*/
    private Button btn_jamie2;

    /**
     * The listener for this fragment to notify the activity which
     * button was pressed.
     */
    private OnFragmentInteractionListener mListener;

    /** Required empty public constructor.*/
    public BreakfastFragment() {

    }

    /**
     * Setting up the fragment.
     *
     * @param savedInstanceState The given data from an activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Opens the "Gordon Breakfast" video.
     *
     * @param v
     */
    public void launchGordonBreakfast(View v) {
        Intent intent = new Intent(getActivity(),
                                OpenGordonBreakfastActivity.class);

        startActivity(intent);
    }

    /**
     * Opens a Jamie Breakfast video.
     */
    public void launchJamieBreakfast1() {
        Intent intent = new Intent(getActivity(),
                                OpenJamieBreakfast1Activity.class);

        startActivity(intent);
    }

    /**
     * Opens a Jamie Breakfast video.
     */
    public void launchJamieBreakfast2() {
        Intent intent = new Intent(getActivity(),
                                OpenJamieBreakfast2Activity.class);

        startActivity(intent);
    }

    /**
     * Initializes all the features to make this page display.
     *
     * @param inflater Specifies how to display the fragment.
     * @param container The container where this fragment will reside.
     * @param savedInstanceState The given data from an activity.
     * @return The view of how this fragment will be displayed.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_breakfast, container,
                                                            false);
        btn_gordon = (Button) v.findViewById(R.id.gordon_btn);
        btn_gordon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGordonBreakfast(v);
            }
        });

        btn_jamie1 = (Button) v.findViewById(R.id.jamie1_btn);
        btn_jamie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchJamieBreakfast1();
            }
        });

        btn_jamie2 = (Button) v.findViewById(R.id.jamie2_btn);
        btn_jamie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchJamieBreakfast2();
            }
        });
        return v;
    }

    /**
     * Launches a video when the button is pressed.
     *
     * @param uri The specifics on the click.
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * Connecting the local class listener to the activity this
     * fragment is attached to.
     *
     * @param context The activity calling this fragment.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Disassociating with the current activity and stopping the
     * listener.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}