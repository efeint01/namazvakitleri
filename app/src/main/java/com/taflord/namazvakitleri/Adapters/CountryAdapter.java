package com.taflord.namazvakitleri.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taflord.namazvakitleri.Activity.IlceActivity;
import com.taflord.namazvakitleri.Activity.SehirActivity;
import com.taflord.namazvakitleri.Models.CountryItem;
import com.taflord.namazvakitleri.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    OnCountryListener mOnCountryListener;
    ArrayList<CountryItem> arrayList;
    Context context;

    public CountryAdapter(Context context, ArrayList<CountryItem> arrayList, OnCountryListener mOnCountryListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.mOnCountryListener = mOnCountryListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view =  LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view, mOnCountryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getUlkeAdi());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SehirActivity.class);
                i.putExtra("position", arrayList.get(position).getUlkeID());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        OnCountryListener onCountryListener;


        public ViewHolder(@NonNull View itemView, OnCountryListener mOnCountryListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.rowItemName);
            this.onCountryListener = mOnCountryListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }

    public void setData(ArrayList<CountryItem> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public interface OnCountryListener {
        void OnCountryClick(int position);
    }
}
