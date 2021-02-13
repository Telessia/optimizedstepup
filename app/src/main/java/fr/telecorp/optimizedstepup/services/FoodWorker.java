package fr.telecorp.optimizedstepup.services;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import fr.telecorp.optimizedstepup.database.FoodDatabase;

public class FoodWorker extends Worker {

    public FoodWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {

        // Do the work here--in this case, upload the images.
        FoodDatabase.getInstance(getApplicationContext()).foodDao().resetCurrentValues();

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }
}
