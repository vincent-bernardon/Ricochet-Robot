package code;

import java.util.Arrays;

public class PlateauJeu {

    private boolean[][] haut;
    private boolean[][] bas;
    private boolean[][] gauche;
    private boolean[][] droite;

    //Construction du tableau avec l'implémentation des 4 tableaux + sa fonction remplirTableau
    public PlateauJeu() {
        haut = new boolean[16][16];
        bas = new boolean[16][16];
        gauche = new boolean[16][16];
        droite = new boolean[16][16];
        remplirTableau();
    }
    //remplissage du tableau non automatiquement
    public void remplirTableau() {
        //remplissage des coté haut des cases
        boolean mur = true;
        for (int i = 0; i <= 15; i++) {
            haut[0][i] = mur;
        }

        haut[1][11] = mur;
        haut[2][5] = mur;
        haut[2][15] = mur;
        haut[3][14] = mur;
        haut[3][7] = mur;
        haut[4][0] = mur;
        haut[5][3] = mur;
        haut[5][6] = mur;
        haut[5][9] = mur;
        haut[6][1] = mur;
        haut[7][12] = mur;
        haut[9][7] = mur;
        haut[9][8] = mur;
        haut[9][12] = mur;
        haut[10][0] = mur;
        haut[10][15] = mur;
        haut[11][3] = mur;
        haut[11][5] = mur;
        haut[11][10] = mur;
        haut[12][14] = mur;
        haut[13][2] = mur;
        haut[13][4] = mur;
        haut[15][11] = mur;

        //remplissage des coté bas des cases
        for (int i = 0; i <= 15; i++) {
            bas[15][i] = mur;
        }
        bas[0][11] = mur;
        bas[1][5] = mur;
        bas[1][15] = mur;
        bas[2][14] = mur;
        bas[2][7] = mur;
        bas[3][0] = mur;
        bas[4][3] = mur;
        bas[4][6] = mur;
        bas[4][9] = mur;
        bas[5][1] = mur;
        bas[6][12] = mur;
        bas[6][7] = mur;
        bas[6][8] = mur;
        bas[8][12] = mur;
        bas[9][0] = mur;
        bas[9][15] = mur;
        bas[10][3] = mur;
        bas[10][5] = mur;
        bas[10][10] = mur;
        bas[11][14] = mur;
        bas[12][2] = mur;
        bas[12][4] = mur;
        bas[14][11] = mur;


        //remplissage des coté gauche des cases
        for (int i = 0; i <= 15; i++) {
            gauche[i][0] = mur;
        }
        gauche[0][3] = mur;
        gauche[0][9] = mur;
        gauche[1][5] = mur;
        gauche[1][11] = mur;
        gauche[2][8] = mur;
        gauche[3][15] = mur;
        gauche[4][4] = mur;
        gauche[4][10] = mur;
        gauche[5][6] = mur;
        gauche[6][2] = mur;
        gauche[6][12] = mur;
        gauche[7][9] = mur;
        gauche[8][9] = mur;
        gauche[9][12] = mur;
        gauche[10][4] = mur;
        gauche[10][11] = mur;
        gauche[11][6] = mur;
        gauche[12][2] = mur;
        gauche[12][15] = mur;
        gauche[13][4] = mur;
        gauche[14][11] = mur;
        gauche[15][4] = mur;
        gauche[15][14] = mur;


        //remplissage des coté droite des cases
        for (int i = 0; i <= 15; i++) {
            droite[i][15] = mur;
        }
        droite[0][2] = mur;
        droite[0][8] = mur;
        droite[1][4] = mur;
        droite[1][10] = mur;
        droite[2][7] = mur;
        droite[3][14] = mur;
        droite[4][3] = mur;
        droite[4][9] = mur;
        droite[5][5] = mur;
        droite[6][1] = mur;
        droite[6][11] = mur;
        droite[7][6] = mur;
        droite[8][6] = mur;
        droite[9][11] = mur;
        droite[10][3] = mur;
        droite[10][10] = mur;
        droite[11][5] = mur;
        droite[12][1] = mur;
        droite[12][14] = mur;
        droite[13][3] = mur;
        droite[14][10] = mur;
        droite[15][3] = mur;
        droite[15][13] = mur;
    }

    @Override
   // Murs doublés pour ce toString temporaire
    public String toString() {

        String g = "\n";

        for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 15; j++) {

                if (gauche[i][j]) {
                    g = g + "|";
                } else {
                    g = g + " ";
                }
                if (bas[i][j]) {
                    g = g + "_";
                } else if (haut[i][j]) {
                    g = g + "‾";
                } else {
                    g = g + " ";
                }
                if (droite[i][j]) {
                    g = g + "|";
                } else {
                    g = g + " ";
                }
            }
            g= g+"\n";
        }

        return g;
    }


    public boolean[][] getHaut() {
        return haut;
    }

    public boolean[][] getBas() {
        return bas;
    }

    public boolean[][] getGauche() {
        return gauche;
    }

    public boolean[][] getDroite() {
        return droite;
    }


    public boolean canGoUp(int x, int y){
        return haut[x][y];
    }
    public boolean canGoDown(int x,int y){
        return bas[x][y];
    }
    public boolean canGoLeft(int x, int y){
        return gauche[x][y];
    }
    public boolean canGoRight(int x, int y){
        return droite[x][y];
    }
}

