package com.example.projetofinaljavafx.controle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class UsuarioComumController implements Initializable {
    Player player = new Player();

    @FXML
    private Button AdicionarDiretorioBotao;

    @FXML
    private ListView<String> MusicasListView;

    @FXML
    private Button PreviousMusicButton;

    @FXML
    private Button NextMusicButton;

    @FXML
    private Button PlayButton;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        player.carregarUsuarios();
        player.carregarDiretorios();
        player.carregarMusicas();
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
    }

    @FXML
    void musicaAnterior(ActionEvent event) {

    }

    @FXML
    void musicaPosterior(ActionEvent event) {

    }

    @FXML
    void tocarMusica(ActionEvent event) {
        player.tocarMusica();
    }
}
