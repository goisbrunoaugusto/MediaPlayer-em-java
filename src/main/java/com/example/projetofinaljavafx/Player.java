package com.example.projetofinaljavafx;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.projetofinaljavafx.modelo.Usuario;
import com.example.projetofinaljavafx.modelo.UsuarioVIP;
import com.example.projetofinaljavafx.dao.DiretorioDao;
import com.example.projetofinaljavafx.dao.MusicaDao;
import com.example.projetofinaljavafx.dao.UsuarioDao;
import com.example.projetofinaljavafx.modelo.Musica;
import com.example.projetofinaljavafx.modelo.Playlist;
import javafx.event.ActionEvent;
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

    public void carregarUsuarios() throws IOException {
        try {
            u.carregarUsuarios();
        }
        catch(Exception e) {
            throw e;
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
    
    public boolean adicionarMusicaEmPlaylist(String caminhoMusica, String nomePlaylist) {
        if(!(usuarioLogado instanceof UsuarioVIP)) {
            return false;
        }
        
        Musica musicaAdicionada = null;
        for(Musica musica : m.getMusicas()) {
            if(musica.getCaminho() == caminhoMusica) {
                musicaAdicionada = musica;
                break;
            }
        }
        
        if(musicaAdicionada == null) {
            return false;
        }
        
        try {
            ((UsuarioVIP)usuarioLogado).getP().adicionarMusica(nomePlaylist, musicaAdicionada, usuarioLogado);
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }


    public void tocarMusica() {
        media = new Media(m.getMusicas().get(musicaAtual).getCaminho());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}