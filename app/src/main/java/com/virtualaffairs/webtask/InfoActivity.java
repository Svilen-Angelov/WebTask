package com.virtualaffairs.webtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView id;
    private TextView date;
    private TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        id = (TextView)findViewById(R.id.info_id);
        date = (TextView)findViewById(R.id.info_date);
        amount = (TextView)findViewById(R.id.info_amount);

        Intent intent = getIntent();

        id.setText(String.valueOf(intent.getExtras().getInt("id")));
        date.setText(intent.getExtras().getString("date"));
        amount.setText(String.valueOf(intent.getExtras().getDouble("amount")));

    }
}
