package com.example.projetofinaljavafx.controle;

import java.io.File;
import java.net.URL;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class UsuarioComumController implements Initializable {
    private Player player = new Player();
    private Musica musicaAtual;

    @FXML
    private Button AdicionarDiretorioBotao;

    @FXML
    private ListView<Musica> MusicasListView;

    @FXML
    private Button PreviousMusicButton;

    @FXML
    private Button NextMusicButton;

    @FXML
    private Button PlayButton;
    @FXML
    private Button Stop;

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
    public void atualizarMusicas() {
        MusicasListView.getItems().clear();
        MusicasListView.getItems().addAll(player.getMusicas());
    }
    public void setUsuarioLogado(Usuario u) {
        player.setUsuarioLogado(u);
    }

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

    @FXML
    void tocarMusica(ActionEvent event) {
        player.tocarMusica(musicaAtual);
    }
    @FXML
    void pararMusica(ActionEvent event) {
        player.pararMusica();
    }
}
