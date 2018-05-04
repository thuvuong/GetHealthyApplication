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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BreakfastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BreakfastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreakfastFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button btn_gordon;
    Button btn_jamie1;
    Button btn_jamie2;
    public BreakfastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BreakfastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BreakfastFragment newInstance(String param1, String param2) {
        BreakfastFragment fragment = new BreakfastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    public void launchGordonBreakfast(View v) {
        Intent intent = new Intent(getActivity(), OpenGordonBreakfastActivity.class);
        startActivity(intent);


    }

    public void launchJamieBreakfast1() {
        Intent intent = new Intent(getActivity(), OpenJamieBreakfast1Activity.class);
        startActivity(intent);


    }

    public void launchJamieBreakfast2() {
        Intent intent = new Intent(getActivity(), OpenJamieBreakfast2Activity.class);
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_breakfast, container, false);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
