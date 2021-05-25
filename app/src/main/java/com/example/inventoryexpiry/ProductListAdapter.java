/**
 * @file Product.java
 * @author Jean-Baptiste Despujol
 * @date 05/25/2020
 * @brief File containing the adapter for a ListView of Product
 */

package com.example.inventoryexpiry;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private Context context;
    private int resource;

    public ProductListAdapter(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String date = getItem(position).getExpiryDate();
        String gtin = getItem(position).getGTIN();

        Product product = new Product(date, gtin);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.date);
        TextView tvGtin = (TextView) convertView.findViewById(R.id.gtin);

        tvDate.setText(date);
        Methods.Time time = Methods.remainingTime(date);
        switch (time) {
            case EXPIRED:
               tvDate.setTextColor(context.getResources().getColor(R.color.expired));
               tvDate.setPaintFlags(tvDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
               break;
            case CRITICAL:
                tvDate.setTextColor(context.getResources().getColor(R.color.critical));
                break;
            case NEAR:
                tvDate.setTextColor(context.getResources().getColor(R.color.near));
                break;
            case MEDIUM:
                tvDate.setTextColor(context.getResources().getColor(R.color.medium));
                break;
            case FAR:
                tvDate.setTextColor(context.getResources().getColor(R.color.far));
                break;
        }
        tvGtin.setText(gtin);

        return convertView;
    }
}
