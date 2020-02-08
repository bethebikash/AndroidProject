package com.bhattaraibikash.erepair.activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.info.ContactInfoActivity;
import com.bhattaraibikash.erepair.fragment.main.HomeFragment;
import com.bhattaraibikash.erepair.fragment.main.InfoFragment;
import com.bhattaraibikash.erepair.fragment.main.MyBookingFragment;
import com.bhattaraibikash.erepair.fragment.main.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String from;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extra = getIntent().getExtras();
        if (extra != null && !extra.isEmpty()) {
            from = extra.getString("from");
            if(from.equals("EditProfile")){
                loadFragment(new ProfileFragment(), "My Profile");
            } else if(from.equals("Booking")){
                loadFragment(new MyBookingFragment(), "My Bookings");
            }
        } else {
            loadFragment(new HomeFragment(), "Home");
        }

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0] <= 2){
                    Intent intent = new Intent(MainActivity.this, ContactInfoActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null){
            sensorManager.registerListener(sel , sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No Sensor Found", Toast.LENGTH_SHORT).show();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:
                    fragment = new HomeFragment();
                    loadFragment(fragment, "Home");
                    return true;
                case R.id.navMyBooking:
                    fragment = new MyBookingFragment();
                    loadFragment(fragment, "My Bookings");
                    return true;
                case R.id.navInfo:
                    fragment = new InfoFragment();
                    loadFragment(fragment, "Info");
                    return true;
                case R.id.navProfile:
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
        getSupportActionBar().setTitle(toolbarTitle);
        transaction.commit();
    }


//    To handel back pressed behaviour.

    private static final int TIME_INTERVAL = 1500; // Time between two presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Tap again to quit eRepair", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
