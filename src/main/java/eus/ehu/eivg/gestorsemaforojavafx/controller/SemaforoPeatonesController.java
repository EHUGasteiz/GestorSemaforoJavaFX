package eus.ehu.eivg.gestorsemaforojavafx.controller;

import eus.ehu.eivg.gestorsemaforojavafx.model.GestorSemaforos;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class SemaforoPeatonesController implements Initializable, Observer {
    private final GestorSemaforos model = GestorSemaforos.getGestorSemaforos();
    private final SimpleObjectProperty<Image> lightOff = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_apagado.png")).toExternalForm()));
    private final SimpleObjectProperty<Image> redLight = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_rojo.png")).toExternalForm()));
    private final SimpleObjectProperty<Image> greenLight = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_verde.png")).toExternalForm()));

    @FXML
    private Label contLabel;

    @FXML
    private ImageView greenLightImageView;

    @FXML
    private ImageView redLightImageView;


    @FXML
    void pedirPasoAction() {
        model.ponerVerde();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing controller");
        model.addObserver(this);
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {

        contLabel.setText(String.valueOf(model.getContador()));
        if (model.estaVerde()) {
            contLabel.setStyle("-fx-text-fill:green;-fx-background-color:black;");
            redLightImageView.setImage(lightOff.get());
            greenLightImageView.setImage(greenLight.get());
        } else {
            contLabel.setStyle("-fx-text-fill:red;-fx-background-color:black;");
            redLightImageView.setImage(redLight.get());
            greenLightImageView.setImage(lightOff.get());
        }

    }
}
