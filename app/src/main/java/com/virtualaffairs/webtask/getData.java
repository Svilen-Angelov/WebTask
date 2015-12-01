package com.virtualaffairs.webtask;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getData extends AsyncTask<String,String,String> {

    HttpURLConnection urlConnection;

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("http://demo6103298.mockable.io/invoices");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream instream = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader buffreader = new BufferedReader(new InputStreamReader(instream));

            String line;
            while ((line = buffreader.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

}