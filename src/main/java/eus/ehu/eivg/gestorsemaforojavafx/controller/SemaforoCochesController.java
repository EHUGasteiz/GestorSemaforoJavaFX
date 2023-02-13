package eus.ehu.eivg.gestorsemaforojavafx.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SemaforoCochesController implements Initializable {

    // Atributos para gestionar el estado del semáforo
    private static final int PERIODO = 15;
    private boolean estaVerde;
    private int cont;


    private final SimpleObjectProperty<Image> lightOff = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_apagado.png")).toExternalForm()));
    private final SimpleObjectProperty<Image> redLight = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_rojo.png")).toExternalForm()));
    private final SimpleObjectProperty<Image> greenLight = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_verde.png")).toExternalForm()));

    @FXML
    private Label contLabel;

    @FXML
    private ImageView greenLightImageView;

    @FXML
    private ImageView redLightImageView;



    private void actualizarCont() {
        cont--;
        if (cont == 0) {
            estaVerde = !estaVerde;
            cont = PERIODO;
        }
        contLabel.setText(String.valueOf(cont));
        if (estaVerde) {
            contLabel.setStyle("-fx-text-fill:red;-fx-background-color:black;");
            redLightImageView.setImage(redLight.get());
            greenLightImageView.setImage(lightOff.get());
        } else {
            contLabel.setStyle("-fx-text-fill:green;-fx-background-color:black;");
            redLightImageView.setImage(lightOff.get());
            greenLightImageView.setImage(greenLight.get());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing controller");

        cont = PERIODO;
        estaVerde = false;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> actualizarCont());
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
