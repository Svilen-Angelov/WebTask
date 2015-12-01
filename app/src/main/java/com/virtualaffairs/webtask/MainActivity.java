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

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button mFilterButton;
    private static final String TAG = "MainActivity";
    private static final String TAG_INVOICES = "InvoicesArray";
    private static String url = "http://demo6103298.mockable.io/invoices";

    /*
    //private ArrayList<Invoice> list = new ArrayList<Invoice>();
    private String jstring = "{\n" +
            "    \"InvoicesArray\":[{\n" +
            "            \"id\":\"44533\",\n" +
            "            \"date\":\"12/12/2015\",\n" +
            "\t\t\t\"amount\":\"313.56\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"25313\",\n" +
            "            \"date\":\"04/11/2015\",\n" +
            "\t\t\t\"amount\":\"323.56\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"963510\",\n" +
            "            \"date\":\"12/11/2016\",\n" +
            "\t\t\t\"amount\":\"42\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"88\",\n" +
            "            \"date\":\"06/06/2014\",\n" +
            "\t\t\t\"amount\":\"7777\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"66600\",\n" +
            "            \"date\":\"08/11/2015\",\n" +
            "\t\t\t\"amount\":\"97.4\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"9999\",\n" +
            "            \"date\":\"1/12/2015\",\n" +
            "\t\t\t\"amount\":\"66.56\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"1337\",\n" +
            "            \"date\":\"23/12/2015\",\n" +
            "\t\t\t\"amount\":\"72\"\n" +
            "        },\n" +
            "\t\t{\n" +
            "            \"id\":\"445\",\n" +
            "            \"date\":\"07/02/2016\",\n" +
            "\t\t\t\"amount\":\"441.5\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String jstring = null;
        try {
            jstring = new getData().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonRootObject = new JSONObject(jstring);

            JSONArray jsonArray = jsonRootObject.optJSONArray(TAG_INVOICES);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String date = jsonObject.optString("date").toString();
                double amount = Double.parseDouble(jsonObject.optString("amount").toString());

                Invoice temp = new Invoice(id,date,amount);

                MyArraylist.list.add(temp);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mFilterButton = (Button)findViewById(R.id.filter_button);
        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start screenC
                Intent FilterIntent = new Intent(MainActivity.this, FilteredActivity.class);
                startActivity(FilterIntent);
            }
        });

        final MyAdapter adapter = new MyAdapter(MyArraylist.list, this);

        ListView ScreenAview = (ListView) findViewById(R.id.unfiltered_list);
        ScreenAview.setAdapter(adapter);

        ScreenAview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "position : " + position);
                Intent InfoIntent = new Intent(MainActivity.this,InfoActivity.class);
                InfoIntent.putExtra("id", (int) adapter.getItemId(position));
                InfoIntent.putExtra("date",adapter.getItemDate(position));
                InfoIntent.putExtra("amount", adapter.getItemAmount(position));
                startActivity(InfoIntent);
            }
        });
    }
}
