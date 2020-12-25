package com.example.infocovid.viewmodel;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.infocovid.model.DataIndo;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DataIndoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<DataIndo>> mutableLiveDataProvinsi = new MutableLiveData<>();
    private MutableLiveData<DataIndo> mutableLiveDataIndo = new MutableLiveData<>();

    public void setMutableLiveDataProvinsi(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<DataIndo> dataProvinsiArrayList = new ArrayList<>();
        String url = "https://indonesia-covid-19.mathdro.id/api/provinsi";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("data");
                    for (int i =0 ; i<list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        DataIndo dataIndo = new DataIndo();
                        dataIndo.setKode(object.getInt("kodeProvi"));
                        dataIndo.setProvinsi(object.getString("provinsi"));
                        dataIndo.setPositif(object.getInt("kasusPosi"));
                        dataIndo.setSembuh(object.getInt("kasusSemb"));
                        dataIndo.setMeninggal(object.getInt("kasusMeni"));
                        dataProvinsiArrayList.add(dataIndo);
                    }
                    mutableLiveDataProvinsi.postValue(dataProvinsiArrayList);
                } catch (JSONException e) {
                    Log.d("Error ", e.toString());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure ", error.toString());
            }
        });
    }

    public LiveData<ArrayList<DataIndo>> getDataProvinsi() {
        return mutableLiveDataProvinsi;
    }

    public void setMutableLiveDataIndo(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://indonesia-covid-19.mathdro.id/api";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    DataIndo dataIndo = new DataIndo();
                    dataIndo.setPositif(jsonObject.getInt("jumlahKasus"));
                    dataIndo.setSembuh(jsonObject.getInt("sembuh"));
                    dataIndo.setMeninggal(jsonObject.getInt("meninggal"));
                    mutableLiveDataIndo.postValue(dataIndo);
                } catch (JSONException e) {
                    Log.d("Error ", e.toString());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure ", error.toString());
            }
        });
    }

    public LiveData<DataIndo> getDataIndo() {
        return mutableLiveDataIndo;
    }

}
