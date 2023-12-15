package com.example.projetofinaljavafx.modelo;

/**
 * Classe que representa uma música.
 */
public class Musica {
    /**
     * Nome da música.
     */
    private String nome;
    /**
     * Caminho para o arquivo da música.
     */
    private String caminho;

    /**
     * Construtor da classe.
     * @param nome Nome da música.
     * @param caminho Caminho para o arquivo da música.
     */
    public Musica(String nome, String caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }

    /**
     * Retorna o nome da música.
     * @return Nome da música.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o caminho para o arquivo da música.
     * @return Caminho para o arquivo da música.
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * Altera o nome da música.
     * @param nome Novo nome da música.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Altera o caminho para o arquivo da música.
     * @param caminho Novo caminho para o arquivo da música.
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    /**
     * Retorna o nome da música.
     * @return Nome da música.
     */
    @Override
    public String toString() {
        return nome;
    }
}
