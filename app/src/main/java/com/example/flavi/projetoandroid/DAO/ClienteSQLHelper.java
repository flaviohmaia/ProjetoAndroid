package com.example.flavi.projetoandroid.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClienteSQLHelper extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "db_projeto";
    public static final int VERSAO_BANCO = 1;

    public static final String TABELA_CLIENTE = "tb_cliente";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_APELIDO = "apelido";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_CATEGORIA = "categoria";
    public static final String COLUNA_SUBCATEGORIA = "sub_categoria";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_USUARIO = "usuario";
    public static final String COLUNA_TELEFONE = "telefone";

    public ClienteSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " +TABELA_CLIENTE+ "(" +
                 COLUNA_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                 COLUNA_NOME + "TEXT" +
                 COLUNA_APELIDO + "TEXT" +
                 COLUNA_CATEGORIA +"TEXT"+
                 COLUNA_TELEFONE +"TEXT"+
                 COLUNA_DESCRICAO +"TEXT"+
                 COLUNA_SUBCATEGORIA +"TEXT"+
                 COLUNA_USUARIO +"REAL"+
                 COLUNA_ENDERECO +"TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
