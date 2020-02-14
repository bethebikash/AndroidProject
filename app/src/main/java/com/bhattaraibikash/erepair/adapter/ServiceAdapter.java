package com.bhattaraibikash.erepair.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.activities.service.ServiceDetailActivity;
import com.bhattaraibikash.erepair.models.Service;
import com.bhattaraibikash.erepair.url.Url;
import com.bumptech.glide.Glide;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    Context context;
    List<Service> serviceList;

    public ServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        final Service service = serviceList.get(position);
        holder.ivService.setImageResource(R.drawable.icon);
        Glide.with(holder.itemView)
                .load(Url.base_url + service.getImage())
                .placeholder(R.drawable.icon)
                .into(holder.ivService);
        holder.tvServiceName.setText(service.getTitle());

        holder.tvCatNameS.setText("Rs. "+service.getPrice());

        holder.cardService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ServiceDetailActivity.class);
                intent.putExtra("_id", service.get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivService;
        TextView tvServiceName;
        TextView tvCatNameS;
        CardView cardService;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivService = itemView.findViewById(R.id.ivService);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvCatNameS = itemView.findViewById(R.id.tvCatNameS);
            cardService = itemView.findViewById(R.id.cardService);
        }
    }
}
