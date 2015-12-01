package com.virtualaffairs.webtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilteredActivity extends AppCompatActivity {

    private static final String TAG = "FilteredActivity";
    private ArrayList<Invoice> filteredList = new MyArraylist().filterByCurrentdate();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered);

        final MyAdapter adapter = new MyAdapter(filteredList, this);

        ListView ScreenCview = (ListView) findViewById(R.id.filtered_list);
        ScreenCview.setAdapter(adapter);

        ScreenCview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "position : " + position);
                Intent InfoIntent = new Intent(FilteredActivity.this, InfoActivity.class);
                InfoIntent.putExtra("id", (int) adapter.getItemId(position));
                InfoIntent.putExtra("date", adapter.getItemDate(position));
                InfoIntent.putExtra("amount", adapter.getItemAmount(position));
                startActivity(InfoIntent);
            }
        });

    }
}
