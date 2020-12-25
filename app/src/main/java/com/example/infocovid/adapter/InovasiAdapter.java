package com.example.infocovid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.R;
import com.example.infocovid.model.Inovasi;

import java.util.ArrayList;

public class InovasiAdapter extends RecyclerView.Adapter<InovasiAdapter.ListViewHolder> {

    private ArrayList<Inovasi> listInovasi;
    private OnClickListener onClickListener;

    public InovasiAdapter(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public InovasiAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inovasi, parent, false);
        return new InovasiAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InovasiAdapter.ListViewHolder holder, int position) {
        Inovasi inovasi = listInovasi.get(position);
        holder.txtInovasi.setText(inovasi.getJudul());
    }

    @Override
    public int getItemCount() {
        return listInovasi.size();
    }

    public void setListInovasi(ArrayList<Inovasi> listInovasi){
        this.listInovasi = listInovasi;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtInovasi;
        Button btnPlay;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInovasi = itemView.findViewById(R.id.txt_inovasi);
            btnPlay = itemView.findViewById(R.id.btn_play);
            btnPlay.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(listInovasi.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener{
        void onClick(Inovasi inovasi);
    }
}
