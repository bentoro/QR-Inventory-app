package com.example.ben.qrcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nihal on 1/24/2017.
 */

public class SalesAdapter extends ArrayAdapter<sold> {
    public SalesAdapter(Context context, List<sold> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        sold sold = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout, parent, false);
        }
        // Lookup view for data population
        TextView data = (TextView) convertView.findViewById(R.id.textView);
        // Populate the data into the template view using the data object
        data.setText(sold.toString());
        // Return the completed view to render on screen
        return convertView;
    }

}