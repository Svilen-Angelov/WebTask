package com.virtualaffairs.webtask;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyArraylist {

    public static ArrayList<Invoice> list = new ArrayList<Invoice>();

    public ArrayList<Invoice> filterByCurrentdate(){

        ArrayList<Invoice> temp = new ArrayList<Invoice>();

        for(int i = 0; i < list.size(); i++){

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date objectdate = df.parse(list.get(i).getDate());
                Date currentdate = df.parse(df.format(c.getTime()));

                if (currentdate.before(objectdate)) {
                    temp.add(list.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return temp;
    }
}
