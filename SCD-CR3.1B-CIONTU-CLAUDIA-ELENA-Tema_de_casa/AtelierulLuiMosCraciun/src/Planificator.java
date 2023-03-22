public class Planificator {

    public static void main(String[] args) {

        // Creeam cadouri pentru a transfera
        TransferDeCadou Cadou = new TransferDeCadou();

        // Il vom creea pe Mos Craciun(din praf,pulbere si dorintele noastre)
        MosCraciun MosCraciun = new MosCraciun(Cadou);

        // Orice Mos Craciun are nevie de un atelier,asa ca v-a trebui sa ii creeam unul
        Atelier atelier =  new Atelier(Cadou);

        // Vom incepe si constructia fabricilor
        atelier.creazaFabrici();

        // Deja,la Mos Craciun incepe sa primesca cadouri de la reni pentru a impartii copiilor cuminti
        MosCraciun.start();

    }

}
