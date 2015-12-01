package com.virtualaffairs.webtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Invoice> list;
    private Context context;


    public MyAdapter(ArrayList<Invoice> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    public long getItemId(int pos) {
        return list.get(pos).getId();
    }

    public String getItemDate(int pos) {
        return list.get(pos).getDate();
    }

    public double getItemAmount(int pos) {
        return list.get(pos).getAmount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_listview, null);
        }

        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(String.valueOf(getItemId(position)));

        Button deleteBtn = (Button) view.findViewById(R.id.delete_button);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
