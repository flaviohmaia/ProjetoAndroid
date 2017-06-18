package com.example.flavi.projetoandroid.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.flavi.projetoandroid.Entity.Cliente;
import com.example.flavi.projetoandroid.Entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    UsuarioSQLHelper helper;

    public UsuarioDAO(Context context){
        helper = new UsuarioSQLHelper(context);
    }

    private long inserir(Usuario usuario){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UsuarioSQLHelper.COLUNA_USUARIO, usuario.getUsuario());
        cv.put(UsuarioSQLHelper.COLUNA_USUARIO, usuario.getSenha());

        long id = db.insert(UsuarioSQLHelper.TABELA_USUARIO, null, cv);

        if(id != -1){
            usuario.setId(id);
        }

        db.close();
        return id;
    }

    private int atualizar(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UsuarioSQLHelper.COLUNA_USUARIO, usuario.getUsuario());
        cv.put(UsuarioSQLHelper.COLUNA_USUARIO, usuario.getSenha());

        int linhasAfetadas = db.update(UsuarioSQLHelper.TABELA_USUARIO, cv, UsuarioSQLHelper.COLUNA_ID + " =?", new String[]{String.valueOf(usuario.getId())});

        db.close();
        return linhasAfetadas;
    }


    public void salvar(Usuario usuario){
        if(usuario.getId() == 0){
            inserir(usuario);
        }else{
            atualizar(usuario);
        }
    }

    public int excluir(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(UsuarioSQLHelper.TABELA_USUARIO, UsuarioSQLHelper.COLUNA_ID + " =?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return linhasAfetadas;
    }

    public List<Usuario> buscarUsuarios(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + UsuarioSQLHelper.TABELA_USUARIO;
        String[] argumentos = null;

        if(filtro != null){
            sql += "WHERE " + UsuarioSQLHelper.COLUNA_USUARIO + "LIKE ?";
            argumentos = new String[]{filtro};
        }

        sql += "ORDER BY " + ClienteSQLHelper.COLUNA_USUARIO;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_ID));
            String usuario = cursor.getString(cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_USUARIO));
            String senha = cursor.getString(cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_SENHA));

            Usuario usuario1 = new Usuario(id, usuario, senha);
            usuarios.add(usuario1);
        }

        cursor.close();
        db.close();

        return  usuarios;
    }
}
