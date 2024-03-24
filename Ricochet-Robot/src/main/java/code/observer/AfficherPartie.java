package code.observer;
import code.Partie;

import java.util.Observable;
import java.util.Observer;

public class AfficherPartie implements Observer {

    public int[][] update(Partie p){
        return p.getEtat().getCooRobots();
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
