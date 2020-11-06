package com.example.appswipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DirecaoDAO {

    public static void inserir(Context context, Direcao direcao ) {

        ContentValues valores = new ContentValues();
        valores.put("direcao",direcao.getDirecao());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("direcao",null ,valores);

    }
    public static void excluir(Context context, int id){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("direcao", " id = " + id, null);

    }

    public static List<Direcao> getDirecao(Context context){
        List<Direcao> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM direcao ORDER BY id",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                Direcao d = new Direcao();
                d.setId(cursor.getInt(0));
                d.setDirecao(cursor.getString(1));
                lista.add(d);
            }while(cursor.moveToNext());

        }

        return lista;
    }

}

