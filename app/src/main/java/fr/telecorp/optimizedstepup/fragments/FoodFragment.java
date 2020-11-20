package fr.telecorp.optimizedstepup.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fr.telecorp.optimizedstepup.MainActivity;
import fr.telecorp.optimizedstepup.R;
import fr.telecorp.optimizedstepup.food.FoodActivity;
import fr.telecorp.optimizedstepup.food.FoodList;

public class FoodFragment extends Fragment {

    private final static String DATA_KEY="DATA_KEY";
    private FoodList dataSet;
    private Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, null);
        btn= view.findViewById(R.id.btn_food);
        /**GET BUNDLE**/
        Bundle bundle = getArguments();
        if (bundle != null) {
            dataSet = bundle.getParcelable(DATA_KEY);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FoodActivity.class);
                intent.putExtra(DATA_KEY,(Parcelable) dataSet);
                startActivity(intent);
            }
        });


        return view;
    }

}

