package eus.ehu.eivg.gestorsemaforojavafx;

import eus.ehu.eivg.gestorsemaforojavafx.views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SemaforoMVCApplication extends Application {
    public static void main(String[] args) {
      launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory.mostrarSemaforoCoches();
    }
}