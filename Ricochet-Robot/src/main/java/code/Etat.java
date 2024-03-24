package code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Etat{

	private int[][] cooRobots;  //Coordonnées des robots

    /**
     * @param x = Nombre de robots total dans le monde
     * Creer un tableau de x+1 ligne et 2 colonnes
     * en fixant les ligne on fixe les robots differents
     *          cooglob[0][]=Robot rouge
     *          cooglob[1][]=Robot vert     ICI il faut definir une constante pour JAUNE, VERT, ROUGE,
     *          cooglob[2][]=Robot jaune
     *          etc ...
     *
     *          On définira une constante une fois notre généréFIls valide
     */
    //ajoute les etats d'un nombre x de robots
	public Etat(int x, Objectif obj){
        Random r = new Random();
        Boolean valide = true;
        int X;
        int Y;
        cooRobots = new int[x][2];
        //initialise aléatoirement les coordonnées d'un robot
        for (int i = 0; i < cooRobots.length; i++) {
        do{
            X = r.nextInt(16);
            Y = r.nextInt(16);

        }
        //tant que les coordonnées du robot ne sont pas coohérentes avec le jeu de nouvelles coordonnées sont attribués
        while((dejaExiste(X,Y))||((X==7&&Y==7)||(X==7&&Y==8)||(X==8&&Y==7)||(X==8&&Y==8))&&(obj.getCooObjectif()[0]!=X && obj.getCooObjectif()[1] != Y));
        cooRobots[i][0]=X;
        cooRobots[i][1]=Y;
        }
    }

    public Etat(Etat e){
        this.cooRobots = new int[e.cooRobots.length][e.cooRobots[0].length];
        for (int i = 0; i < cooRobots.length; i++) {
            for (int j = 0; j < cooRobots[0].length; j++) {
                cooRobots[i][j]=e.cooRobots[i][j];
            }
        }

    }

    public boolean dejaExiste(int x,int y){
        for (int i = 0; i< cooRobots.length ; i++) {
            if(x== cooRobots[i][0]&&y== cooRobots[i][1]) return true;
        }
        return false;
    }

    //retourne les coordonnées d'un robot
    public int[][] getCooRobots() {
        return cooRobots;
    }

    //coordonnées des robots dans le monde actuel
    @Override
    public String toString() {
        String r = "Coordonées des robots =\n";
        for (int i = 0; i < cooRobots.length; i++) {
            r = r+ "Robot "+(i+1)+" = x="+ cooRobots[i][0]+"  y="+ cooRobots[i][1]+"\n";
        }
        return r;
    }
    //genere un fils en fonction des collisions possibles d'un robot
    public HashSet<Etat> genererFilsRobot(PlateauJeu p, int robot){

        Etat ne = new Etat(this);
        HashSet<Etat> r = new HashSet<>();

        // fils deplacement haut
        int x = cooRobots[robot][0],y = cooRobots[robot][1];
        while(!p.canGoUp(x,y)){
            x--;
            if(dejaExiste(x,y)){
                x++;
                break;
            }
        }
        ne.setXYRobot(robot,x,y);
        if(!(ne.getCooRobots()[robot][0]== cooRobots[robot][0]&&ne.getCooRobots()[robot][1]== cooRobots[robot][1])) {
            r.add(ne);
        }

        // fils deplacement bas
        x = cooRobots[robot][0];
        y = cooRobots[robot][1];
        while(!p.canGoDown(x,y)){
            x++;
            if(dejaExiste(x,y)){
                x--;
                break;
            }
        }
        ne = new Etat(this);
        ne.setXYRobot(robot,x,y);
        if(!(ne.getCooRobots()[robot][0]== cooRobots[robot][0]&&ne.getCooRobots()[robot][1]== getCooRobots()[robot][1])) {
            r.add(ne);
        }

        //fils deplacement gauche
        x = cooRobots[robot][0];
        y = cooRobots[robot][1];
        while(!p.canGoLeft(x,y)){
            y--;
            if(dejaExiste(x,y)){
                y++;
                break;
            }
        }
        ne = new Etat(this);
        ne.setXYRobot(robot,x,y);
        if(!(ne.getCooRobots()[robot][0]== cooRobots[robot][0]&&ne.getCooRobots()[robot][1]== getCooRobots()[robot][1])) {
            r.add(ne);
        }

        //fils deplacement droite
        x = cooRobots[robot][0];
        y = cooRobots[robot][1];
        while(!p.canGoRight(x,y)){
            y++;
            if(dejaExiste(x,y)){
                y--;
                break;
            }
        }
        ne = new Etat(this);
        ne.setXYRobot(robot,x,y);
        if(!(ne.getCooRobots()[robot][0]== cooRobots[robot][0]&&ne.getCooRobots()[robot][1]== getCooRobots()[robot][1])) {
            r.add(ne);
        }
        return r;
    }
    //range tout les fils de tous les robot dans un hashset
    public HashSet<Etat> genererFils(PlateauJeu p){

        HashSet<Etat> r = new HashSet<>();

        for (int i = 0; i < cooRobots.length; i++) {
            r.addAll(genererFilsRobot(p, i));
        }
        
        return r;
    }

    //Met de nouvelles coordonnées a un robot pour les tests
    public void setXYRobot(int robot,int x,int y){

        cooRobots[robot][0] = x;
        cooRobots[robot][1] = y;

    }
    //Verifie si les coordonnées de l'objectifs sont les même que les coordonnées du robot qui est censé y etre
    public boolean estGagnant(Objectif objectif){
        if (objectif.getCooObjectif()[0]== cooRobots[0][0]&&objectif.getCooObjectif()[1]== cooRobots[0][1]){
            System.out.println("VICTORY !!");
            return true;
        }
        return false;
    }

    //utilisé pour l'ia
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etat etat = (Etat) o;
        return Arrays.equals(cooRobots, etat.cooRobots);
    }
    //idem
    @Override
    public int hashCode() {
        return Arrays.hashCode(cooRobots);
    }

    //fonctions de deplacement simple haut bas gauche droite

    public Etat goDroite(int numRobot,PlateauJeu p){
        Etat retour = new Etat(this);
        while(!p.canGoRight(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
            retour.cooRobots[numRobot][1]++;
            if(dejaExiste(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
                retour.cooRobots[numRobot][1]--;
                break;
            }
        }
        return retour;
    }

    public Etat goGauche(int numRobot,PlateauJeu p){
        Etat retour = new Etat(this);
        while(!p.canGoLeft(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
            retour.cooRobots[numRobot][1]--;
            if(dejaExiste(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
                retour.cooRobots[numRobot][1]++;
                break;
            }
        }
        return retour;
    }

    public Etat goBas(int numRobot,PlateauJeu p){
        Etat retour = new Etat(this);
        while(!p.canGoDown(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
            retour.cooRobots[numRobot][0]++;
            if(dejaExiste(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
                retour.cooRobots[numRobot][0]--;
                break;
            }
        }
        return retour;
    }

    public Etat goHaut(int numRobot,PlateauJeu p){
        Etat retour = new Etat(this);
        while(!p.canGoUp(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
            retour.cooRobots[numRobot][0]--;
            if(dejaExiste(retour.cooRobots[numRobot][0],retour.cooRobots[numRobot][1])){
                retour.cooRobots[numRobot][0]++;
                break;
            }
        }
        return retour;
    }


}
/*public static enum Direction{
        HAUT,
        BAS,
        GAUCHE,
        DROITE,
    }
    /**
     *
     * @param d
     * @param plateau
     * @param caseDuRobot
     * @return nouvelleCase

    public int[] deplacement(Direction d,Tableau plateau,int[] caseDuRobot){

	    int x = caseDuRobot[0];
	    int y = caseDuRobot[1];
	    switch (d){
            case HAUT:
                while(!plateau.canGoUp(x,y)){
                    x++;
                }
                return new int[]{x, y};
            case BAS:
                while(!plateau.canGoDown(x,y)){
                    x--;
                }
                return new int[]{x, y};
            case GAUCHE:
                while(!plateau.canGoLeft(x,y)){
                    y--;
                }
                return new int[]{x, y};
            case DROITE:
                while(!plateau.canGoRight(x,y)){
                    y++;
                }
                return new int[]{x, y};
        }
        return caseDuRobot;
    }*/