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

/**
 * Classe que controla a tela de cadastro de usuários.
 */
public class CadastrarController implements Initializable {
    /**
     * Instância da classe Player.
     */
    Player player = new Player();

    /**
     * Botão de cadastrar.
     */
    @FXML
    private Button cadastrarBotao;

    /**
     * Campo de texto de senha.
     */
    @FXML
    private PasswordField cadastroSenhaField;

    /**
     * Campo de texto de login.
     */
    @FXML
    private TextField cadastroUsuarioField;

    /**
     * Checkbox de VIP.
     */
    @FXML
    private CheckBox vipCheckBox;

    /**
     * Botão de voltar.
     */
    @FXML
    private Button voltarBotao;

    /**
     * Label de erro.
     */
    @FXML
    private Label labelErro;

    /**
     * Inicializa a tela de cadastro de usuários.
     */
    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        player.carregarUsuarios();
        player.carregarDiretorios();
        player.carregarMusicas();
    }

    /**
     * Cadastra um usuário.
     * @param event Evento de clique no botão de cadastrar.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
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

    /**
     * Volta para a tela de login.
     * @param event Evento de clique no botão de voltar.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
    @FXML
    void Voltar(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("LoginView.fxml");
    }

    /**
     * Define se o usuário é VIP.
     * @param event Evento de clique no checkbox de VIP.
     */
    @FXML
    void ehVip(ActionEvent event) {

    }
}
