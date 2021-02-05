package fr.telecorp.optimizedstepup.food;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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
import fr.telecorp.optimizedstepup.database.FoodDatabase;

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
        radioGroupUnit.check(R.id.radioButton_gram);
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

        radioGroupType.check(R.id.cereals);
        type = "cereal_icon";
        Resources resources = getApplicationContext().getResources();
        int resourceId;
        resourceId = resources.getIdentifier(type, "drawable",
                getApplicationContext().getPackageName());
        imageView.setImageDrawable(resources.getDrawable(resourceId));

        /*   CONFIRM  */
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioButtonType = findViewById(radioGroupType.getCheckedRadioButtonId());
                radioButtonUnit = findViewById(radioGroupUnit.getCheckedRadioButtonId());
                radioButtonVitamin = findViewById(radioGroupVitamin.getCheckedRadioButtonId());

                    if (name.getText().toString().length() <= 0 || calories.getText().toString().length() <= 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Name, Type, Unit and Calories values are required", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        String type_for_class;
                        String[] typeS = type.split("_");
                        if(typeS.length >= 3) {
                            typeS[0] = typeS[0].substring(0, 1).toUpperCase() + typeS[0].substring(1).toLowerCase();
                            type_for_class = typeS[0] + " " + typeS[1];
                        } else{
                            typeS[0] = typeS[0].substring(0, 1).toUpperCase() + typeS[0].substring(1).toLowerCase();
                            type_for_class = typeS[0];
                        }
                        Food f = new Food(name.getText().toString(), type_for_class, unit, Float.parseFloat(calories.getText().toString()));
                        if (proteins.getText().toString().length() <= 0) {
                            f.setProteins(0);
                        } else {
                            f.setProteins(Float.parseFloat(proteins.getText().toString()));
                        }
                        if (glucids.getText().toString().length() <= 0) {
                            f.setGlucids(0);
                        } else {
                            f.setGlucids(Float.parseFloat(glucids.getText().toString()));
                        }
                        if (lipids.getText().toString().length() <= 0) {
                            f.setLipids(0);
                        } else {
                            f.setLipids(Float.parseFloat(lipids.getText().toString()));
                        }
                        if (saturated.getText().toString().length() <= 0) {
                            f.setSaturated(0);
                        } else {
                            f.setSaturated(Float.parseFloat(saturated.getText().toString()));
                        }
                        if (fibers.getText().toString().length() <= 0) {
                            f.setFibers(0);
                        } else {
                            f.setFibers(Float.parseFloat(fibers.getText().toString()));
                        }
                        if (iron.getText().toString().length() <= 0) {
                            f.setIron(0);
                        } else {
                            f.setIron(Float.parseFloat(iron.getText().toString()));
                        }
                        if (zinc.getText().toString().length() <= 0) {
                            f.setZinc(0);
                        } else {
                            f.setZinc(Float.parseFloat(zinc.getText().toString()));
                        }
                        if (magnesium.getText().toString().length() <= 0) {
                            f.setMagnesium(0);
                        } else {
                            f.setMagnesium(Float.parseFloat(magnesium.getText().toString()));
                        }
                        if (manganese.getText().toString().length() <= 0) {
                            f.setManganese(0);
                        } else {
                            f.setManganese(Float.parseFloat(manganese.getText().toString()));
                        }
                        if (om3.getText().toString().length() <= 0) {
                            f.setOm3(0);
                        } else {
                            f.setOm3(Float.parseFloat(om3.getText().toString()));
                        }
                        if (om6.getText().toString().length() <= 0) {
                            f.setOm6(0);
                        } else {
                            f.setOm6(Float.parseFloat(om6.getText().toString()));
                        }
                        if (om9.getText().toString().length() <= 0) {
                            f.setOm9(0);
                        } else {
                            f.setOm9(Float.parseFloat(om9.getText().toString()));
                        }

                        f.setCurrentValue(0);

                        FoodDatabase.getInstance(getApplicationContext()).foodDao().insert(f);

                    Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                    intent.putExtra("food_datas", f);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

        /*   CONFIRM  */

        /*   EXIT  */
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                setResult(Activity.RESULT_OK);
                finish();
             }
        });

        /*   EXIT  */

        typeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              if (radioGroupType.getVisibility() == (View.GONE)) {
                                                  radioGroupType.setVisibility(View.VISIBLE);
                                              }
                                              else{
                                                  radioGroupType.setVisibility(View.GONE);
                                              }
                                          }
                                      });


            radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedRadioButtonId) {
                    radioGroupType.setVisibility(View.GONE);
                    Resources resources = getApplicationContext().getResources();
                    int resourceId;

                    if (checkedRadioButtonId == R.id.radioButton_redMeat) {
                        type = "red_meat_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_whiteMeat) {
                        type = "white_meat_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_seaFood) {
                        type = "sea_food_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_vegetables) {
                        type = "vegetable_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_eggs) {
                        type = "egg_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_dairy) {
                        type = "dairy_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_nuts) {
                        type = "nut_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_cereals) {
                        type = "cereal_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_fruits) {
                        type = "fruit_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_supplements) {
                        type = "supplement_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
                    } else if (checkedRadioButtonId == R.id.radioButton_meal) {
                        type = "meal_icon";
                        resourceId = resources.getIdentifier(type, "drawable",
                                getApplicationContext().getPackageName());
                        imageView.setImageDrawable(resources.getDrawable(resourceId));
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
                    int checkedRadioButtonId = radioGroupUnit.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.radioButton_unit){
                        unit = "unit";
                        unitButton.setText("unit");
                    }else if (checkedRadioButtonId == R.id.radioButton_gram){
                        unit = "gram";
                        unitButton.setText("gram");
                    }else if (checkedRadioButtonId == R.id.radioButton_ml){
                        unit = "ml";
                        unitButton.setText("ml");
                    }
                }
            }
        });

        vitaminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroupVitamin.getVisibility() == (View.GONE)) {
                    radioGroupVitamin.setVisibility(View.VISIBLE);
                } else {
                    radioGroupVitamin.setVisibility(View.GONE);
                }
            }
        });
        radioGroupVitamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedRadioButtonId)
            {
                radioButtonVitamin.setVisibility(View.GONE);
                if (checkedRadioButtonId == R.id.radioButton_vitamin_a) {
                    mainVitamin = "A";
                    vitaminButton.setText("vit A");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b1) {
                    mainVitamin = "B1";
                    vitaminButton.setText("vit B1");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b2) {
                    mainVitamin = "B2";
                    vitaminButton.setText("vit B2");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b3) {
                    mainVitamin = "B3";
                    vitaminButton.setText("vit B3");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b5) {
                    mainVitamin = "B5";
                    vitaminButton.setText("vit B5");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b6) {
                    mainVitamin = "B6";
                    vitaminButton.setText("vit B6");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b8) {
                    mainVitamin = "B8";
                    vitaminButton.setText("vit B8");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b9) {
                    mainVitamin = "B9";
                    vitaminButton.setText("vit B9");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_b12) {
                    mainVitamin = "B12";
                    vitaminButton.setText("vit B12");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_c) {
                    mainVitamin = "C";
                    vitaminButton.setText("vit C");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_d) {
                    mainVitamin = "D";
                    vitaminButton.setText("vit D");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_e) {
                    mainVitamin = "E";
                    vitaminButton.setText("vit E");
                } else if (checkedRadioButtonId == R.id.radioButton__vitamin_k) {
                    mainVitamin = "K";
                    vitaminButton.setText("vit k");
                }
            }
    });

}
}

