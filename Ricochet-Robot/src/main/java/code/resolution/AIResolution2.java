package code.resolution;

import code.Etat;
import code.Objectif;
import code.PlateauJeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class AIResolution2 {

        private LinkedHashSet<Couple2> frontiere;//Les états du monde générés mais pas encore exploré
        private HashSet<Etat> dejaVu; //Les états du monde deja vu

        //initialisation des état dejaVu et de la frontiere actuelle
        public AIResolution2(){
            //la frontière c'est les etats qui n'ont pas été encore vu comme gagnants
            frontiere = new LinkedHashSet<>();
            //c'est les etats qui ont été déja vu comme non gagnants
            dejaVu = new HashSet<>();
        }

        //pour la fonction resoudre nous ajoutons les premiers etat dans la frontière actuelle ainsi que les déja vu
        public Couple2 resoudre(Etat premierEtat, Objectif objectif, PlateauJeu plateau) {
            Couple2 current = new Couple2(premierEtat,null);
            frontiere.add(current);
            dejaVu.add(premierEtat);

            //tant que l'etat n'a pas trouvé une solution atteignant l'objectif et qu'il reste encore des etats a exploré on continue
            //a ajouter les etats fils actuels dans la frontière
            while((!current.getEtat().estGagnant(objectif))&&(!frontiere.isEmpty())){
                ArrayList<Etat> fils = new ArrayList<>();
                fils.addAll(current.getEtat().genererFils(plateau));

                //si l'etat generer n'a pas déja été vu on l'ajoute dans la frontière
                for (Etat e:fils) {
                    if(!dejaVu.contains(e)) {
                        frontiere.add(new Couple2(e, current));
                    }
                }
                frontiere.remove(current);

                //si il reste des état pas encore vu on dit que l'etat actuel correspond a la prochaine vague de la frontière
                if(!frontiere.isEmpty()) {
                    current = frontiere.iterator().next();
                }
                //après tout ca on ajoute a deja vu les etats actuels
                dejaVu.add(current.getEtat());
                //pour finir on affiche le nombre d'etat deja vu a chaque boucle
//                System.out.println(dejaVu.size());

                //on arrête actuellement a partir de 500 000 etats on décide qu'il n'y a pas de solution on retourne une erreur
                //on compte tester ici si l'etat actuel ne dépasse pas un certain nombre de coups
//                if(dejaVu.size()>500000) throw new OutOfMemoryError();
            }
            return current;
        }

    public HashSet<Etat> getDejaVu() {
        return dejaVu;
    }
}


