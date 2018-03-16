package com.grebeteam.agrecovery.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by Enrico on 18/10/2017.
 */

public class User {
    //attributes
    private int id;
    private String name;
    private String address;
    private String tel;
    private String email;
    private Drawable propic;

    //constructor method
    public User(String name, String address, String tel, String email, Drawable propic) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.id = this.hashCode() + String.valueOf(System.currentTimeMillis()).hashCode();
        this.propic = propic;
    }

    //getters and setters
    public int getId() {
        return this.id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Drawable getPropic() {
        return propic;
    }
    public void setPropic(Drawable propic) {
        this.propic = propic;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
