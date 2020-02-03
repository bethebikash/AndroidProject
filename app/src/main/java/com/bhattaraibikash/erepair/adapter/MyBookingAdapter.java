package com.bhattaraibikash.erepair.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.booking.ReviewActivity;
import com.bhattaraibikash.erepair.responses.MyBookingResponse;

import java.util.List;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyBookingViewHolder> {

    Context context;
    List<MyBookingResponse> myBookingResponseList;

    public MyBookingAdapter(Context context, List<MyBookingResponse> myBookingResponseList) {
        this.context = context;
        this.myBookingResponseList = myBookingResponseList;
    }

    @NonNull
    @Override
    public MyBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mybooking, parent, false);
        return new MyBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingViewHolder holder, int position) {
        final MyBookingResponse myBookingResponse = myBookingResponseList.get(position);

        holder.tvProblemBook.setText(myBookingResponse.getProblem());
        holder.tvDateBook.setText(myBookingResponse.getDate());
        holder.tvTimeBook.setText(myBookingResponse.getTime());
        holder.tvLocationBook.setText(myBookingResponse.getLocation());
        holder.tvStatusBook.setText(myBookingResponse.getStatus());

        holder.btnReviewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewActivity.class);
//                intent.putExtra("_id", service.get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myBookingResponseList.size();
    }

    public class MyBookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvProblemBook, tvDateBook, tvTimeBook, tvLocationBook, tvStatusBook;
        Button btnReviewBook;

        public MyBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProblemBook = itemView.findViewById(R.id.tvProblemBook);
            tvDateBook = itemView.findViewById(R.id.tvDateBook);
            tvTimeBook = itemView.findViewById(R.id.tvTimeBook);
            tvLocationBook = itemView.findViewById(R.id.tvLocationBook);
            tvStatusBook = itemView.findViewById(R.id.tvStatusBook);
            btnReviewBook = itemView.findViewById(R.id.btnReviewBook);

        }
    }
}
