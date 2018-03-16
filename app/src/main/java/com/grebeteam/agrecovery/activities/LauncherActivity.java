package com.grebeteam.agrecovery.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.grebeteam.agrecovery.R;
import com.grebeteam.agrecovery.support.VendorsListManager;

public class LauncherActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        this.context = this;
        //init vendorsManager
        VendorsListManager.initVendorsListManager(context);

        Intent intent = new Intent(context, MarketActivity.class);
        context.startActivity(intent);
    }
}
