package es.cipfpbatoi.dam.psp.examen;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Barberia {
    private final Lock lock;
    private final int sillasDisponibles = 5;
    private final Queue<Cliente> sillas;
    private final Condition barberoDurmiendo;
    private final Condition cliente;

    public Barberia() {
        sillas = new LinkedList<>();
        lock = new ReentrantLock();
        barberoDurmiendo = lock.newCondition();
        cliente = lock.newCondition();
    }

    public void cortarPelo() throws InterruptedException {
        lock.lock();
        try {
            while (sillas.isEmpty()) {
                System.out.println("Barbero : Dormir");
                barberoDurmiendo.await();
            }

            Cliente cliente = sillas.element();
            this.cliente.signal();
            System.out.println("Barbero : Atender cliente " + cliente.getId());
            lock.unlock();
            Thread.sleep(new Random().nextInt(6000) + 10000);
            lock.lock();
            sillas.poll();
            System.out.println("Cliente : Cliente " + cliente.getId()+" atendido");
        } finally {
            lock.unlock();
        }
    }

    public void clienteNuevo(Cliente cliente) {
        lock.lock();
        try {
            if (sillasDisponibles == sillas.size()) {
                System.out.println("No hay espacio, cliente " + cliente.getId() + " sale de la barberia");
                Thread.currentThread().interrupt();
                return;
            }
            sillas.add(cliente);
            System.out.println("El cliente " + cliente.getId() + " espera en una de las sillas.");
            barberoDurmiendo.signal();
            this.cliente.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean estaVacia() {
        return sillas.isEmpty();
    }
}