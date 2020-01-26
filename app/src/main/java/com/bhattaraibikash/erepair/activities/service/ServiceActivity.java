package com.bhattaraibikash.erepair.activities.service;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.adapter.ServiceAdapter;
import com.bhattaraibikash.erepair.models.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    private RecyclerView rvServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        getSupportActionBar().setTitle("Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Service Recycler view
        rvServices = findViewById(R.id.rvServices);

        List<Service> serviceList =new ArrayList<>();
        serviceList.add(new Service("0222520", "Title one", "the description", "2514", "image", "category"));
        serviceList.add(new Service("5454851", "Title two", "icon", "2514", "image", "category"));
        serviceList.add(new Service("2125648", "Title three", "icon", "2514", "image", "category"));
        serviceList.add(new Service("2125648", "Title four", "icon", "2514", "image", "category"));
        serviceList.add(new Service("2125648", "Title five", "icon", "2514", "image", "category"));

        ServiceAdapter serviceAdapter = new ServiceAdapter(this, serviceList);
        rvServices.setAdapter(serviceAdapter);
        rvServices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
