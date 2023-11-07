package org.example;

public class Coche extends Thread{
    Puente puente;

    public Coche(Puente puente){
        this.puente = puente;
    }

    @Override
    public void run() {
        super.run();
        try {
            puente.entrarCoche(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void conducir(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
