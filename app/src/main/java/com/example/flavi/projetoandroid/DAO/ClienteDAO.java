package com.example.flavi.projetoandroid.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.flavi.projetoandroid.Entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    ClienteSQLHelper helper;

    public ClienteDAO(Context context){
        helper = new ClienteSQLHelper(context);
    }

    private long inserir(Cliente cliente){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ClienteSQLHelper.COLUNA_NOME, cliente.getNome());
        cv.put(ClienteSQLHelper.COLUNA_APELIDO, cliente.getApelido());
        cv.put(ClienteSQLHelper.COLUNA_CATEGORIA, cliente.getCategoria());
        cv.put(ClienteSQLHelper.COLUNA_SUBCATEGORIA, cliente.getSubCategoria());
        cv.put(ClienteSQLHelper.COLUNA_ENDERECO, cliente.getEndereco());
        cv.put(ClienteSQLHelper.COLUNA_DESCRICAO, cliente.getDescricao());
        cv.put(ClienteSQLHelper.COLUNA_TELEFONE, cliente.getTelefone());

        long id = db.insert(ClienteSQLHelper.TABELA_CLIENTE, null, cv);

        if(id != -1){
            cliente.setId(id);
        }

        db.close();
        return id;
    }

    private int atualizar(Cliente cliente){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ClienteSQLHelper.COLUNA_NOME, cliente.getNome());
        cv.put(ClienteSQLHelper.COLUNA_APELIDO, cliente.getApelido());
        cv.put(ClienteSQLHelper.COLUNA_CATEGORIA, cliente.getCategoria());
        cv.put(ClienteSQLHelper.COLUNA_SUBCATEGORIA, cliente.getSubCategoria());
        cv.put(ClienteSQLHelper.COLUNA_ENDERECO, cliente.getEndereco());
        cv.put(ClienteSQLHelper.COLUNA_DESCRICAO, cliente.getDescricao());
        cv.put(ClienteSQLHelper.COLUNA_TELEFONE, cliente.getTelefone());

        int linhasAfetadas = db.update(ClienteSQLHelper.TABELA_CLIENTE, cv, ClienteSQLHelper.COLUNA_ID + " =?", new String[]{String.valueOf(cliente.getId())});

        db.close();
        return linhasAfetadas;
    }


    public void salvar(Cliente cliente){
        if(cliente.getId() == 0){
            inserir(cliente);
        }else{
            atualizar(cliente);
        }
    }

    public int excluir(Cliente cliente){
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(ClienteSQLHelper.TABELA_CLIENTE, ClienteSQLHelper.COLUNA_ID + " =?", new String[]{String.valueOf(cliente.getId())});
        db.close();
        return linhasAfetadas;
    }

    public List<Cliente> buscarCliente(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + ClienteSQLHelper.TABELA_CLIENTE;
        String[] argumentos = null;

        if(filtro != null){
            sql += "WHERE " + ClienteSQLHelper.COLUNA_NOME + "LIKE ?";
            argumentos = new String[]{filtro};
        }

        sql += "ORDER BY " + ClienteSQLHelper.COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Cliente> clientes = new ArrayList<Cliente>();
        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_ID));
            String nome = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_NOME));
            String apelido = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_APELIDO));
            String categoria = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_CATEGORIA));
            String subCategoria = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_SUBCATEGORIA));
            String telefone = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_TELEFONE));
            String descricao = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_DESCRICAO));
            String endereco = cursor.getString(cursor.getColumnIndex(ClienteSQLHelper.COLUNA_ENDERECO));

            Cliente cliente = new Cliente( nome, apelido, categoria, subCategoria, telefone, descricao, endereco);
            clientes.add(cliente);
        }

        cursor.close();
        db.close();

        return  clientes;
    }
}
