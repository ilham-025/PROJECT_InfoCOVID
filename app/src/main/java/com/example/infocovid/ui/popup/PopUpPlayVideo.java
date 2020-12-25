package com.example.infocovid.ui.popup;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.infocovid.R;
import com.example.infocovid.model.Inovasi;

public class PopUpPlayVideo {

    private WebView webView;

    public void showPopupWindow(final View view, Inovasi inovasi) {

        final LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View popupView = inflater.inflate(R.layout.popup_play_video, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView txtInovasi = popupView.findViewById(R.id.txt_inovasi);
        txtInovasi.setText(inovasi.getJudul());

        ImageButton buttonBatal = popupView.findViewById(R.id.btn_batal);
        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        webView = popupView.findViewById(R.id.web_view);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setDefaultFontSize(18);

        String kode = inovasi.getKode();
        muatVideo(kode);
    }

    private void muatVideo(String kodeVideo){
        int width = getScreenWidth();
        int height = getScreenHeight();
        Log.d("Width", String.valueOf(width));
        Log.d("Height", String.valueOf(height));
        String kodeHTML = "<center> <iframe width=\""+width/2+"\" height=\""+height/7+"\" src=\"https://www.youtube.com/embed/" +
                kodeVideo+
                "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe> </center>";
        webView.loadData(kodeHTML, "text/html; charset=uft-8", null);
    }
    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
