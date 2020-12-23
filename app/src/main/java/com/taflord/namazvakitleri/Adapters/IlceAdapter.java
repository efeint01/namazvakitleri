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
import com.taflord.namazvakitleri.Activity.VakitActivity;
import com.taflord.namazvakitleri.Models.IlceItem;
import com.taflord.namazvakitleri.Models.SehirItem;
import com.taflord.namazvakitleri.R;

import java.util.ArrayList;

public class IlceAdapter extends RecyclerView.Adapter<IlceAdapter.ViewHolder> {

    OnIlceListener mOnIlceListener;
    ArrayList<IlceItem> arrayList;
    Context context;

    public IlceAdapter(Context context, ArrayList<IlceItem> arrayList, OnIlceListener mOnIlceListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.mOnIlceListener = mOnIlceListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view =  LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IlceAdapter.ViewHolder holder, int position) {
            holder.textView.setText(arrayList.get(position).getIlceAdi());
            holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, VakitActivity.class);
            i.putExtra("position", arrayList.get(position).getIlceID());
            i.putExtra("name", arrayList.get(position).getIlceAdi());
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rowItemName);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            System.out.println("onClick: " + getAdapterPosition());
        }
    }

    public void setData(ArrayList<IlceItem> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public interface OnIlceListener {
        void OnIlceClick(int position);
    }
}
