package com.androboys.marineresource;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fish_cultivation_tab.TabFishCollect;
import fish_cultivation_tab.TabFishFeed;
import fish_cultivation_tab.TabFishOffice;
import fish_cultivation_tab.TabLoan;
import jolo_jatra_tab.TabCoastGuard;
import jolo_jatra_tab.TabDirection;
import jolo_jatra_tab.TabJoloShima;
import jolo_jatra_tab.TabMarker;

public class JoloJatra extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jolo_jatra);
/**
 *Setup the DrawerLayout and NavigationView
 */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabJoloShima()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();


                if (menuItem.getItemId() == R.id.nav_item_joloshima) {

                    fragmentTransaction.replace(R.id.containerView, new TabJoloShima()).commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_direction) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new TabDirection()).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_marker) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new TabMarker()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_coastguard) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new TabCoastGuard()).commit();
                }


                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
}
