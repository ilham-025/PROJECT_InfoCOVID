package com.example.infocovid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Inovasi implements Parcelable {
    private String judul, kode;

    public Inovasi(){}

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    private Inovasi(Parcel in) {
        judul = in.readString();
        kode = in.readString();
    }

    public static final Creator<Inovasi> CREATOR = new Creator<Inovasi>() {
        @Override
        public Inovasi createFromParcel(Parcel in) {
            return new Inovasi(in);
        }

        @Override
        public Inovasi[] newArray(int size) {
            return new Inovasi[size];
        }
    };

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(kode);
    }
}
