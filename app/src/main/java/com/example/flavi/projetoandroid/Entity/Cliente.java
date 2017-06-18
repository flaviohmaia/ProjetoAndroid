package com.example.flavi.projetoandroid.Entity;


import java.io.Serializable;

public class Cliente implements Serializable{

    private Long id;
    private String nome;
    private String categoria;
    private String subCategoria;
    private String endereco;
    private String apelido;
    private String telefone;
    private String descricao;

    public Cliente(String nome, String apelido, String categoria, String subCategoria, String telefone, String descricao, String endereco){
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.categoria = categoria;
        this.subCategoria = subCategoria;
        this.telefone = telefone;
        this.descricao = descricao;
        this.endereco = endereco;
    }


    //GETTERS E SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
