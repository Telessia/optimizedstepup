package fr.telecorp.optimizedstepup.food;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class Food implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "NAME")
    private String name = "empty";
    @ColumnInfo(name = "TYPE")
    private String type = "empty";
    @ColumnInfo(name = "UNIT")
    private String unit = "empty";
    @ColumnInfo(name = "CALORIES")
    private float calories = 0;
    @ColumnInfo(name = "PROTEINS")
    private float proteins = 0;
    @ColumnInfo(name = "GLUCIDS")
    private float glucids = 0;
    @ColumnInfo(name = "LIPIDS")
    private float lipids = 0;
    @ColumnInfo(name = "SATURATED")
    private float saturated = 0;
    @ColumnInfo(name = "FIBERS")
    private float fibers = 0;
    @ColumnInfo(name = "IRON")
    private float iron = 0;
    @ColumnInfo(name = "ZINC")
    private float zinc = 0;
    @ColumnInfo(name = "MAGNESIUM")
    private float magnesium = 0;
    @ColumnInfo(name="MANGANESE")
    private float manganese = 0;
    @ColumnInfo(name = "OM3")
    private float om3 = 0;
    @ColumnInfo(name = "OM6")
    private float om6 = 0;
    @ColumnInfo(name = "OM9")
    private float om9 = 0;
    @ColumnInfo(name = "MAINVITAMIN")
    private String mainVitamin = "empty";
    @ColumnInfo(name = "CURRENTVALUE")
    private int currentValue = 0;

    @Ignore
    public Food(){}

    @Ignore
    public Food(String name, String type, String unit, float calories) {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.calories = calories;
    }
    @Ignore
    public Food(String name, String type, String unit, float calories, float proteins, float glucids, float lipids, float saturated, float fibers, float iron,
                float zinc, float magnesium, float manganese, float om3, float om6, float om9) {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.calories = calories;
        this.proteins = proteins;
        this.glucids = glucids;
        this.lipids = lipids;
        this.fibers = fibers;
    }

    /* PARCEL PART FOR INTENTS/BUNDLES START*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(unit);
        parcel.writeFloat(calories);
        parcel.writeFloat(proteins);
        parcel.writeFloat(glucids);
        parcel.writeFloat(lipids);
        parcel.writeFloat(fibers);
        parcel.writeFloat(iron);
        parcel.writeFloat(zinc);
        parcel.writeFloat(magnesium);
        parcel.writeFloat(om3);
        parcel.writeFloat(om6);
        parcel.writeFloat(om9);
        parcel.writeString(mainVitamin);
        parcel.writeInt(currentValue);
    }

    private Food(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.type = in.readString();
        this.unit = in.readString();
        this.proteins = in.readFloat();
        this.glucids = in.readFloat();
        this.lipids = in.readFloat();
        this.fibers = in.readFloat();
        this.iron = in.readFloat();
        this.zinc = in.readFloat();
        this.magnesium = in.readFloat();
        this.om3 = in.readFloat();
        this.om6 = in.readFloat();
        this.om9 = in.readFloat();
        this.mainVitamin = in.readString();
        this.currentValue = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    /* PARCEL PART FOR INTENTS/BUNDLES END*/
}