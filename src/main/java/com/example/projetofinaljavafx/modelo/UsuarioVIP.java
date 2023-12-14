package com.example.projetofinaljavafx.modelo;

import java.util.ArrayList;

import com.example.projetofinaljavafx.dao.PlaylistDao;

public class UsuarioVIP extends com.example.projetofinaljavafx.modelo.Usuario {
    //ArrayList<Playlist> playlists;
    private PlaylistDao p = new PlaylistDao();

    public PlaylistDao getP() {
        return p;
    }

    public void setP(PlaylistDao p) {
        this.p = p;
    }
    
}
