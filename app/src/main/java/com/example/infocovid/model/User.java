package com.example.infocovid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String provinsi, namaUser;
    private int kode;

    private User(Parcel in) {
        provinsi = in.readString();
        kode = in.readInt();
        namaUser = in.readString();
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public User() {}

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(provinsi);
        dest.writeInt(kode);
        dest.writeString(namaUser);
    }
}
