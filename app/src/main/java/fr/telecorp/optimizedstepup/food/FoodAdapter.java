package fr.telecorp.optimizedstepup.food;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.Iterator;

import fr.telecorp.optimizedstepup.R;
import fr.telecorp.optimizedstepup.database.FoodDao;
import fr.telecorp.optimizedstepup.database.FoodDatabase;
import lombok.NonNull;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private FoodList foodDataset;
    private FoodList originalItems = new FoodList();
    private FoodList currentItemsByTypes = new FoodList();
    private Activity parentActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView iv;
        public TextView name;
        public TextView unit;
        public TextView calories;
        public TextView proteines;
        public TextView glucids;
        public TextView lipids;
        public TextView fibers;
        public EditText value;
        public FoodViewHolder(View v) {
            super(v);
            iv = v.findViewById(R.id.foodicon);
            name = v.findViewById(R.id.name);
            unit = v.findViewById(R.id.unit);
            calories = v.findViewById(R.id.calories);
            proteines = v.findViewById(R.id.proteins);
            glucids = v.findViewById(R.id.glucids);
            lipids = v.findViewById(R.id.lipids);
            fibers = v.findViewById(R.id.fibers);
            value = v.findViewById(R.id.value);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FoodAdapter(FoodList pDataset, Activity pActivity) {
        foodDataset = pDataset;
        cloneItems(pDataset);
        this.parentActivity = pActivity;
    }

    // Create new views (invoked by the layout manager)
    @androidx.annotation.NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
                // create a new view
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cell_food, parent, false);

                return new FoodViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String type = foodDataset.get(position).getType();
        String[] splited = type.split(" ");
        if (splited.length > 1) {
            type = (splited[0] + "_" + splited[1] + "_icon");
        } else {
            type = splited[0] + "_icon";
        }
        type = type.toLowerCase();
        int drawableId = parentActivity.getResources().getIdentifier(type, "drawable", parentActivity.getPackageName());
        holder.iv.setImageResource(drawableId);
        holder.name.setText(foodDataset.get(position).getName());
        holder.unit.setText(foodDataset.get(position).getUnit());
        holder.calories.setText("Calories :\n" + foodDataset.get(position).getCalories() + " kCal");
        holder.proteines.setText("Proteins :\n" + foodDataset.get(position).getProteins() + " g");
        holder.glucids.setText("Glucids :\n" + foodDataset.get(position).getGlucids() + " g");
        holder.lipids.setText("Lipids :\n" + foodDataset.get(position).getLipids() + " g");
        holder.fibers.setText("Fibers :\n" + foodDataset.get(position).getFibers() + " g");
        holder.value.setText(String.valueOf(foodDataset.get(position).getCurrentValue()));
        holder.setIsRecyclable(false); //TODO check if it works
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                FoodDatabase db = Room.databaseBuilder(holder.itemView.getContext(),
                                        FoodDatabase.class, "food_database").allowMainThreadQueries().build();
                                FoodDao foodDao = db.foodDao();
                                foodDao.delete(foodDataset.get(holder.getAdapterPosition()));
                                db.close();
                                foodDataset.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                //NOTHING
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setMessage("Do you really want to delete this item?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });
    }

    public void restoreFilter(){
        foodDataset = originalItems;
    }

    public void saveCurrentType(){
        currentItemsByTypes = foodDataset;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodDataset.size();
    }

    protected void cloneItems(FoodList items) {
        for (Iterator<Food> iterator = items.iterator(); iterator
                .hasNext();) {
            Food f = (Food) iterator.next();
            originalItems.add(f);
        }
    }

    public Filter getFilterByName() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {//Filtre grace aux -
                //- lettres du TextWatcher cf.MusicActivity

                FilterResults results = new FilterResults();

                FoodList FilteredFood = new FoodList();

                if (constraint == null || constraint.length() == 0) {
                    //synchronized (mLock) {
                        results.values = currentItemsByTypes;
                        results.count = currentItemsByTypes.size();
                        return results;
                    }

                constraint = constraint.toString().toLowerCase();

                for (int i = 0; i < foodDataset.size(); i++) {
                    String filteredtitle = foodDataset.get(i).getName();
                    if (filteredtitle.toLowerCase().startsWith(constraint.toString())||filteredtitle.toLowerCase().contains(constraint.toString())) {
                        FilteredFood.add(foodDataset.get(i));
                    }
                }
                results.count = FilteredFood.size();
                results.values = FilteredFood;
                return results;
            }
            //Applique le résultat sur la liste
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                foodDataset = (FoodList) results.values;
                notifyDataSetChanged();//Rafraichit la liste de FoodActivity pour afficher-
                //-la nouvelle liste filtrée
            }
        };
        return filter;
    }

    public Filter getFilterByType() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {//Filtre grace aux -
                //- lettres du TextWatcher cf.MusicActivity

                FilterResults results = new FilterResults();

                FoodList FilteredFood = new FoodList();

                if (constraint == null || constraint.length() == 0) {
                    //synchronized (mLock) {
                    results.values = originalItems;
                    results.count = originalItems.size();
                    return results;
                }

                constraint = constraint.toString().toLowerCase();

                for (int i = 0; i < foodDataset.size(); i++) {
                    String filteredtitle = foodDataset.get(i).getType();
                    if (filteredtitle.toLowerCase().startsWith(constraint.toString())||filteredtitle.toLowerCase().contains(constraint.toString())) {
                        FilteredFood.add(foodDataset.get(i));
                    }
                }
                results.count = FilteredFood.size();
                results.values = FilteredFood;
                return results;
            }
            //Applique le résultat sur la liste
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                foodDataset = (FoodList) results.values;
                currentItemsByTypes = foodDataset;
                notifyDataSetChanged();//Rafraichit la liste de FoodActivity pour afficher-
                //-la nouvelle liste filtrée
            }
        };
        return filter;
    }

    
}
