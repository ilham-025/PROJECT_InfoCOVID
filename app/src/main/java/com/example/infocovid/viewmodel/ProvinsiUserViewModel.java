package com.example.infocovid.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.infocovid.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ProvinsiUserViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> mutableLiveDataProvinsi = new MutableLiveData<>();

    public void setMutableLiveDataProvinsi(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<User> dataUserArrayList = new ArrayList<>();
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
                        User dataUser = new User();
                        dataUser.setKode(object.getInt("kodeProvi"));
                        dataUser.setProvinsi(object.getString("provinsi"));
                        dataUserArrayList.add(dataUser);
                    }
                    mutableLiveDataProvinsi.postValue(dataUserArrayList);
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

    public LiveData<ArrayList<User>> getDataProvinsi() {
        return mutableLiveDataProvinsi;
    }
}
