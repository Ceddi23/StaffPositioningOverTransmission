package com.example.developer.staffpositioningovertransmission;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.developer.staffpositioningovertransmission.Fragment.HistoryFragment;
import com.example.developer.staffpositioningovertransmission.Fragment.HomeFragment;
import com.example.developer.staffpositioningovertransmission.Fragment.ProfileFragment;
import com.example.developer.staffpositioningovertransmission.Fragment.TaskFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;
    HomeFragment homeFragment = new HomeFragment().newInstance();
    TaskFragment taskFragment = new TaskFragment().newInstance();
    HistoryFragment historyFragment = new HistoryFragment().newInstance();
    ProfileFragment profileFragment = new ProfileFragment().newInstance();
    FragmentManager  manager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(
                            R.id.mainFrame,
                            homeFragment,
                            homeFragment.getTag()
                    ).commit();
                    break;
                case R.id.navigation_taskList:
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(
                            R.id.mainFrame,
                            taskFragment,
                            taskFragment.getTag()
                    ).commit();
                    break;
                case R.id.navigation_history:
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(
                            R.id.mainFrame,
                            historyFragment,
                            historyFragment.getTag()
                    ).commit();
                    break;

            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] deliverArray = {"DELIVER 1","DELIVER 2","DELIVER 3","DELIVER 4","DELIVER 5"};

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final AlertDialog.Builder welcomeDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.welcome_dialog, null);
        welcomeDialog.setView(dialogView);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.task_listview, deliverArray);

        ListView listView = (ListView) dialogView.findViewById(R.id.welcome_dialog_listview);
        listView.setAdapter(adapter);

        welcomeDialog.setCancelable(true);
        welcomeDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        welcomeDialog.show();

        FragmentManager  manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.mainFrame,
                homeFragment,
                homeFragment.getTag()
        ).commit();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navbar_profile) {
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.mainFrame,
                    profileFragment,
                    profileFragment.getTag()
            ).commit();
        } else if (id == R.id.navbar_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent j = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(j);
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
