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

public class LoginController implements Initializable {
    Player player = new Player();

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
    }

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

    public void cadastrar(ActionEvent actionEvent) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("CadastrarView.fxml");
    }
}
