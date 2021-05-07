package com.example.infocovid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.infocovid.model.User;
import static com.example.infocovid.database.DatabaseContract.UserColumns.KODE;
import static com.example.infocovid.database.DatabaseContract.UserColumns.NAMA;
import static com.example.infocovid.database.DatabaseContract.UserColumns.PROVINSI;

public class UserHelper {
    private static DatabaseHelper databaseHelper;
    private static UserHelper INSTANCE;
    private static SQLiteDatabase database;

    private UserHelper(Context context){
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public static synchronized UserHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new UserHelper(context);
        }
        return INSTANCE;
    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if(database.isOpen()){
            database.close();
        }
    }


    public User all(){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ DatabaseContract.TABLE_USER,null);
        cursor.moveToFirst();
        Log.d("panjang curosr",String.valueOf(cursor.getCount()));
        User user = new User();
        if(cursor.getCount()>0){
            do{
                user.setNamaUser(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                user.setProvinsi(cursor.getString(cursor.getColumnIndexOrThrow(PROVINSI)));
                user.setKode(cursor.getInt(cursor.getColumnIndexOrThrow(KODE)));
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return user;
    }

    public long insert(User user){
        ContentValues arg = new ContentValues();
        long result;
        arg.put(NAMA,user.getNamaUser());
        arg.put(PROVINSI,user.getProvinsi());
        arg.put(KODE,user.getKode());
        result = database.insert(DatabaseContract.TABLE_USER, null, arg);
        return result;
    }

    public long update(User user){
        ContentValues arg = new ContentValues();
        long result;
        arg.put(NAMA,user.getNamaUser());
        arg.put(PROVINSI,user.getProvinsi());
        arg.put(KODE,user.getKode());
        String where = "rowid=(SELECT MIN(rowid) FROM " + DatabaseContract.TABLE_USER + ")";
        result = database.update(DatabaseContract.TABLE_USER, arg, where, null);
        return result;
    }

    public Boolean checkIfExists(){
        boolean exits = false;
        Cursor cursor = database.rawQuery("SELECT * FROM "+ DatabaseContract.TABLE_USER,null);

        if(cursor.getCount()>0){
            exits = true;
        }
        return  exits;
    }

    public int delete(String nama){
        return database.delete(DatabaseContract.TABLE_USER,NAMA+"='"+nama+"'",null);
    }
}
