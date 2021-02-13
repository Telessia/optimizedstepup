package fr.telecorp.optimizedstepup.services;

import android.app.Service;
import android.content.Context;

import fr.telecorp.optimizedstepup.database.FoodDatabase;

public class FoodService {

    private Context context;

    public FoodService(Context ctx){
        context=ctx;
    }

    public void reset(){
        FoodDatabase.getInstance(context).foodDao().resetCurrentValues();
    }
}
