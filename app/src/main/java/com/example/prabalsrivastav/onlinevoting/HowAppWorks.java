package com.example.prabalsrivastav.onlinevoting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HowAppWorks extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        FirebaseDatabase database;
        DatabaseReference reference;
        String City ;  // To get the value of city through the passed login ID

        Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_app_works);

        //Always use FrameLayout and not fragment
        //Also add fragment using java(in the onCreate method of the container activity for the default preview on the start
        // of the program like this),and not using xml

        Fragment fragment = new FragmentHome();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentPlace,fragment);
        transaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.


        //getting login ID sent from login page
        String login = getIntent().getStringExtra("Login");
        //getting the city of the passed login ID
        reference = database.getInstance().getReference();
        reference.child("Details").child(login).child("city").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                City = (String) dataSnapshot.getValue();

                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    // Handle the home action
                    fragment = new FragmentHome();

                } else if (id == R.id.nav_party) {
                    fragment = new FragmentParty();

                    //passing City from this activity to party fragment

                    Bundle bundle = new Bundle();
                    bundle.putString("city",City);
                    fragment.setArguments(bundle);

                } else if (id == R.id.nav_candidate) {
                    if(City.equals("Allahabad")) {
                        fragment = new AllahabadCandidate();
                    }

                    else if(City.equals("Varanasi")) {
                        fragment = new VaranasiCandidate();
                    }

                    else if(City.equals("Noida")) {
                        fragment = new NoidaCandidate();
                    }

                    else if(City.equals("Kanpur")) {
                        fragment = new KanpurCandidate();
                    }

                    else if(City.equals("Lucknow")) {
                        fragment = new LucknowCandidate();
                    }

                } else if (id == R.id.nav_topCandidate) {
                    fragment = new FragmentTopCandidate();
                }

                if(fragment!=null) {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentPlace,fragment);
                    transaction.commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
