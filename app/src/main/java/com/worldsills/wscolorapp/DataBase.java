package com.worldsills.wscolorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {


    private static final String name= "colorapp.db";
    private static final int version= 1;
    private static String table = "CREATE TABLE PARTIDA (ID INTEGER PRIMARY KEY AUTOINCREMENT, PUNTAJE INTEGER)";

    public DataBase(Context context) {
        super(context, name, null, version);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CREATE"+table);
        db.execSQL(table);
    }

    public void guardar(int puntaje){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("PUNTAJE", puntaje);

        db.insert("PARTIDA", null, values);

    }


    public Cursor cargar(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;


        String find [] = {"PUNTAJE"};
        String orderBy = "DESC";
        String limit = "5";

        try {
            cursor= db.query("PARTIDA", find, null, null, null, orderBy, limit);
            return cursor;
        } catch (Exception e){}

        return null;

    }
}
