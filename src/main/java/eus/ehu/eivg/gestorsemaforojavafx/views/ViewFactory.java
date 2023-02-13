package eus.ehu.eivg.gestorsemaforojavafx.views;

import eus.ehu.eivg.gestorsemaforojavafx.SemaforoMVCApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public static void mostrarSemaforoCoches() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SemaforoMVCApplication.class.getResource("views/SemaforoCochesView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e ->System.exit(0));
        stage.setTitle("Coches");
        stage.show();
    }
}
