package com.grebeteam.agrecovery.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.grebeteam.agrecovery.R;
import com.grebeteam.agrecovery.entities.User;
import com.grebeteam.agrecovery.entities.Vendor;
import com.grebeteam.agrecovery.support.VendorsListManager;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    //userInfos
    private CircleImageView userPic;
    private TextView userName;
    private TextView userStatus;
    private TextView userDescription;
    private TextView userTelephone;
    private TextView userAddress;
    private TextView userMail;

    private void findViews() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.userPic = (CircleImageView) findViewById(R.id.profile_user_image);
        this.userName = (TextView) findViewById(R.id.profile_user_name);
        this.userStatus = (TextView) findViewById(R.id.profile_user_status);
        this.userDescription = (TextView) findViewById(R.id.profile_user_description);
        this.userTelephone = (TextView) findViewById(R.id.profile_user_tel);
        this.userAddress = (TextView) findViewById(R.id.profile_user_address);
        this.userMail = (TextView) findViewById(R.id.profile_user_mail);
    }

    private void setupViews() {
        setupToolbar();
        setupDrawer();
        setupNavigationView();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
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

    private void showInfo(User selectedUser) {
        userName.setText(selectedUser.getName());
        userPic.setImageDrawable(selectedUser.getPropic());
        userMail.setText(selectedUser.getEmail());
        userAddress.setText(selectedUser.getAddress());
        userTelephone.setText(selectedUser.getTel());

        if (selectedUser instanceof Vendor) {
            Vendor selectedVendor = (Vendor) selectedUser;
            userStatus.setText("Vendor");
            userDescription.setText(selectedVendor.getDescription());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.context = this;
        VendorsListManager.initVendorsListManager(context);

        findViews();
        setupViews();

        String status = getIntent().getStringExtra("status");
        if (status.equals("vendor")) {
            int selectedId = getIntent().getIntExtra("userId", VendorsListManager.ERROR);
            if (selectedId == VendorsListManager.ERROR) {
                //elaborate error
                Log.d("ProfileActivity", "Error");
            }
            else {
                Vendor selectedVendor = VendorsListManager.getVendor(selectedId);
                Log.d("ProfileActivity", selectedVendor.toString());
                showInfo(selectedVendor);
            }
        }
        else if (status.equals("user")) {
            //do things
        }
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
        getMenuInflater().inflate(R.menu.profile, menu);
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
