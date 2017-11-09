package com.example.ben.qrcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class salesAdapter extends ArrayAdapter<sold> {
    public salesAdapter(Context context, List<sold> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sold sold = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout, parent, false);
        }
        TextView data = (TextView) convertView.findViewById(R.id.textView);
        data.setText(sold.toString());
        return convertView;
    }

}