package com.example.projetofinaljavafx.modelo;

public class Usuario {
    private String login;
    private String senha;
    private int id;

    public String getLogin() { 
        return this.login; 
    }

    public void setLogin(String login) { 
        this.login = login; 
    }

    public String getSenha() { 
        return this.senha; 
    }

    public void setSenha(String senha) { 
        this.senha = senha; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
