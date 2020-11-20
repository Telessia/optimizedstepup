package fr.telecorp.optimizedstepup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import fr.telecorp.optimizedstepup.R;

public class ScheduleFragment extends Fragment {

    EditText textMsg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_food, null);
        textMsg = (EditText) view.findViewById(R.id.schedfragtext);

        Bundle bundle = getArguments();
        if(bundle != null){
            String msg = bundle.getString("KEY_FOOD");
            if(msg != null){
                textMsg.setText(msg);
            }
        }

        return view;
    }

    public void setMsg(String msg){
        textMsg.setText(msg);
    }

}

