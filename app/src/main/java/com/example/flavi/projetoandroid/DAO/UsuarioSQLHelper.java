package com.example.flavi.projetoandroid.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioSQLHelper extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "db_projeto";
    public static final int VERSAO_BANCO = 1;

    public static final String TABELA_USUARIO = "tb_usuario";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_USUARIO = "usuario";
    public static final String COLUNA_SENHA = "senha";

    public UsuarioSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " +TABELA_USUARIO+ "(" +
                COLUNA_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUNA_USUARIO + "TEXT" +
                COLUNA_SENHA + "TEXT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
