package com.example.infocovid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import com.example.infocovid.R;
import com.example.infocovid.adapter.DataProvinsiAdapter;
import com.example.infocovid.model.DataIndo;
import com.example.infocovid.viewmodel.DataIndoViewModel;
import java.util.ArrayList;

public class DaftarProvinsiActivity extends AppCompatActivity {

    private RecyclerView rvProvinsi;
    private DataProvinsiAdapter dataProvinsiAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_provinsi);

        rvProvinsi = findViewById(R.id.rv_provinsi);
        progressBar = findViewById(R.id.progress_bar);
        ImageButton btnBack = findViewById(R.id.btn_back);

        rvProvinsi.setLayoutManager(new LinearLayoutManager(this));
        dataProvinsiAdapter = new DataProvinsiAdapter();
        DataIndoViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DataIndoViewModel.class);

        showLoading(true);
        viewModel.setMutableLiveDataProvinsi();
        viewModel.getDataProvinsi().observe(this, new Observer<ArrayList<DataIndo>>() {
            @Override
            public void onChanged(ArrayList<DataIndo> data) {
                if (data != null) {
                    rvProvinsi.setAdapter(dataProvinsiAdapter);
                    dataProvinsiAdapter.setData(data);
                    showLoading(false);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
