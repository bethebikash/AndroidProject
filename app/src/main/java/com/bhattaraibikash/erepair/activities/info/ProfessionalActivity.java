package com.bhattaraibikash.erepair.activities.info;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bhattaraibikash.erepair.R;

public class ProfessionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        getSupportActionBar().setTitle("Become Professional");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
