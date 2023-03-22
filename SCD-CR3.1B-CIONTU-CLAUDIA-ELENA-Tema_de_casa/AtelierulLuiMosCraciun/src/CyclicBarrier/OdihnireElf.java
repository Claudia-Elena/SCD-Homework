package CyclicBarrier;
public class OdihnireElf extends Thread {

    public void run() {

        while(true) {

            Atelier.elfPensionareSemaphore.release();


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
