package com.example.projetofinaljavafx.controle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.projetofinaljavafx.modelo.Musica;
import com.example.projetofinaljavafx.modelo.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 * Classe que controla a tela do usuário comum.
 */
public class UsuarioComumController implements Initializable {
    /**
     * Instância da classe Player.
     */
    private Player player = new Player();
    /**
     * Música atual.
     */
    private Musica musicaAtual;

    /**
     * Botão de adicionar diretório.
     */
    @FXML
    private Button AdicionarDiretorioBotao;

    /**
     * Lista de músicas.
     */
    @FXML
    private ListView<Musica> MusicasListView;

    /**
     * Botão de música anterior.
     */
    @FXML
    private Button PreviousMusicButton;

    /**
     * Botão de próxima música.
     */
    @FXML
    private Button NextMusicButton;

    /**
     * Botão de tocar música.
     */
    @FXML
    private Button PlayButton;

    /**
     * Botão de parar música.
     */
    @FXML
    private Button Stop;

    /**
     * Inicializa a tela do usuário comum.
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
     * Altera o usuário logado.
     * @param u Novo usuário logado.
     */
    public void setUsuarioLogado(Usuario u) {
        player.setUsuarioLogado(u);
    }

    /**
     * Adiciona um diretório.
     * @param actionEvent Evento de clique no botão de adicionar diretório.
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
     * Toca a música anterior.
     * @param event Evento de clique no botão de música anterior.
     */
    @FXML
    void musicaAnterior(ActionEvent event) {
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
        player.pararMusica();
        player.tocarMusica(musicaAtual);
    }

    /**
     * Toca a próxima música.
     * @param event Evento de clique no botão de próxima música.
     */
    @FXML
    void musicaPosterior(ActionEvent event) {
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
        player.pararMusica();
        player.tocarMusica(musicaAtual);
    }

    /**
     * Toca a música.
     * @param event Evento de clique no botão de tocar música.
     */
    @FXML
    void tocarMusica(ActionEvent event) {
        player.tocarMusica(musicaAtual);
    }

    /**
     * Para a música.
     * @param event Evento de clique no botão de parar música.
     */
    @FXML
    void pararMusica(ActionEvent event) {
        player.pararMusica();
    }
}
