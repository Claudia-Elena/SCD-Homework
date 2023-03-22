public class TransferDeCadou {

    private volatile int head = 0;
    private volatile int tail = 0;
    private int[] cadouri = new int[10];
    // Metoda prin care Mos Craciun primeste cadourile de la reni
    public synchronized int receiveGift() {

        int cadou = 0;

       
        while(tail == head) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

      
        cadou = cadouri[head % cadouri.length];
        head++;

        
        notifyAll();

        return cadou;
    }
 // Metoda prin care reni ii dau lui Mos Craciun cadourile
    public synchronized void giveGift(int cadou) {

       
        while(tail - head == cadouri.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       
        cadouri[tail % cadouri.length] = cadou;
        tail++;

        
        notifyAll();

    }
}
