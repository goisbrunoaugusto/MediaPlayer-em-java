package com.example.projetofinaljavafx.modelo;

import java.util.ArrayList;

public class Playlist {
    private String titulo;
    private ArrayList<Musica> musicas;
    private Usuario usuarioDono;

    public Playlist(String titulo, ArrayList<Musica> musicas, Usuario usuarioDono) {
        this.titulo = titulo;
        this.musicas = musicas;
        this.usuarioDono = usuarioDono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public Usuario getUsuarioDono() {
        return usuarioDono;
    }

    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
