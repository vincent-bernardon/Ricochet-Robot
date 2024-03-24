package code;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Partie implements Observable{
    private PlateauJeu plateau;
    private Etat etat;
    private Objectif objectif;

    //Construction d'une partie avec un nouveau plateau, un objectif générer dans ce plateau et un nombre de robots prédéfinis
    public Partie(int x){
        plateau = new PlateauJeu();
        objectif = new Objectif(plateau);
        etat = new Etat(x, objectif);
    }

    public Partie(){
        plateau = new PlateauJeu();
        objectif = new Objectif(plateau);
        etat = new Etat(4,objectif);
    }

    //le toString générique
    @Override
    public String toString(){
        return "Partie{" +
                "plateau=" + plateau +
                ", etat=" + etat +
                ", objectif=" + objectif +
                '}';
    }

    //les 3 getters
    public PlateauJeu getPlateau(){
        return plateau;
    }

    public Etat getEtat(){
        return etat;
    }

    public Objectif getObjectif(){
        return objectif;
    }

    //fonctions obligatoires on touche pas
    @Override public void addListener(InvalidationListener invalidationListener){}@Override public void removeListener(InvalidationListener invalidationListener){}
}
