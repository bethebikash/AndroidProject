package com.bhattaraibikash.erepair.adapter;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.booking.ReviewActivity;
import com.bhattaraibikash.erepair.api.BookingApi;
import com.bhattaraibikash.erepair.notification.CreateChannel;
import com.bhattaraibikash.erepair.responses.MyBookingResponse;
import com.bhattaraibikash.erepair.url.Url;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyBookingViewHolder> {

    Context context;
    List<MyBookingResponse> myBookingResponseList;
    private NotificationManagerCompat notificationManagerCompat;
    private int count = 1;

    public MyBookingAdapter(Context context, List<MyBookingResponse> myBookingResponseList) {
        this.context = context;
        this.myBookingResponseList = myBookingResponseList;
    }

    @NonNull
    @Override
    public MyBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mybooking, parent, false);

        notificationManagerCompat = NotificationManagerCompat.from(context);
        CreateChannel channel = new CreateChannel(context);
        channel.createChannel();

        return new MyBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingViewHolder holder, final int position) {
        final MyBookingResponse myBookingResponse = myBookingResponseList.get(position);

        holder.tvProblemBook.setText(myBookingResponse.getProblem());
        holder.tvDateBook.setText("Date: "+myBookingResponse.getDate());
        holder.tvTimeBook.setText("Time: "+myBookingResponse.getTime());
        holder.tvLocationBook.setText("Location: "+myBookingResponse.getLocation());
        holder.tvStatusBook.setText(myBookingResponse.getStatus());

                Gson gson = new Gson();
        String json = gson.toJson(myBookingResponse.getService()); //convert

        String[] arrayString = json.split("\"");
        final String data = arrayString[3];


        holder.btnReviewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewActivity.class);
                intent.putExtra("service", data);
                context.startActivity(intent);
            }
        });

        holder.btnCancelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
                CancelBook(myBookingResponse.get_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return myBookingResponseList.size();
    }

    public class MyBookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvProblemBook, tvDateBook, tvTimeBook, tvLocationBook, tvStatusBook;
        Button btnReviewBook, btnCancelBook;

        public MyBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProblemBook = itemView.findViewById(R.id.tvProblemBook);
            tvDateBook = itemView.findViewById(R.id.tvDateBook);
            tvTimeBook = itemView.findViewById(R.id.tvTimeBook);
            tvLocationBook = itemView.findViewById(R.id.tvLocationBook);
            tvStatusBook = itemView.findViewById(R.id.tvStatusBook);
            btnReviewBook = itemView.findViewById(R.id.btnReviewBook);
            btnCancelBook = itemView.findViewById(R.id.btnCancelBook);

        }
    }

    private void CancelBook(String _id){
        BookingApi bookingApi = Url.getInstance().create(BookingApi.class);

        Call<Void> voidCall = bookingApi.deleteBooking(_id);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> voidCall, Response<Void> response) {
                Toast.makeText(context, "Booking has been Canceled", Toast.LENGTH_SHORT).show();
                BookingCancelNotification();
            }

            @Override
            public void onFailure(Call<Void> voidCall, Throwable t) {
                Toast.makeText(context, "failed:" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteItem(int position) {
        myBookingResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, myBookingResponseList.size());
    }

    private  void BookingCancelNotification() {
        Notification notification = new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Booking Cancel")
                .setContentText("Your booking has been canceled")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(count, notification);
    }
}
