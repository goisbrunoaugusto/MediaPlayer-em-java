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

/**
 * Classe que controla o player.
 */
public class Player {
    /**
     * Usuário logado no player.
     */
    private Usuario usuarioLogado = null;
    /**
     * Instância da classe UsuarioDao.
     */
    private UsuarioDao u = new UsuarioDao();
    /**
     * Instância da classe DiretorioDao.
     */
    private DiretorioDao d = new DiretorioDao();
    /**
     * Instância da classe MusicaDao.
     */
    private MusicaDao m = new MusicaDao();
    /**
     * Instância da classe Media.
     */
    private Media media;
    /**
     * Instância da classe MediaPlayer.
     */
    private MediaPlayer mediaPlayer;

    /**
     * Altera o usuário logado no player.
     * @param usuario Novo usuário logado no player.
     */
    public void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    /**
     * Carrega os usuários.
     */
    public void carregarUsuarios() {
        try {
            u.carregarUsuarios();
        }
        catch(Exception e) {
        }
    }

    /**
     * Faz o login do usuário.
     * @param senha Senha do usuário.
     * @param login Login do usuário.
     * @return True se o login foi realizado com sucesso, false caso contrário.
     */
    public boolean fazerLogin(String senha, String login) throws IOException {
        for(Usuario i : u.getUsuarios()) {
            if(i.getLogin().equals(login) && i.getSenha().equals(senha)) {
                usuarioLogado = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Cadastra um usuário.
     * @param senha Senha do usuário.
     * @param login Login do usuário.
     * @param tipo Tipo do usuário.
     * @return True se o usuário foi cadastrado com sucesso, false caso contrário.
     */
    public boolean cadastrarUsuario(String senha, String login, String tipo) {
            return u.cadastrarUsuario(login, senha, tipo);
    }

    /**
     * Cadastra um diretório.
     * @param caminho Caminho do diretório.
     * @return True se o diretório foi cadastrado com sucesso, false caso contrário.
     */
    public boolean cadastrarDiretorio(String caminho) {
        try {
            d.cadastrarDiretorio(caminho);
        } 
        catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Carrega os diretórios.
     * @return True se os diretórios foram carregados com sucesso, false caso contrário.
     */
    public boolean carregarDiretorios() {
        try {
            d.carregarDiretorios();
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Carrega as músicas.
     * @return True se as músicas foram carregadas com sucesso, false caso contrário.
     */
    public boolean carregarMusicas() {
        try {
            m.carregarMusicas();
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Carrega as playlists.
     * @return True se as playlists foram carregadas com sucesso, false caso contrário.
     */
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

    /**
     * Cria uma playlist.
     * @param nome Nome da playlist.
     * @return True se a playlist foi criada com sucesso, false caso contrário.
     */
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
    
    /**
     * Adiciona uma música a uma playlist.
     * @param musica Música a ser adicionada.
     * @param nomePlaylist Nome da playlist.
     * @return True se a música foi adicionada com sucesso, false caso contrário.
     */
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

    /**
     * Retorna o usuário logado.
     * @return Usuário logado.
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * Retorna as playlists do usuário logado.
     * @return Playlists do usuário logado.
     */
    public ArrayList<Playlist> getPlaylists() {
        if(!(usuarioLogado instanceof UsuarioVIP))  {
            return new ArrayList<>();
        }

        return ((UsuarioVIP)usuarioLogado).getP().getPlaylists();
    }

    /**
     * Toca uma música.
     * @param musica Música a ser tocada.
     */
    public void tocarMusica(Musica musica) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
        media = new Media(new File(musica.getCaminho()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    /**
     * Para a música que está tocando.
     */
    public void pararMusica() {
        mediaPlayer.stop();
    }

    /**
     * Retorna as músicas.
     * @return Músicas.
     */
    public ArrayList<Musica> getMusicas() {
        return m.getMusicas();
    }
}
