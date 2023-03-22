package Semaphore;

public class Planificator {

    public static void main(String[] args) {

        TransferDeCadou Cadou = new TransferDeCadou();

        MosCraciun MosCraciun = new MosCraciun(Cadou);

        Atelier atelier =  new Atelier(Cadou);

        atelier.creazaFabrici();

        MosCraciun.start();

    }

}
