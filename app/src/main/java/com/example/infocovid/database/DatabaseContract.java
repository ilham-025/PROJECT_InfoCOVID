package com.example.infocovid.database;

import android.provider.BaseColumns;

class DatabaseContract {
    static final String TABLE_USER = "users";
    static final class UserColumns implements BaseColumns {
        static String NAMA = "nama";
        static String PROVINSI = "provinsi";
        static String KODE = "kode";
    }
}
