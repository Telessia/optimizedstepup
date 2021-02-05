package fr.telecorp.optimizedstepup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.telecorp.optimizedstepup.food.FoodList;
import fr.telecorp.optimizedstepup.fragments.ExerciseFragment;
import fr.telecorp.optimizedstepup.fragments.FoodFragment;
import fr.telecorp.optimizedstepup.fragments.MainFragment;
import fr.telecorp.optimizedstepup.fragments.ParamsFragment;
import fr.telecorp.optimizedstepup.fragments.ScheduleFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    final static String TAG_1 = "FRAGMENT_MAIN";
    final static String TAG_2 = "FRAGMENT_FOOD";
    final static String TAG_3 = "FRAGMENT_EXERCISE";
    final static String TAG_4 = "FRAGMENT_SCHEDULE";
    final static String TAG_5 = "FRAGMENT_PARAMS";
    String current_TAG;
    final static String KEY_MAIN = "KEY_MAIN";
    final static String KEY_FOOD = "KEY_FOOD";
    final static String KEY_EXERCISE = "KEY_EXERCISE";
    final static String KEY_SCHEDULE = "KEY_SCHEDULE";
    final static String KEY_PARAMS = "KEY_PARAMS";
    final static String DATA_KEY = "DATA_KEY";

    private MainFragment mainFragment;
    private FoodFragment foodFragment;
    private ExerciseFragment exeFragment;
    private ScheduleFragment scheFragment;
    private ParamsFragment paramsFragment;
    private FragmentContainerView fragFrame;
    private BottomNavigationView bottomNavigationView;
    private FoodList dataLoaded;

    private Toolbar toolbar;
    private Button params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.mainmenu).setChecked(true);
        fragFrame = findViewById(R.id.main_frag_container);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MAIN, TAG_1);
        current_TAG = TAG_1;
        mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(fragFrame.getId(), mainFragment, TAG_1).commit();

        params = findViewById(R.id.param_button);
/*
            params.setOnClickListener(new View.OnClickListener(){
               @Override
                public void onClick(View view){
                   if(current_TAG != TAG_5) {
                       Bundle bundle = new Bundle();
                       bundle.putString(KEY_PARAMS, TAG_5);
                       current_TAG = TAG_5;
                       bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
                       paramsFragment = new ParamsFragment();
                       paramsFragment.setArguments(bundle);
                       getSupportFragmentManager().beginTransaction().replace(fragFrame.getId(), paramsFragment, TAG_5).commit();
                }
            }
        });*/
    }

                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mainmenu:
                                if (current_TAG != TAG_1) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString(KEY_MAIN, TAG_1);
                                    current_TAG = TAG_1;
                                    bottomNavigationView.getMenu().findItem(R.id.mainmenu).setChecked(true);
                                    mainFragment = new MainFragment();
                                    mainFragment.setArguments(bundle);
                                    getSupportFragmentManager().beginTransaction().replace(fragFrame.getId(), mainFragment, TAG_1).commit();
                                }
                                break;
                            case R.id.foodmenu:
                                if (current_TAG != TAG_2) {
                                   // Bundle bundle = new Bundle();
                                   // bundle.putParcelable("DATA_KEY", dataLoaded);
                                    current_TAG = TAG_2;
                                    bottomNavigationView.getMenu().findItem(R.id.foodmenu).setChecked(true);
                                    foodFragment = new FoodFragment();
                                    //foodFragment.setArguments(bundle);
                                    getSupportFragmentManager().beginTransaction().replace(fragFrame.getId(), foodFragment, TAG_2).commit();
                                }
                                break;
                            case R.id.exemenu:
                                if (current_TAG != TAG_3) {

                                }
                                break;
                            case R.id.schemenu:
                                if (current_TAG != TAG_4) {

                                }
                                break;
                        }
                        return true;
        }

        public boolean loadHistory(){

        return true;
        }

        public FoodList getDataSet(){
        return dataLoaded;
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_top, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.param_button:
                if(current_TAG != TAG_5) {
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_PARAMS, TAG_5);
                    current_TAG = TAG_5;
                    bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
                    paramsFragment = new ParamsFragment();
                    paramsFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(fragFrame.getId(), paramsFragment, TAG_5).commit();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
