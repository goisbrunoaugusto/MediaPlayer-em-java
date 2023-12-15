package com.example.projetofinaljavafx.modelo;

/**
 * Classe que representa um usuário.
 */
public class Usuario {
    /**
     * Login do usuário.
     */
    private String login;
    /**
     * Senha do usuário.
     */
    private String senha;
    /**
     * Id do usuário.
     */
    private int id;

    /**
     * Construtor da classe.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param id Id do usuário.
     */
    public String getLogin() { 
        return this.login; 
    }

    /**
     * Altera o login do usuário.
     * @param login Novo login do usuário.
     */
    public void setLogin(String login) { 
        this.login = login; 
    }

    /**
     * Retorna a senha do usuário.
     * @return Senha do usuário.
     */
    public String getSenha() { 
        return this.senha; 
    }

    /**
     * Altera a senha do usuário.
     * @param senha Nova senha do usuário.
     */
    public void setSenha(String senha) { 
        this.senha = senha; 
    }

    /**
     * Retorna o id do usuário.
     * @return Id do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Altera o id do usuário.
     * @param id Novo id do usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

}
