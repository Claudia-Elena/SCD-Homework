import java.util.Random;

public class Ren extends Thread {

    private int Numar;
    private FabricaDeJucarii fabrici[];
    private TransferDeCadou Cadou;

    public Ren(FabricaDeJucarii fabrici[], int Numar, TransferDeCadou Cadou) {
        this.fabrici = fabrici;
        this.Numar = Numar;
        this.Cadou = Cadou;

    }

    public void run() {

        while(true) {

            // Obtinem the NrCadou din fabrica
            int NrCadou = extrageCadouDinFabrica();


            if(NrCadou != 0) {
                System.out.println("Renul " + Numar + " A primit un numar de Cadouri =  " + NrCadou);

                // II dam cadourile Mosului
                giveCadouLuiMosCraciun(NrCadou);
            }

            // Este necesara o pauza de 10 - 30 millisecunde
            Random rand = new Random();
            long milis = rand.nextInt(30) + 10;
            try {
                Thread.sleep(milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void giveCadouLuiMosCraciun(int gift) {
        Cadou.giveGift(gift);
        System.out.println("Renul " + Numar + " a dat cadoul " + gift + " lui Mos Cracium");
    }

    private int extrageCadouDinFabrica() {

        // Alegem o fabrica random
        Random rand = new Random();
        int NrFabrica = rand.nextInt(Atelier.nrFabrici) + 0;

        // Vom cere cadourile din fabrica aleasa
        return fabrici[NrFabrica].getCadou();
    }
}
