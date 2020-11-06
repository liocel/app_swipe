package com.example.appswipe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static  final String NOME_BANCO = "listacodigo";
    private static  final int VERSAO_BANCO = 1;

    public Banco(Context context){
        super(context, NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS direcao(" +
                " id integer NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " direcao TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}