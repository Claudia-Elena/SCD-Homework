package Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Atelier {

    public static int nrFabrici;
    private FabricaDeJucarii fabrici[];
    private GeneratorDeElfi generatoare[];
    public static volatile int nrElfi = 1;
    private static ReentrantLock elfiCounterLock = new ReentrantLock();
    private Ren reni[];
    private TransferDeCadou cadou;
    public static volatile Semaphore elfPensionareSemaphore = new Semaphore(0);
    private OdihnireElf elfPensionare = new OdihnireElf();


    public Atelier(TransferDeCadou cadou) {
        this.cadou = cadou;
    }

    public static ReentrantLock getElfiCounterLock() {
        return elfiCounterLock;
    }

    public void creazaFabrici() {

        Random rand = new Random();
        nrFabrici = rand.nextInt(4) + 2;
        int nrReni = rand.nextInt(10) + 8;

        fabrici = new FabricaDeJucarii[nrFabrici];
        generatoare = new GeneratorDeElfi[nrFabrici];
        reni = new Ren[nrReni];


        System.out.println("S-au creat  " + nrFabrici + " de fabrici");
        System.out.println("S-au creat " + nrReni + " de reni");

        for(int i = 0; i < nrFabrici; ++i) {

            int N = rand.nextInt(500) + 100;
            fabrici[i] = new FabricaDeJucarii(N, i + 1);
            generatoare[i] = new GeneratorDeElfi(fabrici[i]);
            System.out.println("Fabrica " + (i + 1) + " are Nr elfi  = " + N);
        }

        for(int i = 0; i < nrReni ; ++i) {
            reni[i] = new Ren(fabrici, i + 1, cadou);
        }

        for(int i = 0; i < nrFabrici; ++i) {
            generatoare[i].start();
            fabrici[i].start();
        }

        for(int i = 0; i < nrReni ; ++i) {
            reni[i].start();
        }

        elfPensionare.start();

    }


}
