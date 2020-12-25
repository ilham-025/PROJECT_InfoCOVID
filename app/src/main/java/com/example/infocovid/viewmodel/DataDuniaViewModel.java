package com.example.infocovid.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.infocovid.model.DataDunia;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class DataDuniaViewModel extends ViewModel {

    private MutableLiveData<DataDunia> mutableLiveDataWorld = new MutableLiveData<>();

    public void setMutableLiveDataWorld(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://corona.lmao.ninja/v2/all?today";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    DataDunia dataDunia = new DataDunia();
                    dataDunia.setKasus(jsonObject.getInt("todayCases"));
                    dataDunia.setPositif(jsonObject.getInt("cases"));
                    dataDunia.setSembuh(jsonObject.getInt("recovered"));
                    dataDunia.setMeninggal(jsonObject.getInt("deaths"));
                    mutableLiveDataWorld.postValue(dataDunia);
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

    public LiveData<DataDunia> getDataWorld() {
        return mutableLiveDataWorld;
    }
}
