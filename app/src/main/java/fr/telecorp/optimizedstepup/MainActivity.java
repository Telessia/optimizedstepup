package fr.telecorp.optimizedstepup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import fr.telecorp.optimizedstepup.food.Food;
import fr.telecorp.optimizedstepup.food.FoodDao;
import fr.telecorp.optimizedstepup.food.FoodDatabase;
import fr.telecorp.optimizedstepup.food.FoodList;
import fr.telecorp.optimizedstepup.fragments.ExerciseFragment;
import fr.telecorp.optimizedstepup.fragments.FoodFragment;
import fr.telecorp.optimizedstepup.fragments.MainFragment;
import fr.telecorp.optimizedstepup.fragments.ScheduleFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    final static String TAG_1 = "FRAGMENT_MAIN";
    final static String TAG_2 = "FRAGMENT_FOOD";
    final static String TAG_3 = "FRAGMENT_EXERCISE";
    final static String TAG_4 = "FRAGMENT_SCHEDULE";
    String current_TAG;
    final static String KEY_MAIN = "KEY_MAIN";
    final static String KEY_FOOD = "KEY_FOOD";
    final static String KEY_EXERCISE = "KEY_EXERCISE";
    final static String KEY_SCHEDULE = "KEY_SCHEDULE";
    final static String DATA_KEY = "DATA_KEY";

    private MainFragment mainFragment;
    private FoodFragment foodFragment;
    private ExerciseFragment exeFragment;
    private ScheduleFragment scheFragment;
    private FragmentContainerView fragFrame;
    private BottomNavigationView bottomNavigationView;
    private FoodList dataLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        writeFile();
        dataLoaded=loadFile();
        */

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
/*
    public FoodList loadFile(){
        File folder = getFilesDir();
        Log.i("PATH", folder.toString());
        FoodList loadedDatas= new FoodList();
        File[] arrayFiles = folder.listFiles();
        if (arrayFiles.length == 0) {
            return null;
        }
        try {
        FileInputStream fis = null;
            for (File arrayFile : arrayFiles) {
                if (arrayFile.getName().contains("foodfile")) {
                    fis = openFileInput(arrayFile.getName());
                    byte[] buffer = new byte[1024];
                    StringBuilder content = new StringBuilder();
                    while ((fis.read(buffer)) != -1) {
                        content.append(new String(buffer));
                    }
                    String Separator1 = "::";
                    String Separator2 = ":";
                    String[] splited;
                    splited = content.toString().split(Separator1);
                    for (int i = 0; i< splited.length-1;i++){
                        String[] tempSplited = splited[i].split(Separator2);
                        Food tempFood = new Food(-1, tempSplited[0], tempSplited[1],
                                tempSplited[2], Float.parseFloat(tempSplited[3]), Float.parseFloat(tempSplited[4]),
                                Float.parseFloat(tempSplited[5]), Float.parseFloat(tempSplited[6]),
                                Float.parseFloat(tempSplited[7]), Float.parseFloat(tempSplited[8]),
                                Float.parseFloat(tempSplited[9]), Float.parseFloat(tempSplited[10]),
                                Float.parseFloat(tempSplited[11]), Float.parseFloat(tempSplited[12]),
                                Float.parseFloat(tempSplited[13]), tempSplited[14]);
                        tempFood.setId(getResources().getIdentifier(tempSplited[1],
                                "drawable", getPackageName())); //SET the resID of the associated drawable for the ViewHolder
                        loadedDatas.add(tempFood);
                    }
                    fis.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedDatas;
    }

        public boolean writeFile(){

            FileOutputStream fos = null;
            try {
                for(int i=0;i<1;i++) {
                    fos = openFileOutput("foodfile" + ".bin", Context.MODE_PRIVATE);
                    fos.write("Tuna".getBytes());
                    fos.write(":".getBytes());
                    fos.write("sea_food".getBytes());
                    fos.write(":".getBytes());
                    fos.write("100g".getBytes());
                    fos.write(":".getBytes());
                    fos.write("116".getBytes());
                    fos.write(":".getBytes());
                    fos.write("25".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("1".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("1.53".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0.77".getBytes());
                    fos.write(":".getBytes());
                    fos.write("27".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("B12".getBytes());
                    fos.write("::".getBytes());
                    fos.write("ChickenEggs".getBytes());
                    fos.write(":".getBytes());
                    fos.write("eggs".getBytes());
                    fos.write(":".getBytes());
                    fos.write("unit".getBytes());
                    fos.write(":".getBytes());
                    fos.write("85".getBytes());
                    fos.write(":".getBytes());
                    fos.write("7".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("1".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("1.53".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0.77".getBytes());
                    fos.write(":".getBytes());
                    fos.write("27".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("B12".getBytes());
                    fos.write("::".getBytes());
                    fos.write("Almonds".getBytes());
                    fos.write(":".getBytes());
                    fos.write("nuts".getBytes());
                    fos.write(":".getBytes());
                    fos.write("100g".getBytes());
                    fos.write(":".getBytes());
                    fos.write("576".getBytes());
                    fos.write(":".getBytes());
                    fos.write("21".getBytes());
                    fos.write(":".getBytes());
                    fos.write("21".getBytes());
                    fos.write(":".getBytes());
                    fos.write("49".getBytes());
                    fos.write(":".getBytes());
                    fos.write("12".getBytes());
                    fos.write(":".getBytes());
                    fos.write("3.72".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0.77".getBytes());
                    fos.write(":".getBytes());
                    fos.write("27".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("0".getBytes());
                    fos.write(":".getBytes());
                    fos.write("A".getBytes());
                    fos.write("::".getBytes());
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
*/
        public boolean loadHistory(){

        return true;
        }

        public FoodList getDataSet(){
        return dataLoaded;
        }

}
