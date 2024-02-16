package eus.ehu.eivg.gestorsemaforojavafx.model;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase representa el módulo que gestiona los semaforos.
 * Implementa los patrones Singleton y Observable
 *
 * @author mikel
 */
public class GestorSemaforos implements Observable {
        private static final int PERIODO = 15;
        private static final GestorSemaforos mGestorSemaforos = new GestorSemaforos();
        private List<InvalidationListener> observers;

        private boolean estaVerde; // Indica si el semáforo está verde para los peatones o no
        private int cont;

    private GestorSemaforos() {
            cont = PERIODO;
            estaVerde = true;
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() ->
                    actualizarCont())   ;
                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 1000);

            // Crear la lista de observadores
            observers = new ArrayList<>();
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
            return cont;
        }



        public final boolean getEstaVerde() {
            return estaVerde;
        }


        /**
         * ponerVerde
         * Si el semafor esta rojo para los peatones, lo pone en verde
         */
        public void ponerVerde() {
            if (!getEstaVerde()) {
                cont = PERIODO;
                estaVerde = true;
            }
            // Notificar a los observadores
            setChanged();
        }

        /**
         * Actualiza el contador del tiempo. Cuando llega a 0 cambia el color de la luz
         */
        private void actualizarCont() {
            cont--;
            if (cont == 0) {
                cont = PERIODO;
                estaVerde = !estaVerde;
            }
            setChanged();
        }

        private void setChanged() {
            observers.forEach(o -> o.invalidated(this));
        }



    public boolean estaVerde() {
        return estaVerde;
    }


    @Override
    public void addListener(InvalidationListener listener) {
        observers.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        observers.remove(listener);
    }
}
