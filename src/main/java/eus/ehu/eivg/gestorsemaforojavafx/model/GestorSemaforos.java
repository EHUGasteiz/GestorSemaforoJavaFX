package eus.ehu.eivg.gestorsemaforojavafx.model;

import javafx.application.Platform;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase representa el módulo que gestiona los semaforos.
 * Implementa los patrones Singleton y Observable
 *
 * @author mikel
 */
public class GestorSemaforos  extends Observable {
        private static final int PERIODO = 15;
        private static final GestorSemaforos mGestorSemaforos = new GestorSemaforos();
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
            setChanged();
            notifyObservers();
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
            notifyObservers();
        }



    public boolean estaVerde() {
        return estaVerde;
    }


}
