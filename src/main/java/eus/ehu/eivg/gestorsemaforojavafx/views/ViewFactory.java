package eus.ehu.eivg.gestorsemaforojavafx.views;

import eus.ehu.eivg.gestorsemaforojavafx.SemaforoMVCApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public static void mostrarSemaforoCoches() throws IOException {
        mostrarVentanaMVC("views/SemaforoCochesView.fxml", "Coches", 200, 0);
    }

    public static void mostrarSemaforoPeatones() throws IOException {
        mostrarVentanaMVC("views/SemaforoPeatonesView.fxml", "Peatones", 0, 0);
    }

    private static void mostrarVentanaMVC(String vista, String titulo, int xPos, int yPos) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SemaforoMVCApplication.class.getResource(vista));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setX(xPos);
        stage.setY(yPos);
        stage.setOnCloseRequest(e ->System.exit(0));
        stage.setTitle(titulo);
        stage.show();
    }
}
