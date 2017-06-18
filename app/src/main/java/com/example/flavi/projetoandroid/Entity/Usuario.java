package com.example.flavi.projetoandroid.Entity;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Long id;
    private String usuario;
    private String senha;

    public Usuario(Long id, String usuario, String senha){
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }


    //GETTERS E SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
