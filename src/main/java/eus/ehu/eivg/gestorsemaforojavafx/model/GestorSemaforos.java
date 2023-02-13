package eus.ehu.eivg.gestorsemaforojavafx.model;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase representa el módulo que gestiona los semáforos.
 * Implementa los patrones Singleton y Observable
 *
 * @author mikel
 */
public class GestorSemaforos {
    private static final int PERIODO = 15;
    private static final GestorSemaforos mGestorSemaforos = new GestorSemaforos();
    private final SimpleBooleanProperty estaVerde; // Indica si el semáforo está verde para los peatones o no
    private final SimpleIntegerProperty cont;

    private GestorSemaforos() {
        estaVerde = new SimpleBooleanProperty();
        cont = new SimpleIntegerProperty();
        setContador(PERIODO);
        setEstaVerde(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->
                        actualizarCont())   ;
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    /**
     * getGestorSemaforos
     * Devuelve la instancia unica de la clase
     *
     * @return el gestor de semaforos
     */
    public static GestorSemaforos getGestorSemaforos() {
        return mGestorSemaforos;
    }


    public final int getContador() {
        return cont.get();
    }

    private void setContador(int contValue) {
        cont.setValue(contValue);
    }


    public final boolean getEstaVerde() {
        return estaVerde.get();
    }

    private void setEstaVerde(boolean estaVerdeValue) {
        estaVerde.setValue(estaVerdeValue);
    }

    /**
     * ponerVerde
     * Si el semafor esta rojo para los peatones, lo pone en verde
     */
    public void ponerVerde() {
        if (!getEstaVerde()) {
            setContador(PERIODO);
            setEstaVerde(true);
        }
    }

    /**
     * Actualiza el contador del tiempo. Cuando llega a 0 cambia el color de la luz
     */
    private void actualizarCont() {
        setContador(getContador()-1);
        if (getContador() == 0) {
            setContador(PERIODO);
            setEstaVerde(!getEstaVerde());
        }
    }



    public SimpleBooleanProperty estaVerdeProperty() {
        return estaVerde;
    }

    public int getCont() {
        return cont.get();
    }

    public SimpleIntegerProperty contProperty() {
        return cont;
    }
}
