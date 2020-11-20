package fr.telecorp.optimizedstepup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import fr.telecorp.optimizedstepup.R;

public class MainFragment extends Fragment {

    TextView textMsg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        textMsg = (TextView) view.findViewById(R.id.mainfragtext);

        Bundle bundle = getArguments();
        if(bundle != null){
            String msg = bundle.getString("KEY_MAIN");
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
