package code;

import javafx.util.converter.ByteStringConverter;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class EtatBit {
    byte cooRobots[];

    //constructeur par defaut ayant 4 robot
    public EtatBit() {
        EtatBit e = new EtatBit(4);
        cooRobots = e.cooRobots;
    }

    //constructeur pour un etat a x robots
    public EtatBit(int x){
        Random r = new Random();


        cooRobots = new byte[x];

        for(int i = 0;i< cooRobots.length;i++){
            byte coo;
            int X=0;
            int Y=0;
            do{
                X = r.nextInt(16);
                Y = r.nextInt(16);


            }
            //tant que les coordonnées du robot ne sont pas coohérentes avec le jeu de nouvelles coordonnées sont attribués
            while((dejaExiste(X,Y))||((X==7&&Y==7)||(X==7&&Y==8)||(X==8&&Y==7)||(X==8&&Y==8)));
            coo = (byte)X;
            coo<<=4;
            coo += (byte)Y;
            cooRobots[i]=coo;
        }
    }


    //Constructeur par copie
    public EtatBit(EtatBit base){
        cooRobots = new byte[base.cooRobots.length];
        for (int i = 0; i < base.cooRobots.length; i++) {
            cooRobots[i]=base.cooRobots[i];
        }
    }

    //dissocie le X de l'octet representant les coordonnées
    public byte obtenirX(byte param){
        byte x=param;
        x >>>= 4;
        if(x<0) {
            byte deuxcentquarante = (byte)240;
            x = (byte)(x^deuxcentquarante);
        }
        return x;
    }
    //dissocie le Y de l'octet representant les coordonnées
    public byte obtenirY(byte param){
        byte y=param;
        byte quinze = (byte)15;
        y = (byte)(y&quinze);
        return y;
    }

    //fonction qui verifie si les coordonés ne sont pas deja utilisé dans le monde
    public boolean dejaExiste(int X,int Y){
        for (int i = 0; i < cooRobots.length; i++) {
            if(((byte)X)==obtenirX(cooRobots[i])&&((byte)Y)==obtenirY(cooRobots[i])) return true;
        }
        return false;
    }

    public HashSet<EtatBit> genererFilsRobot(PlateauJeu p, int robot){

        HashSet<EtatBit> r = new HashSet<>();
        EtatBit ne;

        //Generation du fils en deplacement vers le haut
        ne = new EtatBit(this);
        int x = obtenirX(cooRobots[robot]);
        int y = obtenirY(cooRobots[robot]);
        while(!p.canGoUp(x,y)){
            x--;
            if(dejaExiste(x,y)){
                x++;
                break;
            }
        }
        ne.setXYRobot(robot,x,y);
        if(ne.getCooRobots()[robot]!=cooRobots[robot]) {
            r.add(ne);
        }

        //Generation du fils en deplacement vers le bas
        ne = new EtatBit(this);
        x = obtenirX(cooRobots[robot]);
        y = obtenirY(cooRobots[robot]);
        while(!p.canGoDown(x,y)){
            x++;
            if(dejaExiste(x,y)){
                x--;
                break;
            }
        }
        ne.setXYRobot(robot,x,y);
        if(ne.getCooRobots()[robot]!=cooRobots[robot]) {
            r.add(ne);
        }

        //Generation du fils en deplacement vers la gauche
        ne = new EtatBit(this);
        x = obtenirX(cooRobots[robot]);
        y = obtenirY(cooRobots[robot]);
        while(!p.canGoLeft(x,y)){
            y--;
            if(dejaExiste(x,y)){
                y++;
                break;
            }
        }
        ne.setXYRobot(robot,x,y);
        if(ne.getCooRobots()[robot]!=cooRobots[robot]) {
            r.add(ne);
        }

        //Generation du fils en deplacement vers le droite
        ne = new EtatBit(this);
        x = obtenirX(cooRobots[robot]);
        y = obtenirY(cooRobots[robot]);
        while(!p.canGoRight(x,y)){
            y++;
            if(dejaExiste(x,y)){
                y--;
                break;
            }
        }
        ne.setXYRobot(robot,x,y);
        if(ne.getCooRobots()[robot]!=cooRobots[robot]) {
            r.add(ne);
        }

        return r;
    }

    public void setXYRobot(int robot,int x,int y){
        byte newCoo= (byte)x;

        newCoo<<=4;
        newCoo+=y;

        cooRobots[robot]=newCoo;
    }
    public HashSet<EtatBit> genererFils(PlateauJeu p){

        HashSet<EtatBit> r = new HashSet<>();

        for (int i = 0; i < cooRobots.length; i++) {
            r.addAll(genererFilsRobot(p, i));
        }

        return r;
    }

    public boolean estGagnant(ObjectifBit objectif){
        return (cooRobots[1]==objectif.getCooObjectif());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtatBit etatBit = (EtatBit) o;
        return Arrays.equals(cooRobots, etatBit.cooRobots);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cooRobots);
    }

    //Fonction qui affiche un byte sous forme de suite de 1 et de 0 (en binaire quoi)
    public void showBits(byte param) {
        int mask = 1 << 8;

        for (int i = 1; i <= 8; i++,
                param <<= 1) {
            System.out.print((param & mask) ==
                    0 ? "0" : "1");
            if (i % 8 == 0)
                System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public String toString(){
        String r = "Coordonnées des robots :\n";
        for (int i = 0; i < cooRobots.length; i++) {
            byte X = obtenirX(cooRobots[i]);
            byte Y = obtenirY(cooRobots[i]);
            r += "Robot "+(i+1)+" : X = "+X+"  Y = "+Y+"\n";
        }
        return r;
    }

    public byte[] getCooRobots() {
        return cooRobots;
    }
}
