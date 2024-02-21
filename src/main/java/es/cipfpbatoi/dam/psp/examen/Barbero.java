package es.cipfpbatoi.dam.psp.examen;

public class Barbero implements Runnable {
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (!barberia.estaVacia() || !Thread.currentThread().isInterrupted()) {
            try {
                barberia.cortarPelo();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}



