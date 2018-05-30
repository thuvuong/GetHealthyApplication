package edu.tacoma.uw.css.thuv.gethealthyapplication.food;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MealLogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealLogFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MealLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MealLogFragment.
     */
    public static MealLogFragment newInstance(String param1, String param2) {
        MealLogFragment fragment = new MealLogFragment();
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
        View v = inflater.inflate(R.layout.fragment_meal_log, container, false);
        android.support.v7.widget.Toolbar toolbar = getActivity().findViewById(R.id.food_toolbar);
        toolbar.setTitle("  Food: Meal Log");

        Button breakFastMeal = (Button) v.findViewById(R.id.breakfast_meal_btn);
        Button lunchMeal = (Button) v.findViewById(R.id.lunch_meal_btn);
        Button dinnerMeal = (Button) v.findViewById(R.id.dinner_meal_btn);

        breakFastMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.food_fragment_container, new BreakfastMealFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        lunchMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.food_fragment_container, new LunchMealFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        dinnerMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.food_fragment_container, new DinnerMealFragment())
                        .addToBackStack(null)
                        .commit();
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
        void mealLog(Uri uri);
    }
}
