package com.example.projetofinaljavafx.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.projetofinaljavafx.HelloApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastrarController implements Initializable {
    Player player = new Player();

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

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        player.carregarUsuarios();
        player.carregarDiretorios();
        player.carregarMusicas();
    }

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
}
