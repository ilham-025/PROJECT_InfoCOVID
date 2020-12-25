package com.example.infocovid.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.infocovid.R;
import com.example.infocovid.model.DataIndo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DataProvinsiAdapter extends RecyclerView.Adapter<DataProvinsiAdapter.ListViewHolder> {

    private ArrayList<DataIndo> listProvinsi = new ArrayList<>();

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

        holder.constraintLayout.setMinWidth(getScreenWidth()/3-55);
        holder.constraintLayout2.setMinWidth(getScreenWidth()/3-55);
        holder.constraintLayout3.setMinWidth(getScreenWidth()/3-55);

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
        private ConstraintLayout constraintLayout, constraintLayout2, constraintLayout3;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProvinsi = itemView.findViewById(R.id.txt_provinsi);
            txtKonfirmasi = itemView.findViewById(R.id.txt_konf_prof);
            txtSembuh = itemView.findViewById(R.id.txt_sembuh_kaltim);
            txtMeninggal = itemView.findViewById(R.id.txt_meninggal_kaltim);
            constraintLayout = itemView.findViewById(R.id.linearLayout);
            constraintLayout2 = itemView.findViewById(R.id.linearLayout2);
            constraintLayout3 = itemView.findViewById(R.id.constraintLayout3);
        }
    }

    private String convert(int angka){
        String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
        ganti = ganti.replace("," , ".");
        return ganti;
    }
    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
