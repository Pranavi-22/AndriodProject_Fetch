package com.example.new_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// Activity to view IDs
public class ViewIDActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recycler);

        // Initializing the RecyclerView
        recyclerView = findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true);

        // Getting the value from the intent
        Intent intent = getIntent();
        String jsonData = intent.getStringExtra("data");

        try {
            ArrayList<ItemEntity> itemList = new ArrayList<>();
            // Object mapping from JSON to ItemEntity
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ItemEntity itemEntity = new ItemEntity(jsonObject.getString("id"), jsonObject.getString("name"));
                itemList.add(itemEntity);
            }

            // Creating and setting up the adapter
            ViewIDAdapter viewIDAdapter = new ViewIDAdapter(this, itemList);
            recyclerView.setLayoutManager(new LinearLayoutManager(ViewIDActivity.this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(viewIDAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
