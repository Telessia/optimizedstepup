/*package fr.telecorp.optimizedstepup.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.telecorp.optimizedstepup.R;

public class AddFoodActivity extends AppCompatActivity {

    private int requestcode;
    private Button confirmbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Intent intentIn = getIntent();
        if (intentIn != null) {
            requestcode = intentIn.getIntExtra("requestcode", -1);
        }

        confirmbutton = findViewById(R.id.savebutton);
        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Food f = new Food("1", "1500", title.getText().toString(), notes.getText().toString());
                Intent intent = new Intent(this, ReminderActivity.class);
                intent.putExtra("food_datas", food);
                setResult(Activity.RESULT_OK, intent);
            }
        });
    }

}
*/