module com.example.projetofinaljavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.projetofinaljavafx to javafx.fxml;
    exports com.example.projetofinaljavafx;
    exports com.example.projetofinaljavafx.controle;
    exports com.example.projetofinaljavafx.modelo;
    exports com.example.projetofinaljavafx.dao;
    opens com.example.projetofinaljavafx.controle to javafx.fxml;
}
