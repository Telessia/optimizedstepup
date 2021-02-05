package fr.telecorp.optimizedstepup.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.List;

import fr.telecorp.optimizedstepup.R;
import fr.telecorp.optimizedstepup.food.Food;
import fr.telecorp.optimizedstepup.food.FoodActivity;
import fr.telecorp.optimizedstepup.database.FoodDatabase;
import fr.telecorp.optimizedstepup.food.FoodList;

public class FoodFragment extends Fragment {

    private final static String DATA_KEY="DATA_KEY";
    private FoodList dataSet;
    private Button btn;
    private Float targetCalories;
    private Float targetProteins;
    private Button button_refresh;
    private TextView calories_text;
    private TextView proteins_text;
    private ImageView healthIcon;


    private Float caloriesSum;
    private Float proteinsSum;
    private Float glucidsSum;
    private Float lipidsSum;
    private Float saturatedSum;
    private Float fibersSum;
    private Float ironSum;
    private Float zincSum;
    private Float magnesiumSum;
    private Float manganeseSum;
    private Float om3Sum;
    private Float om6Sum;
    private Float om9Sum;
    private Float vitaminCount;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, null);
        btn= view.findViewById(R.id.btn_food);
        /**GET BUNDLE**/
       /* Bundle bundle = getArguments();
        if (bundle != null) {
            dataSet = bundle.getParcelable(DATA_KEY);
        }
*/
        caloriesSum = 0f;
        proteinsSum = 0f;
        glucidsSum = 0f;
        lipidsSum = 0f;
        saturatedSum = 0f;
        fibersSum = 0f;
        ironSum = 0f;
        zincSum = 0f;
        magnesiumSum = 0f;
        manganeseSum = 0f;
        om3Sum = 0f;
        om6Sum = 0f;
        om9Sum = 0f;
        vitaminCount = 0f;

        calories_text = view.findViewById(R.id.calories_text);
        proteins_text = view.findViewById(R.id.proteins_text);
        healthIcon = view.findViewById(R.id.health_icon);
        button_refresh = view.findViewById(R.id.button_refresh);
        button_refresh.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view){
                refresh();
            }
        });

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        targetCalories=Float.parseFloat(preferences.getString("storedParamCalories","0"));
        targetProteins=Float.parseFloat(preferences.getString("storedParamProteins","0"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FoodActivity.class);
                //intent.putExtra(DATA_KEY,(Parcelable) dataSet);
                startActivity(intent);
            }
        });


        refresh();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void refresh(){
        caloriesSum = 0f;
        proteinsSum = 0f;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        targetCalories=Float.parseFloat(preferences.getString("storedParamCalories","0"));
        targetProteins=Float.parseFloat(preferences.getString("storedParamProteins","0"));

        List<Food> LF = FoodDatabase.getInstance(getContext()).foodDao().getValuableFood();

        for (int i=0;i<LF.size();i++){
            if(LF.get(i).getUnit().equals("gram")||(LF.get(i).getUnit().equals("ml"))){
            float ratio = (float)LF.get(i).getCurrentValue()/100;
            caloriesSum += ratio*LF.get(i).getCalories();
                proteinsSum += ratio*LF.get(i).getProteins();
                glucidsSum += ratio*LF.get(i).getGlucids();
                lipidsSum += ratio*LF.get(i).getLipids();
                saturatedSum += ratio*LF.get(i).getSaturated();
                fibersSum += ratio*LF.get(i).getFibers();
                ironSum += ratio*LF.get(i).getIron();
                zincSum += ratio*LF.get(i).getZinc();
                magnesiumSum += ratio*LF.get(i).getMagnesium();
                manganeseSum += ratio*LF.get(i).getManganese();
                om3Sum += ratio*LF.get(i).getOm3();
                om6Sum += ratio*LF.get(i).getOm6();
                om9Sum += ratio*LF.get(i).getOm9();
            }
            if(LF.get(i).getUnit().equals("unit")){
            float ratio = LF.get(i).getCurrentValue();
                caloriesSum += ratio*LF.get(i).getCalories();
                proteinsSum += ratio*LF.get(i).getProteins();
                glucidsSum += ratio*LF.get(i).getGlucids();
                lipidsSum += ratio*LF.get(i).getLipids();
                saturatedSum += ratio*LF.get(i).getSaturated();
                fibersSum += ratio*LF.get(i).getFibers();
                ironSum += ratio*LF.get(i).getIron();
                zincSum += ratio*LF.get(i).getZinc();
                magnesiumSum += ratio*LF.get(i).getMagnesium();
                manganeseSum += ratio*LF.get(i).getManganese();
                om3Sum += ratio*LF.get(i).getOm3();
                om6Sum += ratio*LF.get(i).getOm6();
                om9Sum += ratio*LF.get(i).getOm9();
            }
        }
        calories_text.setText(caloriesSum.toString() + " kcal");
        proteins_text.setText(proteinsSum.toString() + " g proteins");

        Resources resources = getContext().getResources();
        int resourceId;

        if (caloriesSum <= targetCalories*0.4f){
            resourceId = resources.getIdentifier("dino_1", "drawable",
                    getContext().getPackageName());
            healthIcon.setImageDrawable(resources.getDrawable(resourceId));
            calories_text.setTextColor(getResources().getColor(R.color.Red));
            proteins_text.setTextColor(getResources().getColor(R.color.Red));

        }else if (caloriesSum < targetCalories*0.9f){
            resourceId = resources.getIdentifier("dino_2", "drawable",
                    getContext().getPackageName());
            healthIcon.setImageDrawable(resources.getDrawable(resourceId));
            calories_text.setTextColor(getResources().getColor(R.color.Orange));
            proteins_text.setTextColor(getResources().getColor(R.color.Orange));

        }else if ((caloriesSum >= targetCalories*0.95f)&&(caloriesSum <= targetCalories*1.05f)){
            resourceId = resources.getIdentifier("dino_4_lightning", "drawable",
                    getContext().getPackageName());
            healthIcon.setImageDrawable(resources.getDrawable(resourceId));
            calories_text.setTextColor(getResources().getColor(R.color.Cyan));
            proteins_text.setTextColor(getResources().getColor(R.color.Cyan));

        }else if ((caloriesSum >= targetCalories*0.90f)&&(caloriesSum <= 1.1f)){
            resourceId = resources.getIdentifier("dino_4_lightning", "drawable",
                    getContext().getPackageName());
            healthIcon.setImageDrawable(resources.getDrawable(resourceId));
            calories_text.setTextColor(getResources().getColor(R.color.LimeGreen));
            proteins_text.setTextColor(getResources().getColor(R.color.LimeGreen));

        }else if (caloriesSum > targetCalories*1.1f){
            resourceId = resources.getIdentifier("dino_5", "drawable",
                    getContext().getPackageName());
            healthIcon.setImageDrawable(resources.getDrawable(resourceId));
            calories_text.setTextColor(getResources().getColor(R.color.Fuchsia));
            proteins_text.setTextColor(getResources().getColor(R.color.Fuchsia));

        }
    }

}

