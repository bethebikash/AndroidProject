package com.bhattaraibikash.erepair.activities.service;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.adapter.ServiceAdapter;
import com.bhattaraibikash.erepair.api.ServiceApi;
import com.bhattaraibikash.erepair.models.Service;
import com.bhattaraibikash.erepair.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {

    private RecyclerView rvServices;
    private TextView tvCategory;
    private String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        getSupportActionBar().setTitle("Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCategory = findViewById(R.id.tvCategory);


        Bundle extra = getIntent().getExtras();

        if (!extra.isEmpty()) {
            _id = extra.getString("_id");
            tvCategory.setText(extra.getString("catName"));

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }


        // Service Recycler view
        rvServices = findViewById(R.id.rvServices);

        ServiceApi serviceApi = Url.getInstance().create(ServiceApi.class);

        Call<List<Service>> callService = serviceApi.getServicesByCategory(_id);
        callService.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> callService, Response<List<Service>> response) {
                List<Service> serviceList = response.body();

                ServiceAdapter serviceAdapter = new ServiceAdapter(ServiceActivity.this, serviceList);
                rvServices.setAdapter(serviceAdapter);
                rvServices.setLayoutManager(new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onFailure(Call<List<Service>> callService, Throwable t) {
                Toast.makeText(ServiceActivity.this, "failed:" + t, Toast.LENGTH_SHORT).show();
            }
        });

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
