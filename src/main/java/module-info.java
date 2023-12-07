module com.example.projetofinaljavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.projetofinaljavafx to javafx.fxml;
    exports com.example.projetofinaljavafx;
}