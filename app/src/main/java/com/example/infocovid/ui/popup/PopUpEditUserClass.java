package com.example.infocovid.ui.popup;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.example.infocovid.R;
import com.example.infocovid.database.UserHelper;
import com.example.infocovid.model.Auth;
import com.example.infocovid.model.User;
import com.example.infocovid.ui.activity.InfoActivity;
import com.example.infocovid.viewmodel.ProvinsiUserViewModel;
import java.util.ArrayList;

public class PopUpEditUserClass {

    public void showPopupWindow(final View view) {

        final LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View popupView = inflater.inflate(R.layout.popup_edit_user,null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        final EditText edtNama = popupView.findViewById(R.id.edt_nama);
        final AutoCompleteTextView edtProvinsi = popupView.findViewById(R.id.edt_provinsi);

        edtNama.setText(Auth.user.getNamaUser());
        edtProvinsi.setHint(Auth.user.getProvinsi());

        final String[] arrayListProvinsi = new String[35];
        final ArrayList<User> list = new ArrayList<>();

        ProvinsiUserViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) view.getContext(), new ViewModelProvider.NewInstanceFactory()).get(ProvinsiUserViewModel.class);
        viewModel.setMutableLiveDataProvinsi();
        viewModel.getDataProvinsi().observe((LifecycleOwner) view.getContext(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> user) {
                if (user != null) {
                    for (int i = 0; i< user.size(); i++) {
                        arrayListProvinsi[i] = user.get(i).getProvinsi();
                        User prov = new User();
                        prov.setKode(user.get(i).getKode());
                        prov.setProvinsi(user.get(i).getProvinsi());
                        list.add(prov);
                    }
                }
            }
        });

        final UserHelper userHelper = UserHelper.getInstance(view.getContext());
        final User[] exstraUser = new User[1];
        edtProvinsi.setThreshold(1);
        edtProvinsi.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_expandable_list_item_1, arrayListProvinsi));
        edtProvinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaProv = parent.getItemAtPosition(position).toString();
                for (int i = 0 ; i<list.size() ; i++) {
                    if (namaProv.equals(list.get(i).getProvinsi())) {
                        exstraUser[0] = new User();
                        exstraUser[0].setProvinsi(list.get(i).getProvinsi());
                        exstraUser[0].setKode(list.get(i).getKode());
                    }
                }
                userHelper.open();
                exstraUser[0].setNamaUser(edtNama.getText().toString());
                long cek = userHelper.update(exstraUser[0]);
                Auth.user = userHelper.all();
                Log.d("update", String.valueOf(cek));
                Toast.makeText(view.getContext(), "Berhasil Mengubah", Toast.LENGTH_SHORT).show();
                new InfoActivity.LoadAsyncTask().execute();
                popupWindow.dismiss();
            }
        });

        Button buttonEdit = popupView.findViewById(R.id.btn_ubah);
        ImageButton buttonBatal = popupView.findViewById(R.id.btn_batal);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNama.getText().toString().isEmpty() && !edtProvinsi.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Pilih Provinsi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Isi dengan lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}