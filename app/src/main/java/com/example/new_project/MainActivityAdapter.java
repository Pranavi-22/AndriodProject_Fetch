package com.example.new_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Adapter for the RecyclerView in MainActivity
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    ArrayList<String> listIDs; // List of IDs
    Context context; // Context reference
    OnIDRecyclerViewClickListener onIDRecyclerViewClickListener; // Listener for item click

    // Constructor for the adapter
    public MainActivityAdapter(MainActivity mainActivity, ArrayList<String> listIDs, Context context) {
        this.listIDs = listIDs;
        this.context = context;
        this.onIDRecyclerViewClickListener = (OnIDRecyclerViewClickListener) mainActivity; // Set the listener
    }

    // Called when a new ViewHolder is created
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // Inflating the layout view for a single row
        View view = layoutInflater.inflate(R.layout.button, parent, false);
        return new ViewHolder(view);
    }

    // Called to bind data to a ViewHolder at a given position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String listId = listIDs.get(position);
        holder.button.setText("ListId : " + listId);
        holder.button.setBackgroundColor(Color.BLUE);
        holder.button.setOnClickListener(new View.OnClickListener() {
            // When the viewID button is clicked
            @Override
            public void onClick(View v) {
                onIDRecyclerViewClickListener.OnClick(position);
            }
        });
    }

    // Returns the total number of items in the data set
    @Override
    public int getItemCount() {
        return listIDs.size();
    }

    // ViewHolder class for the item view
    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.viewIDs);
        }
    }

    // Interface to handle item clicks
    public interface OnIDRecyclerViewClickListener {
        void OnClick(int pos);
    }
}
