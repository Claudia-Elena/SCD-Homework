public class MosCraciun extends Thread {


    private TransferDeCadou Cadou;

    public MosCraciun(TransferDeCadou Cadou) {
        this.Cadou = Cadou;

    }
 // Din moment ce Mos Craciun este o entitate puternica si magica, acesta nu are nevoie sa faca pauze ,deci nu avem sleep
        
    public void run() {

        while(true) {

            int NrCadouri = Cadou.receiveGift();
            System.out.println("Mos Craciun a pus  " + NrCadouri + " cadouri in sacul lui");

           }
    }

}
