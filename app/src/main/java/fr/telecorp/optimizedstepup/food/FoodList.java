package fr.telecorp.optimizedstepup.food;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends ArrayList<Food> implements Parcelable {

    public FoodList() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags)
    {
        //Taille de la liste
        int size = this.size();
        parcel.writeInt(size);
        for(int i=0; i < size; i++)
        {
            Food food = this.get(i); //On vient lire chaque objet personne
            parcel.writeInt(food.getId());
            parcel.writeString(food.getName());
            parcel.writeString(food.getType());
            parcel.writeString(food.getUnit());
            parcel.writeFloat(food.getCalories());
            parcel.writeFloat(food.getProteins());
            parcel.writeFloat(food.getGlucids());
            parcel.writeFloat(food.getLipids());
            parcel.writeFloat(food.getFibers());
            parcel.writeFloat(food.getIron());
            parcel.writeFloat(food.getZinc());
            parcel.writeFloat(food.getMagnesium());
            parcel.writeFloat(food.getOm3());
            parcel.writeFloat(food.getOm6());
            parcel.writeFloat(food.getOm9());
            parcel.writeString(food.getMainVitamin());
            parcel.writeFloat(food.getCurrentValue());
        }
    }

    public FoodList(Parcel in)
    {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for(int i = 0; i < size; i++)
        {
            Food food = new Food();
            food.setId(in.readInt());
            food.setName(in.readString());
            food.setType(in.readString());
            food.setUnit(in.readString());
            food.setCalories(in.readFloat());
            food.setProteins(in.readFloat());
            food.setGlucids(in.readFloat());
            food.setLipids(in.readFloat());
            food.setFibers(in.readFloat());
            food.setIron(in.readFloat());
            food.setZinc(in.readFloat());
            food.setMagnesium(in.readFloat());
            food.setOm3(in.readFloat());
            food.setOm6(in.readFloat());
            food.setOm9(in.readFloat());
            food.setMainVitamin(in.readString());
            food.setCurrentValue(in.readFloat());
            this.add(food);
        }
    }

    public static final Creator<FoodList> CREATOR = new Creator<FoodList>() {
        public FoodList createFromParcel(Parcel in) {
            return new FoodList(in);
        }

        public FoodList[] newArray(int size) {
            return new FoodList[size];
        }
    };

}
