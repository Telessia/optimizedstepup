package fr.telecorp.optimizedstepup.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.telecorp.optimizedstepup.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private FoodList foodDataset;
    private FoodList originalItems = new FoodList();

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
    public FoodAdapter(FoodList myDataset) {
        foodDataset = myDataset;
        cloneItems(myDataset);
    }

    // Create new views (invoked by the layout manager)
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
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.iv.setImageResource(foodDataset.get(position).getId());
        holder.name.setText(foodDataset.get(position).getName());
        holder.unit.setText(foodDataset.get(position).getUnit());
        holder.calories.setText("Calories :\n" + foodDataset.get(position).getCalories() +" kCal");
        holder.proteines.setText("Proteins :\n" + foodDataset.get(position).getProteins() +" g");
        holder.glucids.setText("Glucids :\n" + foodDataset.get(position).getGlucids() + " g");
        holder.lipids.setText("Lipids :\n" + foodDataset.get(position).getLipids() +" g");
        holder.fibers.setText("Fibers :\n" + foodDataset.get(position).getFibers() +" g");
        holder.value.setHint(String.valueOf(foodDataset.get(position).getCurrentValue()));
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

    public Filter getFilter() {
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
                notifyDataSetChanged();//Rafraichit la liste de MusicActivity pour afficher-
                //-la nouvelle liste filtrée
            }
        };
        return filter;
    }
}
