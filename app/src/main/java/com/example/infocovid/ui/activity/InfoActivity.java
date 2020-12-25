package com.example.infocovid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.infocovid.R;
import com.example.infocovid.model.Auth;
import com.example.infocovid.model.User;
import com.example.infocovid.ui.popup.PopUpDeveloper;
import com.example.infocovid.ui.popup.PopUpEditUserClass;
import com.example.infocovid.ui.popup.PopUpSaranMasukan;
import com.example.infocovid.ui.popup.PopUpTentangApp;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView txtNamaUser;
    private static TextView txtProvinsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageButton btnBack = findViewById(R.id.btn_back);
        Button btnEdit = findViewById(R.id.btn_edit);
        txtNamaUser = findViewById(R.id.txt_user_name);
        txtProvinsi = findViewById(R.id.txt_provinsi);
        TextView txtSaran = findViewById(R.id.txt_saran);
        TextView txtTentangApp = findViewById(R.id.txt_tentang_app);
        TextView txtDeveloper = findViewById(R.id.txt_develop);
        TextView txtBagikan = findViewById(R.id.txt_bagikan);

        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        txtSaran.setOnClickListener(this);
        txtTentangApp.setOnClickListener(this);
        txtDeveloper.setOnClickListener(this);
        txtBagikan.setOnClickListener(this);
        txtNamaUser.setText(Auth.user.getNamaUser());
        txtProvinsi.setText(Auth.user.getProvinsi());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            this.finish();
        } else if (v.getId() == R.id.btn_edit) {
            PopUpEditUserClass popUpEditUserClass = new PopUpEditUserClass();
            popUpEditUserClass.showPopupWindow(v);
        } else if (v.getId() == R.id.txt_saran) {
            PopUpSaranMasukan popUpSaranMasukan = new PopUpSaranMasukan();
            popUpSaranMasukan.showPopupWindow(v);
        } else if (v.getId() == R.id.txt_tentang_app) {
            PopUpTentangApp popUpTentangApp = new PopUpTentangApp();
            popUpTentangApp.showPopupWindow(v);
        } else if (v.getId() == R.id.txt_develop) {
            PopUpDeveloper popUpDeveloper = new PopUpDeveloper();
            popUpDeveloper.showPopupWindow(v);
        } else if (v.getId() == R.id.txt_bagikan) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Download Aplikasi di Sini: ");
            i.putExtra(Intent.EXTRA_TEXT, "https://bit.ly/InfoCOVID19APK");
            startActivity(Intent.createChooser(i, "Bagikan Aplikasi"));
        }
    }

    public static class LoadAsyncTask extends AsyncTask<Void, Void, User> {

        @Override
        protected User doInBackground(Void... voids) {
            return Auth.user;
        }

        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            txtNamaUser.setText(Auth.user.getNamaUser());
            txtProvinsi.setText(Auth.user.getProvinsi());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        txtNamaUser.setText(Auth.user.getNamaUser());
        txtProvinsi.setText(Auth.user.getProvinsi());
    }
}
