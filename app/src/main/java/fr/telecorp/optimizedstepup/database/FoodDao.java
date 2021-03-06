package fr.telecorp.optimizedstepup.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.telecorp.optimizedstepup.food.Food;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM food")
    List <Food> getAll();

   // @Query("SELECT * FROM food WHERE type IN (:foodType)")
    // FoodList loadAllByTypes(String foodType);

    @Insert
    void insert(Food food);

    @Delete
    void delete(Food food);

    @Query("DELETE FROM food")
    public void nukeTable();

    @Update
    void updateFood(Food food);

    @Update
    public int updateFoods(List<Food> foods);

    @Query("SELECT * FROM food WHERE currentvalue > 0")
    List<Food> getValuableFood();

    @Query("UPDATE food SET currentvalue = 0")
    void resetCurrentValues ();
}
