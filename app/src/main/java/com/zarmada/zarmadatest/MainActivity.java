package com.zarmada.zarmadatest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.startNewFragment(new SurveyFragment());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            this.addFragmentToStack(new SurveyListFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setPageTitle(String title) {
        this.getSupportActionBar().setTitle(title);
    }

    /** Check if fragment can respond on back button press  */
    protected int backStack = 0;
    /** Validate back press button action */
    protected boolean isCanChangeFragment = true;

    /**
     * Add new fragment without backStack
     * @param fragment
     */
    public void startNewFragment(Fragment fragment){
        // Check if fragment not doing any BE calling
        if (this.isCanChangeFragment) {
            // Get fragment Manager
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            // Initialize fragment transaction
            FragmentTransaction ft = fm.beginTransaction();
            // Replace with fragment content
            ft.replace(R.id.simple_fragment, fragment).commit();
            // Reset stack
            this.backStack = 0;
        }
    }

    /**
     * Add new fragment to stack.
     * @param fragment
     */
    public void addFragmentToStack(Fragment fragment) {
        // Check if fragment not doing any BE calling
        if (this.isCanChangeFragment) {
            // Initialize fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace with fragment content
            ft.replace(R.id.simple_fragment, fragment);
            // Animation on change
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            // Add fragment to stack (back button memory)
            ft.addToBackStack(fragment.toString());
            ft.commit();
            // Can do back action
            this.backStack++;
        }
    }

}
