package com.example.infocovid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.infocovid.R;
import com.example.infocovid.model.DataIndo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DataProvinsiAdapter extends RecyclerView.Adapter<DataProvinsiAdapter.ListViewHolder> {

    private final ArrayList<DataIndo> listProvinsi = new ArrayList<>();

    public void setData(ArrayList<DataIndo> list){
        listProvinsi.clear();
        listProvinsi.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DataProvinsiAdapter.ListViewHolder holder, int position) {
        DataIndo provinsi = listProvinsi.get(position);

        holder.txtProvinsi.setText(provinsi.getProvinsi());
        holder.txtKonfirmasi.setText(convert(provinsi.getPositif()));
        holder.txtSembuh.setText(convert(provinsi.getSembuh()));
        holder.txtMeninggal.setText(convert(provinsi.getMeninggal()));

    }

    @Override
    public int getItemCount() {
        return listProvinsi.size();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_provinsi, viewGroup, false);
        return new ListViewHolder(view);
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtProvinsi, txtKonfirmasi, txtSembuh, txtMeninggal;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProvinsi = itemView.findViewById(R.id.txt_provinsi);
            txtKonfirmasi = itemView.findViewById(R.id.txt_konf_prof);
            txtSembuh = itemView.findViewById(R.id.txt_sembuh_kaltim);
            txtMeninggal = itemView.findViewById(R.id.txt_meninggal_kaltim);
        }
    }

    private String convert(int angka){
        String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
        ganti = ganti.replace("," , ".");
        return ganti;
    }
}
