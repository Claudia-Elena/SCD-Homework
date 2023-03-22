import java.util.Random;

public class Elf extends Thread {

    private int Numar;
    private int X;
    private int Y;
    private int cadou = 0;
    private FabricaDeJucarii fabrica;

    public Elf(int Numar, int X, int Y, FabricaDeJucarii fabrica) {

        this.Numar = Numar;
        this.X = X;
        this.Y = Y;
        this.fabrica = fabrica;
    }

    public void run() {

        while(true) {

            cadou = cadou + Numar;

            // Mutam elful in fabrica
            fabrica.mutaElf(this);


            // Dupa ce creeaza un cadou , elful ia pauza de 30 milisecunde
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // Pensionarea elfului
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
