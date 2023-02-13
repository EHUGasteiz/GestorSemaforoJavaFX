module eus.ehu.eivg.gestorsemaforojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens eus.ehu.eivg.gestorsemaforojavafx to javafx.fxml;
    opens eus.ehu.eivg.gestorsemaforojavafx.controller to javafx.fxml;
    exports eus.ehu.eivg.gestorsemaforojavafx;
}