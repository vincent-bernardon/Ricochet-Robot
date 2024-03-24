package code;

import code.resolution.AIResolution;
import code.resolution.AIResolution2;
import code.resolution.Couple;
import code.resolution.Couple2;

public class APP {

    public static void main(String[] args) throws InterruptedException {
        long t1,t2;
        //Resolution par int/////////////////////////////////////////////////////////////////////////////////
        /*Partie partie = new Partie();
        partie.getObjectif().setObjectif(2,7);
        partie.getEtat().setXYRobot(0,9,2);//vert
        partie.getEtat().setXYRobot(1,14,4);//rouge
        partie.getEtat().setXYRobot(2,0,3);//bleu
        partie.getEtat().setXYRobot(3,5,9);//jaune



        AIResolution2 aiInt = new AIResolution2();

        t1 = System.currentTimeMillis();
        Couple2 resultatInt = aiInt.resoudre(partie.getEtat(), partie.getObjectif(), partie.getPlateau());
        t2 = System.currentTimeMillis();

        System.out.println("Temp de resolution avec 2 int par robot = "+ ((t2-t1)/1000)+"s");
        System.out.println(resultatInt.getNbCoups());
        System.out.println(resultatInt);
        Thread.sleep(5000);
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        EtatBit etatBit = new EtatBit();
        etatBit.setXYRobot(0,9,2);//vert
        etatBit.setXYRobot(1,14,4);//rouge
        etatBit.setXYRobot(2,0,3);//bleu
        etatBit.setXYRobot(3,5,9);//jaune

        PlateauJeu plato = new PlateauJeu();
        plato.remplirTableau();

        ObjectifBit objectif = new ObjectifBit(plato);
        objectif.setObjectif(2,7);

        AIResolution aiBit = new AIResolution();
        t1 = System.currentTimeMillis();
        Couple resultatBit = aiBit.resoudre(etatBit,objectif,plato);
        t2 = System.currentTimeMillis();
        System.out.println("Temp de resolution avec 1 octet par robot = "+((t1-t2)/1000)+"s");
        System.out.println(resultatBit.getNbCoups());
        System.out.println(resultatBit);*/
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //NbCoups + nbEtat
        int nbErr =0;
        System.out.println("Tests avec des Int");


        for (int i = 0; i < 10; i++) {
            try {
                Partie wsh = new Partie();
                AIResolution2 air = new AIResolution2();
                Couple2 cpl = air.resoudre(wsh.getEtat(), wsh.getObjectif(), wsh.getPlateau());
                System.out.println("Partie : "+i);
                System.out.println("Nombre d'etats = " + air.getDejaVu().size());
                System.out.println("Nombres de coups = " + cpl.getNbCoups());
            }catch(OutOfMemoryError e){
                nbErr ++;
            }
            System.out.println("Nombres d'erreur = "+nbErr);

        }

        System.out.println("Test avec des Byte");
        nbErr=0;
        for (int i = 0; i < 10; i++) {
            try {
                EtatBit etb = new EtatBit();
                PlateauJeu plt = new PlateauJeu();
                plt.remplirTableau();
                ObjectifBit obj = new ObjectifBit(plt);
                AIResolution aire = new AIResolution();
                Couple cple = aire.resoudre(etb, obj, plt);
                System.out.println("Partie : " + i);
                System.out.println("Nombre d'etats = " + aire.getDejaVu().size());
                System.out.println("Nombres de coups = " + cple.getNbCoups());
            }catch(OutOfMemoryError e){
                nbErr ++;
            }
        }
        System.out.println("Nombres d'erreur = "+nbErr);

    }

}
