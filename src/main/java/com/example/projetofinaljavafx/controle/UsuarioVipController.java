package com.example.projetofinaljavafx.controle;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.projetofinaljavafx.modelo.Musica;
import com.example.projetofinaljavafx.modelo.Playlist;
import com.example.projetofinaljavafx.modelo.Usuario;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 * Classe que controla a tela do usuário VIP.
 */
public class UsuarioVipController implements Initializable {
    /**
     * Instância da classe Player.
     */
    private Player player = new Player();

    /**
     * Botão de adicionar diretório.
     */
    @FXML
    private Button AdicionarDiretorioBotao;

    /**
     * Botão de adicionar playlist.
     */
    @FXML
    private Button AdicionarPlaylistBotao;

    /**
     * Botão de adicionar música à playlist.
     */
    @FXML
    private Button AdicionarMusicaPlaylistBotao;

    /**
     * Lista de músicas.
     */
    @FXML
    private ListView<Musica> MusicasListView;

    /**
     * Lista de músicas da playlist.
     */
    @FXML
    private ListView<Musica> MusicasPlaylistListView;

    /**
     * Lista de playlists.
     */
    @FXML
    private ListView<Playlist> PlaylistListView;

    /**
     * Botão de música anterior.
     */
    @FXML
    private Button musicaAnteriorBotao;

    /**
     * Botão de próxima música.
     */
    @FXML
    private Button musicaPosteriorBotao;

    /**
     * Botão de tocar música.
     */
    @FXML
    private Button tocarMusicaBotao;
    /**
     * Botão de parar música.
     */
    @FXML
    private Button Stop;

    /**
     * Música atual.
     */
    private Musica musicaAtual;

    /**
     * Playlist atual.
     */
    private Playlist playlistAtual;

    /**
     * Indica se a música atual foi selecionada na playlist.
     */
    boolean selecionadoPlaylist = false;

    /**
     * Inicializa a tela do usuário VIP.
     */
    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        player.carregarUsuarios();
        player.carregarDiretorios();
        player.carregarMusicas();

        MusicasListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Musica>() {
            @Override
            public void changed(ObservableValue<? extends Musica> observable, Musica oldValue, Musica newValue) {
                musicaAtual = MusicasListView.getSelectionModel().getSelectedItem();     
                selecionadoPlaylist = false;
            }
            
        });

        PlaylistListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
            @Override
            public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
                playlistAtual = PlaylistListView.getSelectionModel().getSelectedItem();     
                atualizarMusicasPlaylist();
            }
        });

        MusicasPlaylistListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Musica>() {

            @Override
            public void changed(ObservableValue<? extends Musica> observable, Musica oldValue, Musica newValue) {
                musicaAtual = MusicasPlaylistListView.getSelectionModel().getSelectedItem();     
                selecionadoPlaylist = true;
            }

        });

        atualizarMusicas();
    }

    /**
     * Atualiza a lista de músicas.
     */
    public void atualizarMusicas() {
        MusicasListView.getItems().clear();
        MusicasListView.getItems().addAll(player.getMusicas());
    }

    /**
     * Atualiza a lista de playlists.
     */
    public void atualizarPlaylists() {
        PlaylistListView.getItems().clear();
        PlaylistListView.getItems().addAll(player.getPlaylists());
    }

    /**
     * Atualiza a lista de músicas da playlist.
     */
    public void atualizarMusicasPlaylist() {
        MusicasPlaylistListView.getItems().clear();
        MusicasPlaylistListView.getItems().addAll(playlistAtual.getMusicas());
    }

    /**
     * Altera o usuário logado.
     * @param u Usuário logado.
     */
    public void setUsuarioLogado(Usuario u) {
        player.setUsuarioLogado(u);
        player.carregarPlaylists();
        atualizarPlaylists();
    }

    /**
     * Adiciona um diretório.
     * @param actionEvent Evento de clique no botão.
     */
    @FXML
    public void adicionarDiretorio(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Window stage = source.getScene().getWindow();

        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Escolha um arquivo");

        File diretorio = directoryChooser.showDialog(stage);
        if (diretorio != null) {
            player.cadastrarDiretorio(diretorio.getAbsolutePath());
        }
        player.carregarMusicas();
        atualizarMusicas();
    }

    /**
     * Adiciona uma playlist.
     * @param event Evento de clique no botão.
     */
    @FXML
    void adicionarPlaylist(ActionEvent event) {
        TextInputDialog tDialog = new TextInputDialog();
        tDialog.setTitle("Teste");
        tDialog.setHeaderText("Digite o nome da playlist:");
        tDialog.setContentText("Nome: ");

        Optional<String> result = tDialog.showAndWait();
        if (result.isPresent()) {
            player.criarPlaylist(result.get());
        }

        atualizarPlaylists();
    }

    /**
     * Adiciona uma música à playlist.
     * @param event Evento de clique no botão.
     */
    @FXML
    void adicionarMusicaPlaylist(ActionEvent event) {
        player.adicionarMusicaEmPlaylist(musicaAtual, playlistAtual.getTitulo());
        atualizarMusicasPlaylist();
    }

    /**
     * Toca a música anterior.
     * @param event Evento de clique no botão.
     */
    @FXML
    void musicaAnterior(ActionEvent event) {
        if(!selecionadoPlaylist) {
            int i;
            for(i=0;i<MusicasListView.getItems().size(); i++) {
                if(MusicasListView.getItems().get(i).getCaminho().equals(musicaAtual.getCaminho())) {
                    break;
                }
            }
            if(i-1 >= 0) {
                musicaAtual = MusicasListView.getItems().get(i-1);
            }
            else {
                player.pararMusica();
                return;
            }
        } else {
            int i;
            for(i=0;i<MusicasPlaylistListView.getItems().size(); i++) {
                if(MusicasPlaylistListView.getItems().get(i).getCaminho().equals(musicaAtual.getCaminho())) {
                    break;
                }
            }
            if(i-1 >= 0) {
                musicaAtual = MusicasPlaylistListView.getItems().get(i-1);
            }
            else {
                player.pararMusica();
                return;
            }
        }
        player.pararMusica();
        player.tocarMusica(musicaAtual);

    }

    /**
     * Toca a próxima música.
     * @param event Evento de clique no botão.
     */
    @FXML
    void musicaPosterior(ActionEvent event) {
        if(!selecionadoPlaylist) {
            int i;
            for(i=0;i<MusicasListView.getItems().size(); i++) {
                if(MusicasListView.getItems().get(i).getCaminho().equals(musicaAtual.getCaminho())) {
                    break;
                }
            }
            if(i+1 < MusicasListView.getItems().size()) {
                musicaAtual = MusicasListView.getItems().get(i+1);
            }
            else {
                player.pararMusica();
                return;
            }
        } else {
            int i;
            for(i=0;i<MusicasPlaylistListView.getItems().size(); i++) {
                if(MusicasPlaylistListView.getItems().get(i).getCaminho().equals(musicaAtual.getCaminho())) {
                    break;
                }
            }
            if(i+1 < MusicasPlaylistListView.getItems().size()) {
                musicaAtual = MusicasPlaylistListView.getItems().get(i+1);
            }
            else {
                player.pararMusica();
                return;
            }
        }
        player.pararMusica();
        player.tocarMusica(musicaAtual);
    }

    /**
     * Toca a música.
     * @param event Evento de clique no botão.
     */
    @FXML
    void tocarMusica(ActionEvent event) {
        player.tocarMusica(musicaAtual);
    }

    /**
     * Para a música.
     * @param event Evento de clique no botão.
     */
    @FXML
    void pararMusica(ActionEvent event) {
        player.pararMusica();
    }
}
