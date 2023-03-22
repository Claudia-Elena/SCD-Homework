package CyclicBarrier;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class GeneratorDeElfi extends Thread {

    private FabricaDeJucarii fabrica;
    private CyclicBarrier elfBarrier;
    public GeneratorDeElfi(FabricaDeJucarii fabrica) {
        this.fabrica = fabrica;
        elfBarrier = new CyclicBarrier(fabrica.getN());
    }

    public void run() {

        while (true) {

            Random rand = new Random();
            long milis = rand.nextInt(1000) + 500;


            try {
                Thread.sleep(milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            generareElf();
        }
    }

    private void generareElf() {

        Random rand = new Random();


        ReentrantLock factoryLock = fabrica.getFabricaLock();

        factoryLock.lock();

        int dimensiuneFabrica = fabrica.getN();


        if (fabrica.nrExistentiDeElfi() != dimensiuneFabrica / 2) {


            int X = rand.nextInt(dimensiuneFabrica) + 0;
            int Y = rand.nextInt(dimensiuneFabrica) + 0;


            ReentrantLock elvesCounterLock = Atelier.getElfiCounterLock();


            elvesCounterLock.lock();

            Elf elf = new Elf(Atelier.nrElfi, X, Y, fabrica,elfBarrier);

            if (fabrica.adaugaElf(elf)) {

                Atelier.nrElfi++;
                System.out.println("Elful " + elf.getNumar() +
                        " a luat nastere in fabrica  " + fabrica.getNumar());
            }

            elvesCounterLock.unlock();

        }
        factoryLock.unlock();

    }


}
