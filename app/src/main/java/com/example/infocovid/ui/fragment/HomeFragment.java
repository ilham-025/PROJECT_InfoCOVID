package com.example.infocovid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.infocovid.R;
import com.example.infocovid.model.Auth;
import com.example.infocovid.model.DataDunia;
import com.example.infocovid.model.DataIndo;
import com.example.infocovid.ui.activity.DaftarProvinsiActivity;
import com.example.infocovid.viewmodel.DataDuniaViewModel;
import com.example.infocovid.viewmodel.DataIndoViewModel;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView txtKonfKaltim, txtSembuhKaltim, txtMeninggalKaltim;
    private TextView txtKonfIndo, txtSembuhIndo, txtMeninggalIndo;
    private TextView txtKonfWorld, txtSembuhWorld, txtMeninggalWorld, txtBaruWorld;
    private TextView txtTanggal, txtProvinsi, txtNamaUser, txtLihatSemua;
    private DataIndoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        txtKonfKaltim = view.findViewById(R.id.txt_konf_kaltim);
        txtSembuhKaltim = view.findViewById(R.id.txt_sembuh_kaltim);
        txtMeninggalKaltim = view.findViewById(R.id.txt_meninggal_kaltim);
        txtKonfIndo = view.findViewById(R.id.txt_konf_indo);
        txtSembuhIndo = view.findViewById(R.id.txt_sembuh_indo);
        txtMeninggalIndo = view.findViewById(R.id.txt_meninggal_indo);
        txtKonfWorld = view.findViewById(R.id.txt_konf_dunia);
        txtSembuhWorld = view.findViewById(R.id.txt_sembuh_dunia);
        txtMeninggalWorld = view.findViewById(R.id.txt_meninggal_dunia);
        txtBaruWorld = view.findViewById(R.id.txt_kasus_dunia);
        txtTanggal = view.findViewById(R.id.txt_tanggal);
        txtProvinsi = view.findViewById(R.id.txt_provinsi);
        txtNamaUser = view.findViewById(R.id.txt_user_name);
        txtLihatSemua = view.findViewById(R.id.txt_lihat_semua);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DataDuniaViewModel viewModelDataDunia = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DataDuniaViewModel.class);
        viewModelDataDunia.setMutableLiveDataWorld();
        viewModelDataDunia.getDataWorld().observe(getViewLifecycleOwner(), new Observer<DataDunia>() {
            @Override
            public void onChanged(DataDunia data) {
                if (data != null) {
                    txtKonfWorld.setText(convert(data.getPositif()));
                    txtSembuhWorld.setText(convert(data.getSembuh()));
                    txtMeninggalWorld.setText(convert(data.getMeninggal()));
                    txtBaruWorld.setText(convert(data.getKasus()));
                }
            }
        });

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DataIndoViewModel.class);

        viewModel.setMutableLiveDataIndo();
        viewModel.getDataIndo().observe(getViewLifecycleOwner(), new Observer<DataIndo>() {
            @Override
            public void onChanged(DataIndo data) {
                if (data != null) {
                    txtKonfIndo.setText(convert(data.getPositif()));
                    txtSembuhIndo.setText(convert(data.getSembuh()));
                    txtMeninggalIndo.setText(convert(data.getMeninggal()));
                }
            }
        });

        viewModel.setMutableLiveDataProvinsi();
        viewModel.getDataProvinsi().observe(getViewLifecycleOwner(), new Observer<ArrayList<DataIndo>>() {
            @Override
            public void onChanged(ArrayList<DataIndo> data) {
                if (data != null) {
                    for (int i = 0 ; i<data.size(); i++) {
                        if (data.get(i).getKode() == Auth.user.getKode()) {
                            txtKonfKaltim.setText(convert(data.get(i).getPositif()));
                            txtSembuhKaltim.setText(convert(data.get(i).getSembuh()));
                            txtMeninggalKaltim.setText(convert(data.get(i).getMeninggal()));
                        }
                    }
                }
            }
        });

        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        Date date = new Date();
        txtTanggal.setText(dateFormat.format(date));
        txtProvinsi.setText(Auth.user.getProvinsi());
        txtNamaUser.setText(Auth.user.getNamaUser());

        txtLihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DaftarProvinsiActivity.class);
                startActivity(intent);
            }
        });
    }

    private String convert(int angka){
        String ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
        ganti = ganti.replace("," , ".");
        return ganti;
    }

    @Override
    public void onResume() {
        super.onResume();
        txtNamaUser.setText(Auth.user.getNamaUser());
        txtProvinsi.setText(Auth.user.getProvinsi());
        viewModel.setMutableLiveDataProvinsi();
        viewModel.getDataProvinsi().observe(getViewLifecycleOwner(), new Observer<ArrayList<DataIndo>>() {
            @Override
            public void onChanged(ArrayList<DataIndo> data) {
                if (data != null) {
                    for (int i = 0 ; i<data.size(); i++) {
                        if (data.get(i).getKode() == Auth.user.getKode()) {
                            txtKonfKaltim.setText(convert(data.get(i).getPositif()));
                            txtSembuhKaltim.setText(convert(data.get(i).getSembuh()));
                            txtMeninggalKaltim.setText(convert(data.get(i).getMeninggal()));
                        }
                    }
                }
            }
        });
    }
}
