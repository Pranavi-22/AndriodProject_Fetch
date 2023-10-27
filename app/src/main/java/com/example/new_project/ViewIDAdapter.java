package com.example.new_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Adapter for the RecyclerView in ViewIDActivity
public class ViewIDAdapter extends RecyclerView.Adapter<ViewIDAdapter.ViewHolder> {

    Context context;
    ArrayList<ItemEntity> itemEntities;

    // Constructor for the adapter
    public ViewIDAdapter(Context context, ArrayList<ItemEntity> itemEntities) {
        this.context = context;
        this.itemEntities = itemEntities;
    }

    // Called when a new ViewHolder is created
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // Inflating the layout view for a single row
        View view = layoutInflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    // Called to bind data to a ViewHolder at a given position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the values from the data set to the view
        holder.id.setText(itemEntities.get(position).id);
        holder.name.setText(itemEntities.get(position).name);
    }

    // Returns the total number of items in the data set
    @Override
    public int getItemCount() {
        return itemEntities.size();
    }

    // ViewHolder class for the item view
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Initializing the elements of the view to the view holder
        TextView id, name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.data_id);
            name = itemView.findViewById(R.id.data_name);
        }
    }
}
