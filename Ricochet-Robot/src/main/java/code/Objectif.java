package code;

import java.util.Arrays;
import java.util.Random;

public class Objectif {

    private int[] cooObjectif;

    public Objectif(PlateauJeu plateau){
        cooObjectif = new int[2];
        Random r = new Random();

        //choisi aléatoirement un objectif dans le tabobjectifs , indice 0 = x et 1 = y
        int coodetabobjectifs = r.nextInt(16);
        int[][] tabobjectifs = genererToutesCooObjectif(plateau);
        cooObjectif[0]=tabobjectifs[coodetabobjectifs][0];
        cooObjectif[1]=tabobjectifs[coodetabobjectifs][1];
    }
    //fonction qui genere automatiquement les objectifs d'un plateau
    public int[][] genererToutesCooObjectif(PlateauJeu plateau){
        int[][] tabobjectifs = new int[17][2];
        int l = 0;
        //pour chaque case vérifier si il touche des coins sauf les bords du tableau puis ajouter coordonnées a tabobjectifs
        for(int i=1;i<15;i++){
            for (int j=1;j<15;j++){
                if ((plateau.canGoUp(i,j)&&(plateau.canGoRight(i,j)|| plateau.canGoLeft(i,j)))
                   ||(plateau.canGoDown(i,j)&&(plateau.canGoRight(i,j)|| plateau.canGoLeft(i,j)))){
                    tabobjectifs[l][0]=i;
                    tabobjectifs[l][1]=j;
                    l++;
                }
            }
        }
        return tabobjectifs;
    }
    //fonction qui retourne les coordonnées d'un objectif choisi
    public int[] getCooObjectif() {
        return cooObjectif;
    }
    //toString pour avoir les coordonnées de l'objectif d'une partie
    public String toString() {
        return "Coord[] onnées de l'objectif =\nX = " + cooObjectif[0] + "\nY = " + cooObjectif[1]+"\n";
    }
    //permet de set un objectif particulier
    public void setObjectif(int x,int y){
        cooObjectif[0] = x;
        cooObjectif[1] = y;
    }
}
