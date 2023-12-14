package com.example.projetofinaljavafx.controle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.example.projetofinaljavafx.dao.DiretorioDao;
import com.example.projetofinaljavafx.dao.MusicaDao;
import com.example.projetofinaljavafx.dao.UsuarioDao;
import com.example.projetofinaljavafx.modelo.Musica;
import com.example.projetofinaljavafx.modelo.Playlist;
import com.example.projetofinaljavafx.modelo.Usuario;
import com.example.projetofinaljavafx.modelo.UsuarioVIP;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player {
    private int musicaAtual = 0;
    Playlist playlistAtual = null;
    Usuario usuarioLogado = null;
    UsuarioDao u = new UsuarioDao();
    DiretorioDao d = new DiretorioDao();
    MusicaDao m = new MusicaDao();
    Media media;
    MediaPlayer mediaPlayer;

    public void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public void carregarUsuarios() {
        try {
            u.carregarUsuarios();
        }
        catch(Exception e) {
        }
    }

    public boolean fazerLogin(String senha, String login) throws IOException {
        for(Usuario i : u.getUsuarios()) {
            if(i.getLogin().equals(login) && i.getSenha().equals(senha)) {
                usuarioLogado = i;
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarUsuario(String senha, String login, String tipo) {
            return u.cadastrarUsuario(login, senha, tipo);
    }
    public boolean cadastrarDiretorio(String caminho) {
        try {
            d.cadastrarDiretorio(caminho);
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean carregarDiretorios() {
        try {
            d.carregarDiretorios();
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean carregarMusicas() {
        try {
            m.carregarMusicas();
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean carregarPlaylists() {
        if(!(usuarioLogado instanceof UsuarioVIP)) {
            return false;
        }

        try {
            ((UsuarioVIP)usuarioLogado).getP().carregarPlaylists(usuarioLogado);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean criarPlaylist(String nome) {
        if (!(usuarioLogado instanceof UsuarioVIP)) {
            return false;
        }
        try {
            ((UsuarioVIP)usuarioLogado).getP().cadastrarPlaylist(nome, usuarioLogado);
        } 
        catch (Exception e) {
            return false;
        }

        return true;
    }
    
    public boolean adicionarMusicaEmPlaylist(Musica musica, String nomePlaylist) {
        if(!(usuarioLogado instanceof UsuarioVIP)) {
            return false;
        }
        
        if(musica == null) {
            return false;
        }
        
        try {
            ((UsuarioVIP)usuarioLogado).getP().adicionarMusica(nomePlaylist, musica, usuarioLogado);
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public ArrayList<Playlist> getPlaylists() {
        if(!(usuarioLogado instanceof UsuarioVIP))  {
            return new ArrayList<>();
        }

        return ((UsuarioVIP)usuarioLogado).getP().getPlaylists();
    }


    public void tocarMusica(Musica musica) {
        media = new Media(new File(musica.getCaminho()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void pararMusica() {
        mediaPlayer.stop();
    }

    public ArrayList<Musica> getMusicas() {
        return m.getMusicas();
    }
}
