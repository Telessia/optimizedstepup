package fr.telecorp.optimizedstepup.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.telecorp.optimizedstepup.R;

public class AddFoodActivity extends AppCompatActivity {

    private int requestcode;
    private Button confirmButton;
    private Button exitButton;
    private Button typeButton;
    private Button unitButton;
    private Button vitaminButton;
    private RadioGroup radioGroupType;
    private RadioGroup radioGroupUnit;
    private RadioGroup radioGroupVitamin;
    private RadioButton radioButtonType;
    private RadioButton radioButtonUnit;
    private RadioButton radioButtonVitamin;

    private EditText name;
    private String type;
    private String unit;
    private EditText calories;
    private EditText proteins;
    private EditText glucids;
    private EditText lipids;
    private EditText saturated;
    private EditText fibers;
    private EditText iron;
    private EditText zinc;
    private EditText magnesium;
    private EditText manganese;
    private EditText om3;
    private EditText om6;
    private EditText om9;
    private String mainVitamin;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Intent intentIn = getIntent();
        if (intentIn != null) {
            requestcode = intentIn.getIntExtra("requestcode", -1);
        }

        confirmButton = findViewById(R.id.add_confirm);
        exitButton = findViewById(R.id.add_exit);
        typeButton = findViewById(R.id.add_type);
        unitButton = findViewById(R.id.add_unit);
        vitaminButton = findViewById(R.id.add_mainvitamin);
        radioGroupType = findViewById(R.id.radioGroupType);
        radioGroupUnit = findViewById(R.id.radioGroupUnit);
        radioGroupVitamin  = findViewById(R.id.radioGroupVitamin);

        /* EDIT TEXT */

        name = findViewById(R.id.add_name);
        calories= findViewById(R.id.add_calories);
        proteins = findViewById(R.id.add_proteins);
        glucids = findViewById(R.id.add_glucids);
        lipids = findViewById(R.id.add_lipids);
        saturated = findViewById(R.id.add_saturated);
        fibers = findViewById(R.id.add_fibers);
        iron = findViewById(R.id.add_iron);
        zinc = findViewById(R.id.add_zinc);
        magnesium = findViewById(R.id.add_magnesium);
        manganese = findViewById(R.id.add_manganese);
        om3 = findViewById(R.id.add_om3);
        om6 = findViewById(R.id.add_om6);
        om9 = findViewById(R.id.add_om9);

        imageView = findViewById(R.id.add_image);

        /*   CONFIRM  */
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioButtonType = findViewById(radioGroupType.getCheckedRadioButtonId());
                radioButtonUnit = findViewById(radioGroupUnit.getCheckedRadioButtonId());
                radioButtonVitamin = findViewById(radioGroupVitamin.getCheckedRadioButtonId());


                if((name.getText().length()<0)&&(Float.parseFloat(calories.getText().toString())<=0)){
                    Toast toast = Toast.makeText(getApplicationContext(),"Name, Type, Unit and Calories values are required", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {

                    Food f = new Food(name.getText().toString(), type, unit, Float.parseFloat(calories.getText().toString()));

                    f.setProteins(Float.parseFloat(proteins.getText().toString()));
                    f.setGlucids(Float.parseFloat(glucids.getText().toString()));
                    f.setLipids(Float.parseFloat(lipids.getText().toString()));
                    f.setSaturated(Float.parseFloat(saturated.getText().toString()));
                    f.setFibers(Float.parseFloat(fibers.getText().toString()));
                    f.setIron(Float.parseFloat(iron.getText().toString()));
                    f.setZinc(Float.parseFloat(zinc.getText().toString()));
                    f.setMagnesium(Float.parseFloat(magnesium.getText().toString()));
                    f.setManganese(Float.parseFloat(manganese.getText().toString()));
                    f.setOm3(Float.parseFloat(om3.getText().toString()));
                    f.setOm6(Float.parseFloat(om6.getText().toString()));
                    f.setOm9(Float.parseFloat(om9.getText().toString()));

                    Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                    intent.putExtra("food_datas", f);
                    setResult(Activity.RESULT_OK, intent);
                }
            }
        });

        /*   CONFIRM  */

        /*   EXIT  */
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             }
        });

        /*   EXIT  */

        typeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroupType.getVisibility()==(View.GONE)) {
                    radioGroupType.setVisibility(View.VISIBLE);
                }
                else{
                    radioGroupType.setVisibility(View.GONE);
                    switch (radioGroupType.getCheckedRadioButtonId()) {

                        case R.id.radioButton_redMeat:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.red_meat_icon));

                        case R.id.radioButton_whiteMeat:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.white_meat_icon));

                        case R.id.radioButton_seaFood:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.sea_food_icon));

                        case R.id.radioButton_vegetables:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.vegetables_icon));

                        case R.id.radioButton_eggs:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.eggs_icon));

                        case R.id.radioButton_dairy:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dairy_icon));

                        case R.id.radioButton_nuts:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.nuts_icon));

                        case R.id.radioButton_cereals:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.cereals_icon));

                        case R.id.radioButton_fruits:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.fruits_icon));

                        case R.id.radioButton_supplements:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.supplements_icon));

                        case R.id.radioButton_meal:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.meal_icon));
                    }
                }
            }
        });

        unitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroupUnit.getVisibility()==(View.GONE)) {
                    radioGroupUnit.setVisibility(View.VISIBLE);
                }
                else{
                    radioGroupUnit.setVisibility(View.GONE);
                }
            }
        });

        vitaminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(radioGroupVitamin.getVisibility()==(View.GONE)) {
                radioGroupVitamin.setVisibility(View.VISIBLE);
            }
            else{
                radioGroupVitamin.setVisibility(View.GONE);
            }
            }
        });

    }
}

