package com.bhattaraibikash.erepair.activities.service;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.adapter.ReviewAdapter;
import com.bhattaraibikash.erepair.responses.ReviewResponse;

import java.util.ArrayList;
import java.util.List;

public class ServiceDetailActivity extends AppCompatActivity {

    private TextView tvService, tvCategorySD, tvPrice;
    private Button btnBookNow;
    private RecyclerView rvReview;
    private String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        getSupportActionBar().setTitle("Service Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();

        if (!extra.isEmpty()) {
            _id = extra.getString("_id");

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        // Service Recycler view
        rvReview = findViewById(R.id.rvReview);

        List<ReviewResponse> reviewResponseList =new ArrayList<>();
        reviewResponseList.add(new ReviewResponse("0222520", "User one", "address of one", "2514", "This is the review from the customer one."));
        reviewResponseList.add(new ReviewResponse("5454851", "User two", "address of two", "2514", "This is the review from the customer one."));
        reviewResponseList.add(new ReviewResponse("2125648", "User three", "address of three", "2514", "This is the review from the customer one."));
        reviewResponseList.add(new ReviewResponse("2125648", "User four", "address of four", "2514", "This is the review from the customer one."));
        reviewResponseList.add(new ReviewResponse("2125648", "User five", "address of five", "2514", "This is the review from the customer one."));

        ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviewResponseList);
        rvReview.setAdapter(reviewAdapter);
        rvReview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
