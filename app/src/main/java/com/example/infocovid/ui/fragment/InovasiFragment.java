package com.example.infocovid.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.infocovid.R;
import com.example.infocovid.adapter.InovasiAdapter;
import com.example.infocovid.model.Inovasi;
import com.example.infocovid.ui.popup.PopUpPlayVideo;
import java.util.ArrayList;
import java.util.Objects;

public class InovasiFragment extends Fragment implements InovasiAdapter.OnClickListener {

    private RecyclerView rvInovasi;
    private ArrayList<Inovasi> listInovasi = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inovasi, container, false);
        rvInovasi = view.findViewById(R.id.rv_inovasi);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listInovasi.addAll(getListInovasi());
        showRecyclerView();
    }

    private ArrayList<Inovasi> getListInovasi(){
        String[] dataJudul = getResources().getStringArray(R.array.inovasi_name);
        String[] dataKode = getResources().getStringArray(R.array.inovasi_kode);

        ArrayList<Inovasi> list = new ArrayList<>();
        for (int i = 0 ; i<dataJudul.length; i++){
            Inovasi inovasi = new Inovasi();
            inovasi.setJudul(dataJudul[i]);
            inovasi.setKode(dataKode[i]);

            list.add(inovasi);
        }
        return list;
    }

    private void showRecyclerView(){
        rvInovasi.setLayoutManager(new LinearLayoutManager(getContext()));

        InovasiAdapter inovasiAdapter = new InovasiAdapter(this);
        inovasiAdapter.setListInovasi(listInovasi);
        rvInovasi.setAdapter(inovasiAdapter);
    }

    @Override
    public void onClick(Inovasi inovasi) {
        PopUpPlayVideo popUpPlayVideo = new PopUpPlayVideo();
        popUpPlayVideo.showPopupWindow(Objects.requireNonNull(getView()), inovasi);
    }
}
