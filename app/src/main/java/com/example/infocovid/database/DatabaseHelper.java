package com.example.infocovid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbinfocovid";
    private static final int DATABASE_VERSION = 1;
    private static final String  SQL_CREATE_USER = String.format("CREATE TABLE %s"+"(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL)",DatabaseContract.TABLE_USER, DatabaseContract.UserColumns._ID, DatabaseContract.UserColumns.NAMA,DatabaseContract.UserColumns.PROVINSI,DatabaseContract.UserColumns.KODE);

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_USER);
    }
}
