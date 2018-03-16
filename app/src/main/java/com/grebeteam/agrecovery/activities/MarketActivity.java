package com.grebeteam.agrecovery.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.grebeteam.agrecovery.support.VendorMarketAdapter;
import com.grebeteam.agrecovery.R;
import com.grebeteam.agrecovery.entities.Vendor;
import com.grebeteam.agrecovery.support.VendorsListManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MarketActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    //views
    private ListView marketVendorsListView;
    private EditText searchBox;

    //data structures
    private ArrayList<Vendor> vendors;
    private VendorsListManager vendorsManager;

    private void findViews() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.marketVendorsListView = (ListView) findViewById(R.id.market_vendors_listview);
        this.searchBox = (EditText) findViewById(R.id.market_search_text);
    }

    private void setupViews() {
        setupToolbar();
        setupFab();
        setupNavigationView();
        setupDrawer();

        setupMarketListView();
        setupSearchBox();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        VendorsListManager.initVendorsListManager(this);
        ((CircleImageView) header.findViewById(R.id.nav_user_pic)).setImageDrawable(VendorsListManager.getMyProfile().getPropic());
    }

    private void setupMarketListView() {
        retrieveVendors();
        VendorMarketAdapter mAdapter = new VendorMarketAdapter(vendors, this);
        marketVendorsListView.setAdapter(mAdapter);
        marketVendorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vendor selectedVendor = (Vendor) marketVendorsListView.getAdapter().getItem(position);
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("userId", selectedVendor.getId());
                intent.putExtra("status", "vendor");
                context.startActivity(intent);
            }
        });
    }

    private void retrieveVendors() {
        vendors = VendorsListManager.getVendors();
    }

    private void setupSearchBox() {
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //to do
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        this.context = this;
        this.vendorsManager = VendorsListManager.initVendorsListManager(context);
        findViews();
        setupViews();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.market, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent navigationIntent;

        switch (id) {
            case R.id.nav_profile:
                navigationIntent = new Intent(context, MyProfileActivity.class);
                context.startActivity(navigationIntent);
                break;
            case R.id.nav_market:
                navigationIntent = new Intent(context, MarketActivity.class);
                context.startActivity(navigationIntent);
                break;
            case R.id.nav_settings:
                navigationIntent = new Intent(context, SettingsActivity.class);
                context.startActivity(navigationIntent);
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
