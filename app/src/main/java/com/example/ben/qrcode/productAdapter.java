package com.example.ben.qrcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class productAdapter extends ArrayAdapter<product> {
    public productAdapter(Context context, List<product> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        product product = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout, parent, false);
        }
        TextView data = (TextView) convertView.findViewById(R.id.textView);
        data.setText(product.toString());
        return convertView;
    }

}