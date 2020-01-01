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

import com.bhattaraibikash.erepair.fragment.main.MyBookingFragment;
import com.bhattaraibikash.erepair.fragment.main.HomeFragment;
import com.bhattaraibikash.erepair.fragment.main.InfoFragment;
import com.bhattaraibikash.erepair.fragment.main.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setVisibility(View.INVISIBLE);
        loadFragment(new HomeFragment(), "Home");

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:
                    btnLogout.setVisibility(View.INVISIBLE);
                    fragment = new HomeFragment();
                    loadFragment(fragment, "Home");
                    return true;
                case R.id.navMyBooking:
                    btnLogout.setVisibility(View.INVISIBLE);
                    fragment = new MyBookingFragment();
                    loadFragment(fragment, "My Bookings");
                    return true;
                case R.id.navInfo:
                    btnLogout.setVisibility(View.INVISIBLE);
                    fragment = new InfoFragment();
                    loadFragment(fragment, "Info");
                    return true;
                case R.id.navProfile:
                    btnLogout.setVisibility(View.VISIBLE);
                    fragment = new ProfileFragment();
                    loadFragment(fragment, "My Profile");
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment, String toolbarTitle) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        toolbar.setTitle(toolbarTitle);
        transaction.addToBackStack(null);
        transaction.commit();
    }



//    @Override
//    public void onBackPressed() {
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
//        int seletedItemId = bottomNavigationView.getSelectedItemId();
//        if (R.id.home != seletedItemId) {
//            setHomeItem(MainActivity.this);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    public static void setHomeItem(Activity activity) {
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)
//                activity.findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setSelectedItemId(R.id.home);
//    }

    @Override
    public void onBackPressed() {
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottomNavigation);
        if (mBottomNavigationView.getSelectedItemId() == R.id.bottomNavigation)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            mBottomNavigationView.setSelectedItemId(R.id.bottomNavigation);
        }
    }

}
