package com.example.projetofinaljavafx.controle;

import com.example.projetofinaljavafx.HelloApplication;
import com.example.projetofinaljavafx.modelo.UsuarioVIP;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable{
    Player player = new Player();
    String[] musicas = {"hino do vasco"};

    //cadastrar
    @FXML
    private Button cadastrarBotao;

    @FXML
    private PasswordField cadastroSenhaField;

    @FXML
    private TextField cadastroUsuarioField;

    @FXML
    private CheckBox vipCheckBox;

    @FXML
    private Button voltarBotao;

    @FXML
    private Label labelErro;
    //UsuarioVip
    @FXML
    private Button AdicionarDiretorioBotao;

    @FXML
    private Button AdicionarPlaylistBotao;

    @FXML
    private ListView<String> MusicasListView;

    @FXML
    private ListView<?> MusicasListViewComum;


    @FXML
    private ListView<?> PlaylistListView;

    @FXML
    private Button musicaAnteriorBotao;

    @FXML
    private Button musicaPosteriorBotao;

    @FXML
    private Button tocarMusicaBotao;
    //Log In
    @FXML
    private Button CadastrarButton;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField UsuarioField;
    @FXML
    private PasswordField senhaField;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        player.carregarUsuarios();
        player.carregarDiretorios();
        player.carregarMusicas();
        MusicasListView.getItems().addAll(musicas);

    }

    //----------------------------------------------
    //cadastrar
    @FXML
    void Cadastrar(ActionEvent event) throws IOException {
        String selecionado = "Comum";
        if (vipCheckBox.isSelected()){
            selecionado = "VIP";
        }
        if (player.cadastrarUsuario(cadastroSenhaField.getText(), cadastroUsuarioField.getText(), selecionado)){
            labelErro.setText("Usuario cadastrado");
        }else{
            labelErro.setText("Usuario já cadastrado");
        }
    }

    @FXML
    void Voltar(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("LoginView.fxml");
    }

    @FXML
    void ehVip(ActionEvent event) {

    }
    //------------------------------------------
    //Usuario VIP
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
    void adicionarPlaylist(ActionEvent event) {

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


    //--------------------------------------------
    //Log In
    public void userLogIn() throws IOException {
        HelloApplication m = new HelloApplication();
        if(player.fazerLogin(senhaField.getText().toString(), UsuarioField.getText().toString())){
            if(player.getUsuarioLogado() instanceof UsuarioVIP) {
                m.changeScene("UsuarioVipView.fxml");
            }else{
                m.changeScene("UsuarioComumView.fxml");
            }
        }
    }

    public void cadastrar(ActionEvent actionEvent) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("CadastrarView.fxml");
    }


}
