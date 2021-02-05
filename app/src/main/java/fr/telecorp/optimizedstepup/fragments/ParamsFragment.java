package fr.telecorp.optimizedstepup.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import fr.telecorp.optimizedstepup.R;

public class ParamsFragment extends Fragment {

    Button button_apply;
    EditText paramCalories;
    EditText paramProteins;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_params, null);
        button_apply = view.findViewById(R.id.params_apply);
        paramCalories = view.findViewById(R.id.params_calories);
        paramProteins = view.findViewById(R.id.params_proteins);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        paramCalories.setText(preferences.getString("storedParamCalories", "0"));
        paramProteins.setText(preferences.getString("storedParamProteins", "0"));

        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((paramCalories.getText().toString().length()>0)&&(paramProteins.getText().toString().length()>0)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("storedParamCalories", paramCalories.getText().toString()); // value to store
                    editor.putString("storedParamProteins", paramProteins.getText().toString()); // value to store
                    editor.apply();
                }
            }
        });

        return view;
    }

}
