package com.example.infocovid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataIndo implements Parcelable {
    private int positif, sembuh, meninggal, kode, kasusToday, meninggalToday;
    private String provinsi;

    private DataIndo(Parcel in) {
        positif = in.readInt();
        sembuh = in.readInt();
        meninggal = in.readInt();
        kode = in.readInt();
        kasusToday = in.readInt();
        meninggalToday = in.readInt();
        provinsi = in.readString();
    }

    public DataIndo(){}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(positif);
        dest.writeInt(sembuh);
        dest.writeInt(meninggal);
        dest.writeInt(kode);
        dest.writeInt(kasusToday);
        dest.writeInt(meninggalToday);
        dest.writeString(provinsi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataIndo> CREATOR = new Creator<DataIndo>() {
        @Override
        public DataIndo createFromParcel(Parcel in) {
            return new DataIndo(in);
        }

        @Override
        public DataIndo[] newArray(int size) {
            return new DataIndo[size];
        }
    };

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public int getPositif() {
        return positif;
    }

    public void setPositif(int positif) {
        this.positif = positif;
    }

    public int getSembuh() {
        return sembuh;
    }

    public void setSembuh(int sembuh) {
        this.sembuh = sembuh;
    }

    public int getMeninggal() {
        return meninggal;
    }

    public void setMeninggal(int meninggal) {
        this.meninggal = meninggal;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public int getKasusToday() {
        return kasusToday;
    }

    public void setKasusToday(int kasusToday) {
        this.kasusToday = kasusToday;
    }

    public int getMeninggalToday() {
        return meninggalToday;
    }

    public void setMeninggalToday(int meninggalToday) {
        this.meninggalToday = meninggalToday;
    }

}
