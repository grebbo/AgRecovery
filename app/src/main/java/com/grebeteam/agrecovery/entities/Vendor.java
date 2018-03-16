package com.grebeteam.agrecovery.entities;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Enrico on 18/10/2017.
 */

public class Vendor extends User{
    //attributes
    private int shippingRange;
    private String description;

    //constructor method
    public Vendor(String name, String address, String tel, String email, Drawable propic, int shippingRange) {
        super(name, address, tel, email, propic);
        this.shippingRange = shippingRange;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
