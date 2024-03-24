package code.resolution;

import code.Etat;
import code.EtatBit;

import java.util.Objects;

public class Couple {
    private EtatBit etat;
    private Couple pere;

    public Couple(EtatBit e, Couple pere){
        etat=e;
        this.pere = pere;
    }

    //retourne chaque mouvement d'un robot avec son etat pere --> etat fils
    public String toString(){
        if(pere==null)return etat.toString();
        String r = "";
        int robotbougé=0;
        for(int i=0;i<etat.getCooRobots().length;i++){
            if(etat.getCooRobots()[i]!=pere.getEtat().getCooRobots()[i]){
                robotbougé = i;
            }
        }

        r+="Mouvement du Robot "+(robotbougé+1)+": x="+pere.getEtat().obtenirX(pere.getEtat().getCooRobots()[robotbougé])+" y= "+pere.getEtat().obtenirY(pere.getEtat().getCooRobots()[robotbougé])+
                " ---> "+"x="+etat.obtenirX(etat.getCooRobots()[robotbougé])+" y= "+etat.obtenirY(etat.getCooRobots()[robotbougé])+"\n";

        r+= pere.toString()+etat.toString();
        return r;
    }

    public EtatBit getEtat() {
        return etat;
    }

    public Couple getPere() {
        return pere;
    }

    //retourne le nombre de coups de la base jusqu'a l'état actuel
    public int getNbCoups(){
        Couple aux = this;
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
        Couple couple = (Couple) o;
        return Objects.equals(etat, couple.etat) && Objects.equals(pere, couple.pere);
    }
    @Override
    public int hashCode() {
        return Objects.hash(etat, pere);
    }
}
