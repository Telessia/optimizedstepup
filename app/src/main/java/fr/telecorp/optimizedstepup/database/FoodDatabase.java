package fr.telecorp.optimizedstepup.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.telecorp.optimizedstepup.food.Food;

@Database(entities = {Food.class}, version = 2)
public abstract class FoodDatabase<INSTANCE> extends RoomDatabase {
    public abstract FoodDao foodDao();
    private static volatile FoodDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FoodDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoodDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodDatabase.class, "food_database").addMigrations(MIGRATION_1_2).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) { //Change "currentValue" type from FLOAT TO INTEGER
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE food_new (id INTEGER NOT NULL, NAME TEXT, TYPE TEXT, UNIT TEXT, CALORIES REAL NOT NULL, PROTEINS REAL NOT NULL, GLUCIDS REAL NOT NULL," +
                    "LIPIDS REAL NOT NULL, SATURATED REAL NOT NULL, FIBERS REAL NOT NULL, IRON REAL NOT NULL, ZINC REAL NOT NULL, MAGNESIUM REAL NOT NULL, MANGANESE REAL NOT NULL," +
                    "OM3 REAL NOT NULL, OM6 REAL NOT NULL, OM9 REAL NOT NULL, MAINVITAMIN TEXT, CURRENTVALUE INTEGER NOT NULL, PRIMARY KEY(ID))");

            // Copy the data
            database.execSQL(
                    "INSERT INTO food_new (id, NAME, TYPE, UNIT, CALORIES, PROTEINS, GLUCIDS, LIPIDS, SATURATED, FIBERS, IRON, ZINC, MAGNESIUM, MANGANESE," +
                            "OM3, OM6, OM9, MAINVITAMIN, CURRENTVALUE) SELECT id, NAME, TYPE, UNIT, CALORIES, PROTEINS, GLUCIDS, LIPIDS, SATURATED" +
                            ", FIBERS, IRON, ZINC, MAGNESIUM, MANGANESE,\" +\n" +
                            "                            \"OM3, OM6, OM9, MAINVITAMIN, CAST(CURRENTVALUE AS INTEGER) FROM food");
            // Remove the old table
            database.execSQL("DROP TABLE food");
            // Change the table name to the correct one
            database.execSQL("ALTER TABLE food_new RENAME TO food");
        }
    };
}




