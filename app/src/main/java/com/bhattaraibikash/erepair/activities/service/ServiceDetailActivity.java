package com.bhattaraibikash.erepair.activities.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.booking.BookingActivity;
import com.bhattaraibikash.erepair.adapter.ReviewAdapter;
import com.bhattaraibikash.erepair.api.ReviewApi;
import com.bhattaraibikash.erepair.api.ServiceApi;
import com.bhattaraibikash.erepair.models.Service;
import com.bhattaraibikash.erepair.responses.ReviewResponse;
import com.bhattaraibikash.erepair.url.Url;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailActivity extends AppCompatActivity {

    private TextView tvService, tvCategorySD, tvPrice, tvDescription;
    private Button btnBookNow;
    private ImageView ivServiceSD;
    private RecyclerView rvReview;
    private String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        getSupportActionBar().setTitle("Service Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvService = findViewById(R.id.tvService);
        tvCategorySD = findViewById(R.id.tvCategorySD);
        tvPrice = findViewById(R.id.tvPrice);
        tvService = findViewById(R.id.tvService);
        tvDescription = findViewById(R.id.tvDescription);
        ivServiceSD = findViewById(R.id.ivServiceSD);
        btnBookNow = findViewById(R.id.btnBookNow);

        Bundle extra = getIntent().getExtras();

        if (!extra.isEmpty()) {
            _id = extra.getString("_id");
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        ServiceApi serviceApi = Url.getInstance().create(ServiceApi.class);

        Call<Service> call = serviceApi.getServicesDetail(_id);
        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {

                if (response.body() != null) {
                    tvService.setText(response.body().getTitle());

                    Gson gson = new Gson();
                    String json = gson.toJson(response.body().getCategory()); //convert

                    String[] arrayString = json.split("\"");
                    String data = arrayString[7];

                    tvCategorySD.setText(data);
                    tvPrice.setText(response.body().getPrice());
                    tvDescription.setText(response.body().getDescription());

                    try {
                        Glide.with(ServiceDetailActivity.this)
                                .load(Url.base_url + response.body().getImage())
                                .placeholder(R.drawable.icon)
                                .into(ivServiceSD);
                    } catch (Exception e) {
                        // This will catch any exception, because they are all descended from Exception
                        System.out.println("Error " + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
            }
        });

        // Service Recycler view
        rvReview = findViewById(R.id.rvReview);

        ReviewApi reviewApi = Url.getInstance().create(ReviewApi.class);

        Call<List<ReviewResponse>> reviewResponseCall = reviewApi.getReview(_id);
        reviewResponseCall.enqueue(new Callback<List<ReviewResponse>>() {
            @Override
            public void onResponse(Call<List<ReviewResponse>> call, Response<List<ReviewResponse>> response) {

                if (response.body() != null) {
                    List<ReviewResponse> responseList = response.body();

                    ReviewAdapter reviewAdapter = new ReviewAdapter(ServiceDetailActivity.this, responseList);
                    rvReview.setAdapter(reviewAdapter);
                    rvReview.setLayoutManager(new LinearLayoutManager(ServiceDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(Call<List<ReviewResponse>> call, Throwable t) {
            }
        });


        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetailActivity.this, BookingActivity.class);
                intent.putExtra("_id", _id);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
