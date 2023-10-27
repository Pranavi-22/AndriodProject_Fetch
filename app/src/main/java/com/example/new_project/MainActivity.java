package com.example.new_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements MainActivityAdapter.OnIDRecyclerViewClickListener {
    // Variables to hold UI elements and data
    private RecyclerView recyclerView;
    private MainActivityAdapter mainActivityAdapter;
    private ArrayList<String> listIDs = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private ArrayList<JSONArray> groupinginIds = new ArrayList<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and its Adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mainActivityAdapter = new MainActivityAdapter(this, listIDs, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mainActivityAdapter);

        // Fetch data from the URL
        fetchData();
    }

    // Method to fetch data from a URL
    private void fetchData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Make a connection to the URL
                    URL url = new URL("https://fetch-hiring.s3.amazonaws.com/hiring.json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;

                        // Read data from the connection
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }

                        // Convert the received data to a JSONArray
                        JSONArray data = new JSONArray(stringBuilder.toString());

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            int listId = jsonObject.getInt("listId");
                            String name = jsonObject.optString("name");

                            if (name != null && !name.isEmpty() && !name.equalsIgnoreCase("null")) {
                                Item item = new Item(id, listId, name);
                                items.add(item);
                            }
                            listIDs.add(String.valueOf(listId));
                        }

                        reader.close();
                    }

                    connection.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Remove duplicate list IDs
                        Set<String> uniqueListIds = new HashSet<>(listIDs);
                        listIDs.clear();
                        listIDs.addAll(uniqueListIds);

                        // Sort listIDs
                        Collections.sort(listIDs);

                        // Group by listId
                        Collections.sort(items, new Comparator<Item>() {
                            @Override
                            public int compare(Item item1, Item item2) {
                                if (item1.getListId() != item2.getListId()) {
                                    return Integer.compare(item1.getListId(), item2.getListId());
                                }
                                return Integer.compare(item1.getId(), item2.getId());
                            }
                        });

                        // Clear and populate groupedJSONOnID
                        groupinginIds.clear();
                        for (int i = 1; i <= 4; i++) {
                            JSONArray jsonArrayForListId = new JSONArray();
                            for (int j = 0; j < items.size(); j++) {
                                Item item = items.get(j);
                                int listId = (int) item.getListId();
                                if (listId == i) {
                                    JSONObject jsonItem = new JSONObject();
                                    try {
                                        jsonItem.put("id", item.getId());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        jsonItem.put("name", item.getName());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArrayForListId.put(jsonItem);
                                }
                            }
                            groupinginIds.add(jsonArrayForListId);
                        }

                        mainActivityAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void OnClick(int pos) {
        if (pos < groupinginIds.size()) {
            JSONArray jsonArray = groupinginIds.get(pos);

            // Start a new activity to view the selected data
            Intent intent = new Intent(this, ViewIDActivity.class);
            intent.putExtra("data", jsonArray.toString());
            startActivity(intent);
        }
    }
}
