package com.grebeteam.agrecovery.support;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.grebeteam.agrecovery.R;
import com.grebeteam.agrecovery.activities.MarketActivity;
import com.grebeteam.agrecovery.entities.Vendor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Enrico on 18/10/2017.
 */

public class VendorMarketAdapter extends BaseAdapter {

    //attributes
    private ArrayList<Vendor> vendors;
    private Context context;

    public VendorMarketAdapter(ArrayList<Vendor> vendors, Context context) {
        this.vendors = vendors;
        this.context = context;
    }

    @Override
    public int getCount() {
        return vendors.size();
    }

    @Override
    public Object getItem(int position) {
        return vendors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vendors.get(position).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup vg)
    {
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.vendor_market_entry, null);
        }

        Vendor vendor = (Vendor) getItem(position);
        TextView name = (TextView) view.findViewById(R.id.vendor_name);
        TextView address =(TextView) view.findViewById(R.id.vendor_address);
        CircleImageView propic = (CircleImageView) view.findViewById(R.id.vendor_profile_image);
        name.setText(vendor.getName());
        address.setText(vendor.getAddress());
        propic.setImageDrawable(vendor.getPropic());

        return view;
    }
}
