package com.example.projetofinaljavafx.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.projetofinaljavafx.HelloApplication;
import com.example.projetofinaljavafx.modelo.UsuarioVIP;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Classe que controla a tela de login.
 */
public class LoginController implements Initializable {
    /**
     * Instância da classe Player.
     */
    Player player = new Player();

    /**
     * Botão de cadastrar.
     */
    @FXML
    private Button CadastrarButton;
    /**
     * Botão de login.
     */
    @FXML
    private Button LoginButton;
    /**
     * Campo de texto de login.
     */
    @FXML
    private TextField UsuarioField;
    /**
     * Campo de texto de senha.
     */
    @FXML
    private PasswordField senhaField;

    /**
     * Inicializa a tela de login.
     */
    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        player.carregarUsuarios();
        player.carregarDiretorios();
        player.carregarMusicas();
    }

    /**
     * Realiza o login do usuário.
     * @param event Evento de clique no botão de login.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
    public void userLogIn(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        if(player.fazerLogin(senhaField.getText().toString(), UsuarioField.getText().toString())){
            if(player.getUsuarioLogado() instanceof UsuarioVIP) {
                FXMLLoader loader = new FXMLLoader(m.getURL("UsuarioVipView.fxml"));
                Parent root = loader.load();

                UsuarioVipController u = loader.getController();
                u.setUsuarioLogado(player.getUsuarioLogado());
                 
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                FXMLLoader loader = new FXMLLoader(m.getURL("UsuarioComumView.fxml"));
                Parent root = loader.load();

                UsuarioComumController u = loader.getController();
                u.setUsuarioLogado(player.getUsuarioLogado());

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /**
     * Muda para a tela de cadastro de usuários.
     * @param actionEvent Evento de clique no botão de cadastrar.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
    public void cadastrar(ActionEvent actionEvent) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("CadastrarView.fxml");
    }
}
