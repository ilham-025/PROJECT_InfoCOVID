package com.example.infocovid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataDunia implements Parcelable {
    private int kasus, positif, sembuh, meninggal;

    private DataDunia(Parcel in) {
        kasus = in.readInt();
        positif = in.readInt();
        sembuh = in.readInt();
        meninggal = in.readInt();
    }

    public DataDunia(){ }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(kasus);
        dest.writeInt(positif);
        dest.writeInt(sembuh);
        dest.writeInt(meninggal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataDunia> CREATOR = new Creator<DataDunia>() {
        @Override
        public DataDunia createFromParcel(Parcel in) {
            return new DataDunia(in);
        }

        @Override
        public DataDunia[] newArray(int size) {
            return new DataDunia[size];
        }
    };

    public int getKasus() {
        return kasus;
    }

    public void setKasus(int kasus) {
        this.kasus = kasus;
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
}
