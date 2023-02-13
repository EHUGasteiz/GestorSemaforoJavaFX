package eus.ehu.eivg.gestorsemaforojavafx.controller;

import eus.ehu.eivg.gestorsemaforojavafx.model.GestorSemaforos;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;

public class SemaforoCochesController implements Initializable {

    GestorSemaforos model = GestorSemaforos.getGestorSemaforos();


    private final SimpleObjectProperty<Image> lightOff = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_apagado.png")).toExternalForm()));
    private final SimpleObjectProperty<Image> redLight = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_rojo.png")).toExternalForm()));
    private final SimpleObjectProperty<Image> greenLight = new SimpleObjectProperty<>(new Image(Objects.requireNonNull(getClass().getResource("/images/circulo_verde.png")).toExternalForm()));

    @FXML
    private Label contLabel;

    @FXML
    private ImageView greenLightImageView;

    @FXML
    private ImageView redLightImageView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing controller");
        contLabel.textProperty().bind(model.contProperty().asString());
        contLabel.styleProperty().bind(Bindings.when(model.estaVerdeProperty())
                .then("-fx-text-fill:red;-fx-background-color:black;")
                .otherwise("-fx-text-fill:green;-fx-background-color:black"));
        redLightImageView.imageProperty().bind(Bindings.when(model.estaVerdeProperty())
                .then(redLight)
                .otherwise(lightOff));
        greenLightImageView.imageProperty().bind(Bindings.when(model.estaVerdeProperty())
                .then(lightOff)
                .otherwise(greenLight));
    }
}
