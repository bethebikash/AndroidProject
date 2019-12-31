package com.bhattaraibikash.erepair;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bhattaraibikash.erepair.fragment.home.HomeFragment;
import com.bhattaraibikash.erepair.fragment.info.InfoFragment;
import com.bhattaraibikash.erepair.fragment.bookings.MyBookingFragment;
import com.bhattaraibikash.erepair.fragment.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Home");
        btnLogout.setVisibility(View.INVISIBLE);
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:
                    toolbar.setTitle("Home");
                    btnLogout.setVisibility(View.INVISIBLE);
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navMyBooking:
                    toolbar.setTitle("My Bookings");
                    btnLogout.setVisibility(View.INVISIBLE);
                    fragment = new MyBookingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navInfo:
                    toolbar.setTitle("Info");
                    btnLogout.setVisibility(View.INVISIBLE);
                    fragment = new InfoFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navProfile:
                    toolbar.setTitle("My Profile");
                    btnLogout.setVisibility(View.VISIBLE);
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
