package Semaphore;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class FabricaDeJucarii extends Thread {

    private int numar;
    private int N;
    private boolean fabrica[][];
    private ArrayList<Elf> elfi = new ArrayList<Elf>();
    private ArrayList<Integer> cadouri = new ArrayList<Integer>();
    private ReentrantLock fabricaLock = new ReentrantLock();
    private ReentrantLock listaElfiLock = new ReentrantLock();
    private Semaphore reniSemaphore = new Semaphore(10);
    private ReentrantLock cadouriLock = new ReentrantLock();

    public ReentrantLock getFabricaLock() {
        return fabricaLock;
    }

    public FabricaDeJucarii(int N, int numar) {

        this.fabrica = new boolean[N][N];
        this.numar = numar;
        this.N = N;
    }

    public int nrExistentiDeElfi() {
        return elfi.size();
    }

    public int getN() {
        return N;
    }

    public int getNumar() {
        return numar;
    }

    public void run() {

        while (true) {

            try {
                askPositiaElfilor();

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void mutaElf(Elf elf) {

        int X = elf.getX();
        int Y = elf.getY();
        int nrCadou = elf.getCadou();
        int nrElf = elf.getNumar();


        try {

            fabricaLock.lock();


            if (mutareSpreDreapta(X, Y)) {

                fabrica[X][Y] = false;
                fabrica[X][Y + 1] = true;

                creeazaCadou(nrCadou, nrElf);

                elf.schimbaPozitia(X, Y + 1);

                askPositiaElfilor();

            } else if (mutareInSus(X, Y)) {

                fabrica[X][Y] = false;
                fabrica[X - 1][Y] = true;

                creeazaCadou(nrCadou, nrElf);

                elf.schimbaPozitia(X - 1, Y);

                askPositiaElfilor();

            } else if (mutareInJos(X, Y)) {

                fabrica[X][Y] = false;
                fabrica[X + 1][Y] = true;

                creeazaCadou(nrCadou, nrElf);

                elf.schimbaPozitia(X + 1, Y);

                askPositiaElfilor();

            } else if (mutareSpreStanga(X, Y)) {

                fabrica[X][Y] = false;
                fabrica[X][Y - 1] = true;

                creeazaCadou(nrCadou, nrElf);

                elf.schimbaPozitia(X, Y - 1);

                askPositiaElfilor();

            } else {

                elf.stopWork();
            }

        } finally {

            fabricaLock.unlock();

        }

    }

    private boolean mutareSpreStanga(int X, int Y) {

        if (Y - 1 > 0) {
            if (!fabrica[X][Y - 1]) {
                return true;
            }
        }

        return false;
    }

    private boolean mutareSpreDreapta(int X, int Y) {

        if (Y + 1 < N) {
            if (!fabrica[X][Y + 1]) {
                return true;
            }
        }
        return false;
    }

    private boolean mutareInJos(int X, int Y) {

        if (X + 1 < N) {
            if (!fabrica[X + 1][Y]) {
                return true;
            }
        }
        return false;
    }

    private boolean mutareInSus(int X, int Y) {

        if (X - 1 > 0) {
            if (!fabrica[X - 1][Y]) {
                return true;
            }
        }
        return false;
    }


    public boolean adaugaElf(Elf elf) {


        listaElfiLock.lock();

        int X = elf.getX();
        int Y = elf.getY();


        if (fabrica[X][Y]) {

            listaElfiLock.unlock();
            return false;

        } else {

            fabrica[X][Y] = true;

            elfi.add(elf);

            elf.start();

            elf.raporteazaPozitia();

            listaElfiLock.unlock();

            return true;
        }


    }

    private void askPositiaElfilor() {

        try {


            fabricaLock.lock();
            listaElfiLock.lock();
            cadouriLock.lock();


            for (int i = 0; i < elfi.size(); i++) {
                Elf elf = elfi.get(i);
                elf.raporteazaPozitia();
            }

        } finally {

            listaElfiLock.unlock();
            fabricaLock.unlock();
            cadouriLock.unlock();

        }

    }

    public int getCadou() {

        int nrCadouri = 0;

        try {

            try {
                reniSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cadouriLock.lock();

            try {
                nrCadouri = cadouri.get(cadouri.size() - 1);
                cadouri.remove(cadouri.size() - 1);
            } catch (Exception exception) {
                // The gifts list is empty
                nrCadouri = 0;
            }

        } finally {

            cadouriLock.unlock();
            reniSemaphore.release();

        }

        return nrCadouri;
    }


    private void creeazaCadou(int cadou, int NrElfi) {

        try {
            cadouriLock.lock();
            cadouri.add(cadou);
            System.out.println("Elful  " + NrElfi + " a creat cadoul " + cadou);

        } finally {

            cadouriLock.unlock();

        }


    }

    public void pensionareElf(Elf elf) {

        try {

            listaElfiLock.lock();
            fabricaLock.lock();

            elfi.remove(elf);

            int X = elf.getX();
            int Y = elf.getY();

            fabrica[X][Y] = false;

            System.out.println("Elful  " + elf.getNumar() +
                    " s-a pesionat din fabrica " + numar);


        } finally {

            listaElfiLock.unlock();
            fabricaLock.unlock();

        }
    }
}



