package com.example.projetofinaljavafx.modelo;

import com.example.projetofinaljavafx.dao.PlaylistDao;

/**
 * Classe que representa um usuário VIP.
 */
public class UsuarioVIP extends com.example.projetofinaljavafx.modelo.Usuario {
    /**
     * Objeto que realiza a persistência das playlists.
     */
    private PlaylistDao p = new PlaylistDao();

    /**
     * Retorna o objeto que realiza a persistência das playlists.
     * @return Objeto que realiza a persistência das playlists.
     */
    public PlaylistDao getP() {
        return p;
    }

    /**
     * Altera o objeto que realiza a persistência das playlists.
     * @param p Novo objeto que realiza a persistência das playlists.
     */
    public void setP(PlaylistDao p) {
        this.p = p;
    }
    
}
