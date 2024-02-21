package es.cipfpbatoi.dam.psp.examen;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia();
        Thread th = new Thread(new Barbero(barberia));
        th.start();

        Thread[] clientes = new Thread[10];

        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente(barberia, i+1);
            try {
                Thread.sleep(new Random().nextInt(6000) + 5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clientes[i] = new Thread(cliente);
            clientes[i].start();
        }

        for (Thread t : clientes) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (barberia.estaVacia()) {
            System.out.println("Barberia Cerrada");
            th.interrupt();
        }
    }
}