package com.example.infocovid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.infocovid.R;
import com.example.infocovid.database.UserHelper;
import com.example.infocovid.model.Auth;
import com.example.infocovid.model.User;
import com.example.infocovid.viewmodel.ProvinsiUserViewModel;
import java.util.ArrayList;

public class LandingActivity extends AppCompatActivity {

    private EditText edtNama;
    private AutoCompleteTextView edtProvinsi;
    private String[] arrayListProvinsi = new String[35];
    private ArrayList<User> list;
    private User extraUser;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        edtNama = findViewById(R.id.edt_nama);
        edtProvinsi = findViewById(R.id.edt_provinsi);
        Button btnMasuk = findViewById(R.id.btn_masuk);
        list = new ArrayList<>();
        userHelper = UserHelper.getInstance(this);

        showProvinsi();

        edtProvinsi.setThreshold(1);
        edtProvinsi.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arrayListProvinsi));

        edtProvinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaProv = parent.getItemAtPosition(position).toString();
                for (int i = 0 ; i<list.size() ; i++) {
                    if (namaProv.equals(list.get(i).getProvinsi())) {
                        extraUser = new User();
                        extraUser.setProvinsi(list.get(i).getProvinsi());
                        extraUser.setKode(list.get(i).getKode());
                    }
                }
                userHelper.open();
                extraUser.setNamaUser(edtNama.getText().toString());
                long cek = userHelper.insert(extraUser);
                Auth.user = userHelper.all();
                Log.d("insert",String.valueOf(cek));
                Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNama.getText().toString().isEmpty() && !edtProvinsi.getText().toString().isEmpty()) {
                    Toast.makeText(LandingActivity.this, "Pilih Provinsi", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Toast.makeText(LandingActivity.this, "Isi dengan lengkap", Toast.LENGTH_LONG).show();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void showProvinsi(){
        ProvinsiUserViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ProvinsiUserViewModel.class);
        viewModel.setMutableLiveDataProvinsi();
        viewModel.getDataProvinsi().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> data) {
                if (data != null) {
                    for (int i = 0 ; i<data.size(); i++) {
                        arrayListProvinsi[i] = data.get(i).getProvinsi();
                        User prov = new User();
                        prov.setKode(data.get(i).getKode());
                        prov.setProvinsi(data.get(i).getProvinsi());
                        list.add(prov);
                        Log.d("provinsi:", arrayListProvinsi[i]);
                    }
                }
            }
        });
    }
}
