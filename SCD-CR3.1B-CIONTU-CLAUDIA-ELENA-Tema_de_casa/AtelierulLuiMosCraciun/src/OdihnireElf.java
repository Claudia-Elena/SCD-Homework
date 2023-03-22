public class OdihnireElf extends Thread {

    public void run() {

        while(true) {

            // Elfii au mai si obosit ,asa ca ii scoatem la pensie
            Atelier.elfPensionareSemaphore.release();


            
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
