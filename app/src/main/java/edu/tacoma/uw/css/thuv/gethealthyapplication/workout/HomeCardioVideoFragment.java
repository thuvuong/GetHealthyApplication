package edu.tacoma.uw.css.thuv.gethealthyapplication.workout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.FoodActivity;
import edu.tacoma.uw.css.thuv.gethealthyapplication.workout.homecardiovideo.HomeCardioVideo;

/**
 * Use this fragment to display the video based on user selection
 */
public class HomeCardioVideoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private HomeCardioVideo mVideo;
    public HomeCardioVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideo = (HomeCardioVideo) getArguments().getSerializable(WorkoutActivity.VIDEO_OBJECT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_cardio_video, container, false);

        String url = mVideo.getUrl();
        WebView wv = (WebView) v.findViewById(R.id.home_cardio_wv1);

//        wv.getSettings().setJavaScriptEnabled(true);
//        wv.getSettings().setDomStorageEnabled(true);
//
//        wv.setWebViewClient(new WebViewClient());
////webView.setWebChromeClient(new WebChromeClient());

        wv.loadUrl(url);
        return v;
    }

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
