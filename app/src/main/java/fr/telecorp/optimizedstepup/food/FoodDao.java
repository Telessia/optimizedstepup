package fr.telecorp.optimizedstepup.food;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

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
}
