package com.grebeteam.agrecovery.support;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.grebeteam.agrecovery.entities.User;
import com.grebeteam.agrecovery.entities.Vendor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enrico on 11/11/2017.
 */

public class VendorsListManager {

    public final static int ERROR = -1;
    private Context context;

    private static User myProfile;

    private static VendorsListManager vendorsListManagerInstance = null;
    private static Map<Integer,Vendor> vendors;

    private VendorsListManager(Context context) {
        vendors = new HashMap<>();
        this.context = context;
        for (Vendor v: generateTestPool(context)) {
            vendors.put(v.getId(), v);
        }
        try {
            myProfile = new User("Jacopo Aiello",
                    "Via della Vittoria 57",
                    "043573512",
                    "jacopoaiello94@mail.com",
                    Drawable.createFromStream(context.getAssets().open("pino.jpg"), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VendorsListManager initVendorsListManager(Context context) {
        if(vendorsListManagerInstance == null) {
            vendorsListManagerInstance = new VendorsListManager(context);
        }
        return vendorsListManagerInstance;
    }

    public static ArrayList<Vendor> getVendors() {
        return new ArrayList<>(vendors.values());
    }

    public static Vendor getVendor(int id) {
        return vendors.get(id);
    }

    public static ArrayList<Vendor> generateTestPool(Context context) {
        AssetManager assetManager = context.getAssets();

        ArrayList<Vendor> result = new ArrayList<>();
        BufferedReader reader;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();
        ArrayList<String> telephones = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(assetManager.open("vendors/names.txt")));
            String line;
            while((line = reader.readLine()) != null) {
                names.add(line);
            }

            reader = new BufferedReader(new InputStreamReader(assetManager.open("vendors/addresses.txt")));
            while((line = reader.readLine()) != null) {
                addresses.add(line);
            }

            reader = new BufferedReader(new InputStreamReader(assetManager.open("vendors/telephones.txt")));
            while((line = reader.readLine()) != null) {
                telephones.add(line);
            }

            reader.close();

            for (int i = 0 ; i < names.size() ; i++) {
                Vendor sample = new Vendor( names.get(i),
                                            addresses.get(i),
                                            telephones.get(i),
                                            names.get(i).replace(" ","_") + "@mail.it",
                                            Drawable.createFromStream(assetManager.open("vendors/" + i + ".jpg"), null),
                                            0);
                sample.setDescription(" Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua...");
                result.add(sample);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static User getMyProfile() {
        return myProfile;
    }
}
