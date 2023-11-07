package org.example;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Puente extends Thread{

    private ArrayList<Coche> coches;
    private final Semaphore semaphore = new Semaphore(20);

    public Puente(){
        coches = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50; i++) {
            Coche coche = new Coche(this);
            coche.start();
        }
    }

    public void entrarCoche(Coche coche) throws InterruptedException {
        String salida="";
        semaphore.acquire();
        synchronized (this) {
            coches.add(coche);
            salida = entradaRandom();
        }
        coche.conducir();
        synchronized (this) {
            coches.remove(coche);
            System.out.println(salida + coches.size());
        }
        semaphore.release();
    }

    public ArrayList<Coche> getCoches() {
        return coches;
    }
    public String entradaRandom(){
        if((int) (Math.random()*2) == 1){
            System.out.println("Un coche ha entrado desde Las Palmas. Coches en el puente: "+coches.size());
            return "Un coche ha salido direccion Galdar. Coches en el puente: ";
        }else{
            System.out.println("Un coche ha entrado desde Galdar. Coches en el puente: "+coches.size());
            return "Un coche ha salido direccion Las Palmas. Coches en el puente: ";
        }
    }
}
