package CyclicBarrier;
public class MosCraciun extends Thread {


    private TransferDeCadou Cadou;

    public MosCraciun(TransferDeCadou Cadou) {
        this.Cadou = Cadou;

    }

    public void run() {

        while(true) {

            int NrCadouri = Cadou.receiveGift();
            System.out.println("Mos Craciun a pus  " + NrCadouri + " cadouri in sacul lui");

        }
    }

}

