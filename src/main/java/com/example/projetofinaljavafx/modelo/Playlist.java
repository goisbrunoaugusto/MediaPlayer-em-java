package com.example.projetofinaljavafx.modelo;

import java.util.ArrayList;

/**
 * Classe que representa uma playlist.
 */
public class Playlist {
    /**
     * Título da playlist.
     */
    private String titulo;
    /**
     * Lista de músicas da playlist.
     */
    private ArrayList<Musica> musicas;
    /**
     * Usuário dono da playlist.
     */
    private Usuario usuarioDono;

    /**
     * Construtor da classe.
     * @param titulo Título da playlist.
     * @param musicas Lista de músicas da playlist.
     * @param usuarioDono Usuário dono da playlist.
     */
    public Playlist(String titulo, ArrayList<Musica> musicas, Usuario usuarioDono) {
        this.titulo = titulo;
        this.musicas = musicas;
        this.usuarioDono = usuarioDono;
    }

    /**
     * Retorna o título da playlist.
     * @return Título da playlist.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Altera o título da playlist.
     * @param titulo Novo título da playlist.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna a lista de músicas da playlist.
     * @return Lista de músicas da playlist.
     */
    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    /**
     * Altera a lista de músicas da playlist.
     * @param musicas Nova lista de músicas da playlist.
     */
    public Usuario getUsuarioDono() {
        return usuarioDono;
    }

    /**
     * Altera o usuário dono da playlist.
     * @param usuarioDono Novo usuário dono da playlist.
     */
    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    /**
     * Retorna a lista de músicas da playlist.
     * @return Lista de músicas da playlist.
     */
    @Override
    public String toString() {
        return titulo;
    }
}
