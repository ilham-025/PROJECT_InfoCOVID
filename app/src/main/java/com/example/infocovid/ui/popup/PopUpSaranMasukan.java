package com.example.infocovid.ui.popup;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.infocovid.R;

public class PopUpSaranMasukan {
    public void showPopupWindow(final View view) {

        final LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View popupView = inflater.inflate(R.layout.popup_saran_masukan, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ConstraintLayout btnWA = popupView.findViewById(R.id.send_wa);
        ConstraintLayout btnGmail = popupView.findViewById(R.id.send_email);

        ImageButton buttonBatal = popupView.findViewById(R.id.btn_batal);
        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        btnWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = "Hai... Saya ingin memberikan Saran & Masukan berupa: ";

                    String toNumber = "6281257021757";

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    inflater.getContext().startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String[] strTo = { "ilhamdotcom81@gmail.com" };
                    intent.putExtra(Intent.EXTRA_EMAIL, strTo);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Saran & Masukan");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hai... Saya ingin memberikan Saran & Masukan berupa: ");

                    intent.setType("message/rfc822");

                    intent.setPackage("com.google.android.gm");

                    inflater.getContext().startActivity(intent);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
