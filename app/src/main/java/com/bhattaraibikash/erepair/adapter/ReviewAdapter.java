package com.bhattaraibikash.erepair.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.responses.ReviewResponse;
import com.google.gson.Gson;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context context;
    List<ReviewResponse> reviewResponseList;

    public ReviewAdapter(Context context, List<ReviewResponse> reviewResponseList) {
        this.context = context;
        this.reviewResponseList = reviewResponseList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        final ReviewResponse reviewResponse = reviewResponseList.get(position);

        Gson gson = new Gson();
        String json = gson.toJson(reviewResponse.getUser()); //convert

        String[] arrayString = json.split("\"");
        String address = arrayString[7];
        String name = arrayString[11];

        holder.tvUserFullName.setText(name);
        holder.tvUserAddress.setText(address);

        String[] isodate = reviewResponse.getCreatedAt().split("T");
        String date = isodate[0];

        holder.tvDateOfReview.setText(date);
        holder.tvReview.setText(reviewResponse.getReview());

    }

    @Override
    public int getItemCount() {
        return reviewResponseList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserFullName, tvUserAddress, tvDateOfReview, tvReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserFullName = itemView.findViewById(R.id.tvUserFullName);
            tvUserAddress = itemView.findViewById(R.id.tvUserAddress);
            tvDateOfReview = itemView.findViewById(R.id.tvDateOfReview);
            tvReview = itemView.findViewById(R.id.tvReview);

        }
    }
}
