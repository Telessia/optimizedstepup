package fr.telecorp.optimizedstepup.food;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Food implements Parcelable {

    private int id = -1;
    private String name = "empty";
    private String type = "empty";
    private String unit = "empty";
    private float calories = 0;
    private float proteins = 0;
    private float glucids = 0;
    private float lipids = 0;
    private float fibers = 0;
    private float iron = 0;
    private float zinc = 0;
    private float magnesium = 0;
    private float om3 = 0;
    private float om6 = 0;
    private float om9 = 0;
    private String mainVitamin = "empty";
    private float currentValue = 0;

    public Food() {
    }

    public Food(int id, String name, String type, String unit, float calories, float proteins) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.calories = calories;
        this.proteins = proteins;
    }

    public Food(int id, String name, String type, String unit, float calories, float proteins, float glucids, float lipids, float fibers) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.calories = calories;
        this.proteins = proteins;
        this.glucids = glucids;
        this.lipids = lipids;
        this.fibers = fibers;
    }

    public Food(int id, String name, String type, String unit, float calories, float proteins, float glucids,
                float lipids, float fibers, float iron, float zinc, float magnesium, float om3,
                float om6, float om9, String mainVitamin) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.calories = calories;
        this.proteins = proteins;
        this.glucids = glucids;
        this.lipids = lipids;
        this.fibers = fibers;
        this.iron = iron;
        this.zinc = zinc;
        this.magnesium = magnesium;
        this.om3 = om3;
        this.om6 = om6;
        this.om9 = om9;
        this.mainVitamin = mainVitamin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getGlucids() {
        return glucids;
    }

    public void setGlucids(float glucids) {
        this.glucids = glucids;
    }

    public float getLipids() {
        return lipids;
    }

    public void setLipids(float lipids) {
        this.lipids = lipids;
    }

    public float getFibers() {
        return fibers;
    }

    public void setFibers(float fibers) {
        this.fibers = fibers;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(float iron) {
        this.iron = iron;
    }

    public float getZinc() {
        return zinc;
    }

    public void setZinc(float zinc) {
        this.zinc = zinc;
    }

    public float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(float magnesium) {
        this.magnesium = magnesium;
    }

    public float getOm3() {
        return om3;
    }

    public void setOm3(float om3) {
        this.om3 = om3;
    }

    public float getOm6() {
        return om6;
    }

    public void setOm6(float om6) {
        this.om6 = om6;
    }

    public float getOm9() {
        return om9;
    }

    public void setOm9(float om9) {
        this.om9 = om9;
    }

    public String getMainVitamin() {
        return mainVitamin;
    }

    public void setMainVitamin(String mainVitamin) {
        this.mainVitamin = mainVitamin;
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", glucids=" + glucids +
                ", lipids=" + lipids +
                ", fibers=" + fibers +
                ", iron=" + iron +
                ", zinc=" + zinc +
                ", magnesium=" + magnesium +
                ", om3=" + om3 +
                ", om6=" + om6 +
                ", om9=" + om9 +
                ", mainVitamin='" + mainVitamin + '\'' +
                ", currentValue=" + currentValue +
                '}';
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
        parcel.writeFloat(currentValue);
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
        this.currentValue = in.readFloat();
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