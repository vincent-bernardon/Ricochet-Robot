package code.resolution;

import code.Etat;

import java.util.Objects;

public class Couple2 {
    private Etat etat;
    private Couple2 pere;

    public Couple2(Etat e, Couple2 pere){
        etat=e;
        this.pere = pere;
    }

    //retourne chaque mouvement d'un robot avec son etat pere --> etat fils
    public String toString(){
        if(pere==null)return etat.toString();
        String r = "";
        int robotbougé=0;
        for(int i=0;i<etat.getCooRobots().length;i++){
            if(etat.getCooRobots()[i][0]!=pere.getEtat().getCooRobots()[i][0]&&etat.getCooRobots()[i][1]!=pere.getEtat().getCooRobots()[i][1]){
                robotbougé = i;
                System.out.println(robotbougé);
            }
        }

        r+="Mouvement du Robot "+(robotbougé+1)+": x="+pere.getEtat().getCooRobots()[robotbougé][0]+" y= "+pere.getEtat().getCooRobots()[robotbougé][1]+
                " ---> "+"x="+etat.getCooRobots()[robotbougé][0]+" y= "+etat.getCooRobots()[robotbougé][1]+"\n";

        r+= pere.toString()+etat.toString();
        return r;
    }

    public Etat getEtat() {
        return etat;
    }

    public Couple2 getPere() {
        return pere;
    }

    //retourne le nombre de coups de la base jusqu'a l'état actuel
    public int getNbCoups(){
        Couple2 aux = this;
        int nbEtat = 0;
        while(aux.getPere()!=null){
            aux = aux.getPere();
            nbEtat++;
        }
        return nbEtat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple2 couple = (Couple2) o;
        return Objects.equals(etat, couple.etat) && Objects.equals(pere, couple.pere);
    }
    @Override
    public int hashCode() {
        return Objects.hash(etat, pere);
    }
}
