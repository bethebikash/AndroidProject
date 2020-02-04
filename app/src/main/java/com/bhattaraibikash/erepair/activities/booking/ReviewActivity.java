package com.bhattaraibikash.erepair.activities.booking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.api.ReviewApi;
import com.bhattaraibikash.erepair.responses.ReviewResponse;
import com.bhattaraibikash.erepair.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    private EditText etReview;
    private Button btnSendReview;
    private String service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        getSupportActionBar().setTitle("Service Book");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (!extra.isEmpty()) {
            service = extra.getString("service");
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

        etReview = findViewById(R.id.etReview);
        btnSendReview = findViewById(R.id.btnSendReview);

        btnSendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etReview.getText().toString().isEmpty()){
                    etReview.setError("Your Review is required");
                    etReview.requestFocus();
                } else {
                    sendReview();
                }
            }
        });
    }

    private void sendReview() {
        String review = etReview.getText().toString();

        ReviewApi reviewApi = Url.getInstance().create(ReviewApi.class);
        Call<ReviewResponse> reviewResponseCall = reviewApi.sendReview(Url.token, review, service);

        reviewResponseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ReviewActivity.this, "Review has been sent Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Toast.makeText(ReviewActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
