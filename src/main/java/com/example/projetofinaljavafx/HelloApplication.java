package com.example.projetofinaljavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Classe que controla a aplicação.
 */
public class HelloApplication extends Application {
    /**
     * Stage da aplicação.
     */
    private static Stage stg;

    /**
     * Inicializa a aplicação.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            stg = stage;
            Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera a cena.
     * @param fxml Nome do arquivo FXML.
     */
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    /**
     * Retorna a URL do arquivo FXML.
     * @param fxml Nome do arquivo FXML.
     * @return URL do arquivo FXML.
     */
    public URL getURL(String fxml) throws IOException {
        return getClass().getResource(fxml);
    }

    /**
     * Método main.
     */
    public static void main(String[] args) {
        launch();
    }
}
