package com.grebeteam.agrecovery.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.grebeteam.agrecovery.R;
import com.grebeteam.agrecovery.entities.Product;
import com.grebeteam.agrecovery.entities.Product;

import java.util.ArrayList;

/**
 * Created by Enrico on 18/10/2017.
 */

public class ProductMarketAdapter extends BaseAdapter {

    //attributes
    private ArrayList<Product> products;
    private Context context;

    public ProductMarketAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup vg)
    {
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.product_market_entry, null);
        }

        Product product = (Product) getItem(position);
        TextView name = (TextView) view.findViewById(R.id.product_name);
        TextView minPrice =(TextView) view.findViewById(R.id.min_price);
        TextView medPrice =(TextView) view.findViewById(R.id.med_price);
        TextView maxPrice =(TextView) view.findViewById(R.id.max_price);
        name.setText(product.getName());
        minPrice.setText(Long.toString(product.getMinPrice()));
        medPrice.setText(Long.toString(product.getMediumPrice()));
        maxPrice.setText(Long.toString(product.getMaxPrice()));

        //set propic

        return view;
    }
}
