package com.bhattaraibikash.erepair;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bhattaraibikash.erepair.fragment.HomeFragment;
import com.bhattaraibikash.erepair.fragment.InfoFragment;
import com.bhattaraibikash.erepair.fragment.MyBookingFragment;
import com.bhattaraibikash.erepair.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
//    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = getSupportActionBar();

        navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

//        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:
//                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navMyBooking:
//                    toolbar.setTitle("My Bookings");
                    fragment = new MyBookingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navInfo:
//                    toolbar.setTitle("Info");
                    fragment = new InfoFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navProfile:
//                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
