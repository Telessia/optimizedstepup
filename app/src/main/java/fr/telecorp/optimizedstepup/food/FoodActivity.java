package fr.telecorp.optimizedstepup.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import fr.telecorp.optimizedstepup.R;
import fr.telecorp.optimizedstepup.food.fragments.AddFoodFragment;

public class FoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentContainerView fragFrame;
    private FoodList foodDataset;
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private LinearLayoutManager layoutManager;
    private final static String DATA_KEY = "DATA_KEY";
    private final static String TAG_1 = "ALL_FOOD_FRAGMENT";
    private final static int ADD_KEY = 1;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        /*if (getIntent().getExtras() != null){
            foodDataset = getIntent().getExtras().getParcelable(DATA_KEY);
        }

         */

        /* DATABASE THREAD */
        //  new Thread(new Runnable() {
        //    public void run() {

        FoodDatabase db = Room.databaseBuilder(getApplicationContext(),
                FoodDatabase.class, "food_database").allowMainThreadQueries().build();
        FoodDao foodDao = db.foodDao();
        foodDao.nukeTable();
        Food f = new Food("Tuna", "Sea Food", "gram", 100);
        foodDao.insert(f);
        List<Food> lf = foodDao.getAll();
        foodDataset = FoodList.toFoodList(lf);
        //  }
        //}).start();
        /* DATABASE THREAD */

        toolbar = findViewById(R.id.food_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.food_drawer);
        navigationView = findViewById(R.id.food_nav);
       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_food_nav,
                R.string.close_food_nav
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        recyclerView = findViewById(R.id.food_recycler_view);// use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        foodAdapter = new FoodAdapter(foodDataset, this);
        recyclerView.setAdapter(foodAdapter);

        /*CONFIRM*/
        Button confirmButton = findViewById(R.id.confirm_food);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDatabase db = Room.databaseBuilder(getApplicationContext(),
                        FoodDatabase.class, "food_database").allowMainThreadQueries().build();
                FoodDao foodDao = db.foodDao();

               /* for(int i=0;i<foodDataset;)
                foodDao.insert(f);
                TODO
                */
                db.close();


                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            try {
                Food f = data.getParcelableExtra("food_datas");
                foodDataset.add(f);
                foodAdapter.notifyDataSetChanged();
            }catch (NullPointerException e){
                //NOTHING
            }
            //save(this, remindlist);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.food_search, menu);
       // MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                foodAdapter.getFilterByName().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // search goes here !!
                foodAdapter.getFilterByName().filter(newText);
                return false;
            }
            });
        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.food_all) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter(null);
            searchView.setEnabled(true);
        } else if (itemId == R.id.redmeat) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Red meat");
            searchView.setEnabled(false);
        } else if (itemId == R.id.whitemeat) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("White meat");
            searchView.setEnabled(false);
        } else if (itemId == R.id.cereals) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Cereals");
            searchView.setEnabled(false);
        } else if (itemId == R.id.dairy) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Dairy");
            searchView.setEnabled(false);
        } else if (itemId == R.id.eggs) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Eggs");
            searchView.setEnabled(false);
        } else if (itemId == R.id.nuts) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Nuts");
            searchView.setEnabled(false);
        } else if (itemId == R.id.seafood) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Sea food");
            searchView.setEnabled(false);
        } else if (itemId == R.id.fruits) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Fruits");
            searchView.setEnabled(false);
        } else if (itemId == R.id.vegetables) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Vegetables");
            searchView.setEnabled(false);
        } else if (itemId == R.id.supplements) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Supplements");
            searchView.setEnabled(false);
        } else if (itemId == R.id.meal) {
            foodAdapter.restoreFilter();
            foodAdapter.getFilterByType().filter("Meal");
            searchView.setEnabled(false);
        } else if (itemId == R.id.add_food) {
            Intent intent = new Intent(this, AddFoodActivity.class);
            startActivityForResult(intent, ADD_KEY);
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
