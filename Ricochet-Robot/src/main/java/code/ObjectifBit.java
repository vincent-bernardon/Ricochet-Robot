package code;

import java.util.Random;

public class ObjectifBit {
    private byte cooObjectif;


    public ObjectifBit(PlateauJeu plateau){
        Random r = new Random();

        int coodetabobjectifs = r.nextInt(16);
        byte[] tabobjectifs = genererToutesCooObjectif(plateau);
        cooObjectif = tabobjectifs[coodetabobjectifs];
    }

    public byte[] genererToutesCooObjectif(PlateauJeu plateau){
        byte[] tabobjectif = new byte[17];
        int l = 0;
        for(int i=1;i<15;i++){
            for (int j=1;j<15;j++){
                if ((plateau.canGoUp(i,j)&&(plateau.canGoRight(i,j)|| plateau.canGoLeft(i,j)))
                        ||(plateau.canGoDown(i,j)&&(plateau.canGoRight(i,j)|| plateau.canGoLeft(i,j)))){
                    byte coo = (byte)i;
                    coo <<= 4;
                    coo += (byte)j;
                    tabobjectif[l]=coo;
                    l++;
                }
            }
        }
        return tabobjectif;
    }

    public void setObjectif(int x, int y){
        cooObjectif=(byte)x;
        cooObjectif<<=4;
        cooObjectif+=(byte)y;
    }

    public byte getCooObjectif() {
        return cooObjectif;
    }
}
