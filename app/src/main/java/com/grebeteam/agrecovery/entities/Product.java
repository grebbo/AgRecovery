package com.grebeteam.agrecovery.entities;

import java.util.ArrayList;

/**
 * Created by Enrico on 18/10/2017.
 */

public class Product {
    //attributes
    private String name;
    private long minPrice;
    private long mediumPrice;
    private long maxPrice;
    private ArrayList<Vendor> productVendors;

    public Product(String name) {
        this.name = name;
        productVendors = new ArrayList<>();
        fillProductVendors();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(long minPrice) {
        this.minPrice = minPrice;
    }

    public long getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(long mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    public long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(long maxPrice) {
        this.maxPrice = maxPrice;
    }

    private void fillProductVendors() {
        //fetch vendors from db
    }
}
