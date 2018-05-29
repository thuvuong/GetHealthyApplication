package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Objects;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BreakfastMealFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BreakfastMealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreakfastMealFragment extends Fragment {

    private final static String USER_ADD_URL
            = "http://tcssandroidthuv.000webhostapp.com/get_healthy_app/addMealLog.php?";

    private SharedPreferences mSharedPreferences;


    private OnFragmentInteractionListener mListener;

    private static TextView datePicker;
    private EditText water;
    private EditText other;
    private EditText food;
    private EditText vegi;
    private EditText fruit;
    private EditText grain;
    private EditText meat;
    private EditText dairy;
    private Button log;

    public BreakfastMealFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static BreakfastMealFragment newInstance(String param1, String param2) {
        BreakfastMealFragment fragment = new BreakfastMealFragment();
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
        View v = inflater.inflate(R.layout.fragment_breakfast_meal, container, false);
        mSharedPreferences = getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS),
                Context.MODE_PRIVATE);
        datePicker = (TextView) v.findViewById(R.id.time);
        water = (EditText) v.findViewById(R.id.water_consume);
        other = (EditText) v.findViewById(R.id.other_consume);
        food = (EditText) v.findViewById(R.id.food_name);
        vegi = (EditText) v.findViewById(R.id.vegi_consume);
        fruit = (EditText) v.findViewById(R.id.fruit_consume);
        grain = (EditText) v.findViewById(R.id.grains_consume2);
        meat = (EditText) v.findViewById(R.id.meat_consume);
        dairy = (EditText) v.findViewById(R.id.dairy_product_consume);
        log = (Button) v.findViewById(R.id.log);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTime = datePicker.getText().toString();
                String waterConsume = water.getText().toString();
                String otherConsume = other.getText().toString();
                String foodName = food.getText().toString();
                String vegiConsume = vegi.getText().toString();
                String fruitConsume = fruit.getText().toString();
                String grainConsume = grain.getText().toString();
                String meatConsume = meat.getText().toString();
                String dairyConsume = dairy.getText().toString();

                if(TextUtils.isEmpty(waterConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your water consume",
                            Toast.LENGTH_SHORT).show();
                    water.requestFocus();
                } else if (TextUtils.isEmpty(otherConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your other beverage consume",
                            Toast.LENGTH_SHORT).show();
                    other.requestFocus();
                } else if (TextUtils.isEmpty(foodName)){
                    Toast.makeText(v.getContext(), "You forgot to enter the name of your food",
                            Toast.LENGTH_SHORT).show();
                    food.requestFocus();
                } else if (TextUtils.isEmpty(vegiConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your vegetable consume",
                            Toast.LENGTH_SHORT).show();
                    vegi.requestFocus();
                } else if (TextUtils.isEmpty(fruitConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your fruit consume",
                            Toast.LENGTH_SHORT).show();
                    fruit.requestFocus();
                } else if (TextUtils.isEmpty(grainConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your grain consume",
                            Toast.LENGTH_SHORT).show();
                    grain.requestFocus();
                } else if (TextUtils.isEmpty(meatConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your meat consume",
                            Toast.LENGTH_SHORT).show();
                    meat.requestFocus();
                } else if (TextUtils.isEmpty(dairyConsume)){
                    Toast.makeText(v.getContext(), "You forgot to enter your dairy product consume",
                            Toast.LENGTH_SHORT).show();
                    dairy.requestFocus();
                } else if (TextUtils.isEmpty(dateTime)){
                    Toast.makeText(v.getContext(), "You forgot to select a date", Toast.LENGTH_SHORT)
                            .show();
                    datePicker.requestFocus();
                } else {
                    String url = buildLogURL(v);
                    mListener.addLog(url);
                }
            }
        });

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
     * Builds a url based on the input values that the user provided.
     *
     * @param v The layout of this page.
     * @return A url which contains the new user's private information.
     */
    private String buildLogURL(View v){
        String email = mSharedPreferences.getString("email", "Missing");

        StringBuilder sb = new StringBuilder(USER_ADD_URL);

        try{
            sb.append("email=");
            sb.append(URLEncoder.encode(email, "UTF-8"));

            String date = datePicker.getText().toString();
            sb.append("&date=");
            sb.append(URLEncoder.encode(date, "UTF-8"));

            String type = "Breakfast";
            sb.append("&type=");
            sb.append(URLEncoder.encode(type, "UTF-8"));

            String waterConsume = water.getText().toString();
            sb.append("&water=");
            sb.append(URLEncoder.encode(waterConsume, "UTF-8"));

            String otherConsume = other.getText().toString();
            sb.append("&other=");
            sb.append(URLEncoder.encode(otherConsume, "UTF-8"));

            String foodName = food.getText().toString();
            sb.append("&food=");
            sb.append(URLEncoder.encode(foodName, "UTF-8"));

            String vegiConsume = vegi.getText().toString();
            sb.append("&vegi=");
            sb.append(URLEncoder.encode(vegiConsume, "UTF-8"));

            String fruitConsume = fruit.getText().toString();
            sb.append("&fruit=");
            sb.append(URLEncoder.encode(fruitConsume, "UTF-8"));

            String grainConsume = grain.getText().toString();
            sb.append("&grain=");
            sb.append(URLEncoder.encode(grainConsume, "UTF-8"));

            String meatConsume = meat.getText().toString();
            sb.append("&meat=");
            sb.append(URLEncoder.encode(meatConsume, "UTF-8"));

            String dairyConsume = dairy.getText().toString();
            sb.append("&dairy=");
            sb.append(URLEncoder.encode(dairyConsume, "UTF-8"));


            Log.i(TAG, sb.toString());
        }catch (Exception e){
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return sb.toString();
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
        void addLog(String url);
    }

    /**
     * The class that will create a date picker for the user to pick a date.
     */
    @SuppressLint("ValidFragment")
    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @SuppressLint("NewApi")
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(Objects.requireNonNull(this.getActivity()), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            populateSetDate(year, month + 1, dayOfMonth);
        }

        public void populateSetDate(int year, int month, int day){
            datePicker.setText(month+"/"+day+"/"+year);
        }
    }
}
