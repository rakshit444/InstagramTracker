package com.jain.rakshit.instagramtrackerfinal.Activites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jain.rakshit.instagramtrackerfinal.Fragments.Following;
import com.jain.rakshit.instagramtrackerfinal.Fragments.Following.OnFragmentInteractionListener;
import com.jain.rakshit.instagramtrackerfinal.Fragments.LostFollowers;
import com.jain.rakshit.instagramtrackerfinal.Fragments.NotFollowingBack;
import com.jain.rakshit.instagramtrackerfinal.Model.InstagramSession;
import com.jain.rakshit.instagramtrackerfinal.Network.InstagramApp;
import com.jain.rakshit.instagramtrackerfinal.ObjectWrapperForBinder;
import com.jain.rakshit.instagramtrackerfinal.R;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.Datum;

import java.util.ArrayList;

public class NavBarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    private static String TAG = "NavBarActivity";
    private InstagramApp mApp;
    private InstagramSession mInstagramSession;
    private MainActivity mMainActivity;
    private Context context;
    private ArrayList<Datum> mList;

    public NavBarActivity() {

    }

    public NavBarActivity(Context context) {
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mApp = (InstagramApp) getIntent().<ObjectWrapperForBinder>getParcelableExtra("object").getData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mInstagramSession = new InstagramSession(getApplicationContext());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_bar, menu);
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
        if (id == R.id.Sign_out) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    NavBarActivity.this);
            builder.setMessage("Disconnect from Instagram?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {

                                public void onClick(
                                        DialogInterface dialog, int id) {

                                    mApp.resetAccessToken();
                                    Log.i(TAG, "Resetting access token");
                                    Intent intent = new Intent(NavBarActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            final AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = new Fragment();

        if (id == R.id.followers) {

            fragment = new Following().newInstance("1", "2");

        } else if (id == R.id.lost_followers) {
            fragment = new LostFollowers().newInstance(mList);
        } else if (id == R.id.NotFollowingBack) {
            fragment = new NotFollowingBack().newInstance(mList);
        }
        transaction.replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentMessage(ArrayList<Datum> mList) {
        this.mList = mList;
        Log.i(TAG, mList + "");
    }

}
