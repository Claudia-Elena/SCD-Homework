package Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
public class Elf extends Thread {

    private int Numar;
    private int X;
    private int Y;
    private int cadou = 0;
    private FabricaDeJucarii fabrica;
    private static int counter = 0;
    private static Semaphore counterSemaphore = new Semaphore(1);

    public Elf(int Numar, int X, int Y, FabricaDeJucarii fabrica) {

        this.Numar = Numar;
        this.X = X;
        this.Y = Y;
        this.fabrica = fabrica;
    }

    public void run() {

        while(true) {
            if(Math.abs(X - Y) <= 1) {
                System.out.println("Elf " + Numar +
                        " is sleeping on position (" + X + "," + Y + ")" );



                if(counterSemaphore.tryAcquire()) {

                    counter++;
                    counterSemaphore.release();

                }else {

                    continue;
                }

                while(counter < fabrica.getN()) {
                    // Wait for other elves
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            cadou = cadou + Numar;

            fabrica.mutaElf(this);


            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }


    public void stopWork() {

        Random rand = new Random();

        long milis = rand.nextInt(50) + 10;

        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void raporteazaPozitia() {

        System.out.println("Elful " + Numar +
                " este pe pozitia  x=" + X + ", y=" +
                Y + " in fabrica " + fabrica.getNumar() );
    }

    public void schimbaPozitia(int newX, int newY) {

        X = newX;
        Y = newY;
    }

    public int getNumar() {
        return Numar;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getCadou() {
        return cadou;
    }



}
