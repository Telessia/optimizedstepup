package fr.telecorp.optimizedstepup.food;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import fr.telecorp.optimizedstepup.R;

public class FoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentContainerView fragFrame;
    private FoodList foodDataset;
    private RecyclerView recyclerView;
    private FoodAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private final static String DATA_KEY = "DATA_KEY";
    private final static String TAG_1 = "ALL_FOOD_FRAGMENT";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        if (getIntent().getExtras() != null){
            foodDataset = getIntent().getExtras().getParcelable(DATA_KEY);
        }

        toolbar=findViewById(R.id.food_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.food_drawer);
        navigationView=findViewById(R.id.food_nav);
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
        mAdapter = new FoodAdapter(foodDataset);
        recyclerView.setAdapter(mAdapter);


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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // search goes here !!
                mAdapter.getFilter().filter(newText);
                return false;
            }
            });
        return true;
    }

    public boolean loadFoodValues(){

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
        switch (menuItem.getItemId()) {

            case R.id.redmeat:
                mAdapter.getFilter().filter("redmeat");
                searchView.setClickable(false);
                break;
            case R.id.whitemeat:
                mAdapter.getFilter().filter("whitemeat");
                searchView.setClickable(false);
                break;
            case R.id.cereals:
                mAdapter.getFilter().filter("cereals");
                searchView.setClickable(false);
                break;
            case R.id.dairy:
                mAdapter.getFilter().filter("dairy");
                searchView.setClickable(false);
                break;
            case R.id.eggs:
                mAdapter.getFilter().filter("eggs");
                searchView.setClickable(false);
                break;
            case R.id.nuts:
                mAdapter.getFilter().filter("nuts");
                searchView.setClickable(false);
                break;
            case R.id.seafood:
                mAdapter.getFilter().filter("seafood");
                searchView.setClickable(false);
                break;
            case R.id.fruits:
                mAdapter.getFilter().filter("fruits");
                searchView.setClickable(false);
                break;
            case R.id.vegetables:
                mAdapter.getFilter().filter("vegetables");
                searchView.setClickable(false);
                break;
            case R.id.supplements:
                mAdapter.getFilter().filter("supplements");
                searchView.setClickable(false);
                break;
            case R.id.meal:
                mAdapter.getFilter().filter("meal");
                searchView.setClickable(false);
                break;

        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
