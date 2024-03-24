package code;


import code.resolution.AIResolution;

import java.util.HashSet;

public class TestBit {
    public static void main(String[] args) {
        int b=0;
        for (int i = 0; i < 1; i++) {
            long t1=System.currentTimeMillis(),t2;
            try {
                EtatBit etat = new EtatBit();
                PlateauJeu plateau = new PlateauJeu();
                plateau.remplirTableau();
                ObjectifBit obj = new ObjectifBit(plateau);

                AIResolution ai = new AIResolution();
                System.out.println(ai.resoudre(etat, obj, plateau));
                t2 = System.currentTimeMillis();
                System.out.println((t2-t1)/1000);
            }catch (OutOfMemoryError e){
               b++;
                System.out.println("error");
                t2 = System.currentTimeMillis();
                System.out.println((t2-t1)/1000);
            }
        }
        System.out.println(b);
    }
}


