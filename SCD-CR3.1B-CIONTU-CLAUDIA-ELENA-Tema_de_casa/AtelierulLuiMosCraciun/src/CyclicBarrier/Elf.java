package CyclicBarrier;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Elf extends Thread {

    private int Numar;
    private int X;
    private int Y;
    private int cadou = 0;
    private FabricaDeJucarii fabrica;
    private CyclicBarrier elfBarrier;
    public Elf(int Numar, int X, int Y, FabricaDeJucarii fabrica, CyclicBarrier elfBarrier) {

        this.Numar = Numar;
        this.X = X;
        this.Y = Y;
        this.fabrica = fabrica;
        this.elfBarrier = elfBarrier;
    }

    public void run() {

        while(true) {
            if(Math.abs(X - Y) <= 1) {

                System.out.println("Elf " + Numar +
                        " is sleeping on position (" + X + "," + Y + ")" );
                try {

                    elfBarrier.await();

                } catch (InterruptedException | BrokenBarrierException e) {

                    e.printStackTrace();
                }
            }
            cadou = cadou + Numar;

            fabrica.mutaElf(this);


            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if(Atelier.elfPensionareSemaphore.tryAcquire()) {
                fabrica.pensionareElf(this);
                break;
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
