package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.food.log.LogInformation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogListDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogListDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogListDetailFragment extends Fragment {
    public final static String LOG_ITEM_SELECTED = "log_selected";

    private TextView date;
    private TextView type;
    private TextView water;
    private TextView other;
    private TextView food;
    private TextView vegi;
    private TextView fruit;
    private TextView grain;
    private TextView meat;
    private TextView dairy;

    private OnFragmentInteractionListener mListener;

    public LogListDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LogListDetailFragment.
     */
    public static LogListDetailFragment newInstance() {
        LogListDetailFragment fragment = new LogListDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_list_detail, container, false);

        date = (TextView) v.findViewById(R.id.time_edit);
        type = (TextView) v.findViewById(R.id.breakfast_meal_edit);
        water = (TextView) v.findViewById(R.id.water_consume_edit);
        other = (TextView) v.findViewById(R.id.other_consume_edit);
        food = (TextView) v.findViewById(R.id.food_name_edit);
        vegi = (TextView) v.findViewById(R.id.vegi_consume_edit);
        fruit = (TextView) v.findViewById(R.id.fruit_consume_edit);
        grain = (TextView) v.findViewById(R.id.grains_consume2_edit);
        meat = (TextView) v.findViewById(R.id.meat_consume_edit);
        dairy = (TextView) v.findViewById(R.id.dairy_product_consume_edit);


        return v;
    }

    public void updateView(LogInformation logInformation){
        if (logInformation != null){
            date.setText(logInformation.getDate());
            type.setText(logInformation.getType());
            water.setText(logInformation.getmWater());
            other.setText(logInformation.getmOther());
            food.setText(logInformation.getmFood());
            vegi.setText(logInformation.getmVegi());
            fruit.setText(logInformation.getmFruit());
            grain.setText(logInformation.getmGrain());
            meat.setText(logInformation.getmMeat());
            dairy.setText(logInformation.getmDairy());

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("detail", "resume");
        Bundle args = getArguments();
        if (args != null) {
            updateView((LogInformation) args.getSerializable(LOG_ITEM_SELECTED));
        } else{
            getActivity().getSupportFragmentManager().popBackStack();
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
