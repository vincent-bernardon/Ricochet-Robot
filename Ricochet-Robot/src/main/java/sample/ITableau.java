package sample;

import code.Etat;
import code.Partie;
import code.PlateauJeu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;

public class ITableau {
    public GridPane tableau;
    private final boolean[][] haut,bas,gauche,droite;
    private int robotselectione = 0;
    private int compte = 1;
    private Partie partie;
    private Etat robots;
    private PlateauJeu plateau;
    private int tailleCase;

    public ITableau(int taille) {
        tableau = new GridPane();
        tableau.addRow(16); //définition des lignes
        tableau.addColumn(16); //définition des colonnes
        partie = new Partie(4);
        haut= partie.getPlateau().getHaut();
        droite = partie.getPlateau().getDroite();
        gauche = partie.getPlateau().getGauche();
        bas = partie.getPlateau().getBas();
        robots = partie.getEtat();
        plateau = partie.getPlateau();
        tailleCase = taille;
    }

    public GridPane getGridPane() {
        return tableau;
    }

    public void handlerClavier(Scene scene, int tailleCase, Stage primaryStage){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //fonctions pour selectionner les robots
                if(keyEvent.getCode() == KeyCode.A) {
                    System.out.println("Vert (1)");
                    robotselectione = 0;
                    genererTableau(tailleCase);
                    keyEvent.consume();
                }
                if(keyEvent.getCode() == KeyCode.Z) {
                    System.out.println("Rouge (2)");
                    robotselectione = 1;
                    genererTableau(tailleCase);
                    keyEvent.consume();
                }
                if(keyEvent.getCode() == KeyCode.E) {
                    System.out.println("Bleu (3)");
                    robotselectione = 2;
                    genererTableau(tailleCase);
                    keyEvent.consume();
                }
                if(keyEvent.getCode() == KeyCode.R) {
                    System.out.println("Jaune (4)");
                    robotselectione = 3;
                    genererTableau(tailleCase);
                    keyEvent.consume();
                }
                //fonctions pour les déplacements
                if(keyEvent.getCode().equals(KeyCode.UP)){
                    robots = robots.goHaut(robotselectione, plateau);
                    genererTableau(tailleCase); //on refresh le tableau
                    System.out.println("UP");
                    compte++;
                    keyEvent.consume();
                }
                if(keyEvent.getCode().equals(KeyCode.DOWN)){
                    robots = robots.goBas(robotselectione, plateau);
                    genererTableau(tailleCase);
                    System.out.println("DOWN");
                    compte++;
                    keyEvent.consume();
                }
                if(keyEvent.getCode().equals(KeyCode.LEFT)){
                    robots = robots.goGauche(robotselectione, plateau);
                    genererTableau(tailleCase);
                    System.out.println("LEFT");
                    compte++;
                    keyEvent.consume();
                }
                if(keyEvent.getCode().equals(KeyCode.RIGHT)){
                    robots = robots.goDroite(robotselectione, plateau);
                    genererTableau(tailleCase);
                    System.out.println("RIGHT");
                    compte++;
                    keyEvent.consume();
                }

                if(robots.estGagnant(partie.getObjectif())) {
                    victoire(scene, tailleCase, primaryStage); //on vérifie si le robot est sur l'objectif
                }

            }
        });

    }

    public void victoire(Scene scene, int taillecase, Stage primaryStage){ //affiche la vistoire si le robot est sur l'objectif
        /*Image image = new Image("sample/victoir.png");
        BackgroundImage bi = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bi);*/
        Label co = new Label("nombre de coup fait : "+(compte-1));
        BorderPane bopa = new BorderPane();
        VBox pa = new VBox();
        //pa.setBackground(bGround);
        Button relance = new Button("Relancer");
        relance.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    ITableau tablo = new ITableau(tailleCase);
                    tablo.genererTableau(tailleCase);
                    Scene ici = new Scene(tablo.getGridPane(), tailleCase * 16, tailleCase * 16);
                    primaryStage.setScene(ici);
                    tablo.handlerClavier(ici, tailleCase, primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        pa.getChildren().add(relance);
        pa.getChildren().add(co);
        pa.alignmentProperty();
        pa.setSpacing(taillecase);
        pa.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        bopa.setCenter(pa);
        Scene voila = new Scene(bopa, tailleCase*16, tailleCase*16);
        primaryStage.setScene(voila);
        /*FlowPane flowPane = new FlowPane();
        flowPane.setStyle("-fx-color: RED;");
        primaryStage.getScene().setFill(Color.RED);*/
    }

    public void genererTableau(int tailleCaze){ //on ajoute les murs dans le plateau
        tableau.setGridLinesVisible(false);
        tableau.getChildren().clear();
        tableau.setGridLinesVisible(true); //permet l'affichage des lignes séparatrices

        //boucles pour généré les cases
        for (int ligne = 0; ligne < haut.length; ligne++) { //boucle pour les lignes
            for (int colonne = 0; colonne < haut[0].length; colonne++) { //boucle pour les colones
                BorderPane bord = new BorderPane(); //permet d'avoir les 4 position de mur dans 1 case
                bord.setMinSize(tailleCaze,tailleCaze); //permet de faire que les cases soit de même taille
                if(haut[ligne][colonne]){ //création des murs du haut
                    Pane couleur = new Pane(); //permet d'étendre la couleur
                    Label estvisible = new Label(" "); //permet de rendre visible la couleur
                    couleur.setStyle("-fx-background-color: #654321;");
                    couleur.getChildren().add(estvisible);
                    couleur.setPrefSize(5,5);
                    bord.setTop(couleur);
                }
                if(bas[ligne][colonne]){ //création des murs du bas (même que au-dessus)
                    Pane couleur = new Pane();
                    Label estvisible = new Label(" ");
                    couleur.setStyle("-fx-background-color: #654321;");
                    couleur.getChildren().add(estvisible);
                    couleur.setPrefSize(5,5);
                    bord.setBottom(couleur);
                }
                if(gauche[ligne][colonne]){ //création des murs de gauche (même que au-dessus)
                    Pane couleur = new Pane();
                    Label estvisible = new Label(" ");
                    couleur.setStyle("-fx-background-color: #654321;");
                    couleur.getChildren().add(estvisible);
                    couleur.setPrefSize(5,5);
                    bord.setLeft(couleur);
                }
                if(droite[ligne][colonne]){ //création des murs de droite (même que au-dessus)
                    Pane couleur = new Pane();
                    Label estvisible = new Label(" ");
                    couleur.setStyle("-fx-background-color: #654321;");
                    couleur.getChildren().add(estvisible);
                    couleur.setPrefSize(5,5);
                    bord.setRight(couleur);
                }
                if(colonne == partie.getObjectif().getCooObjectif()[1] && ligne == partie.getObjectif().getCooObjectif()[0]){
                    Pane couleur = new Pane();
                    Circle estvisible = new Circle(16, 16, 10);
                    estvisible.setStroke(Color.GREEN);
                    estvisible.setFill(Color.GREEN);
                    couleur.getChildren().add(estvisible);
                    couleur.setPrefSize(5,5);
                    bord.setCenter(couleur);
                }
                /*if(robots.getCooRobots()[0][0]==ligne&&robots.getCooRobots()[0][1]==colonne){ //création de robot
                    Pane couleur = new Pane();
                    Label estvisible = new Label("");
                    couleur.setStyle("-fx-background-color: green;");
                    couleur.getChildren().add(estvisible);
                    bord.setCenter(couleur);
                }*/


                tableau.add(bord,colonne,ligne); //ajoute dans la case


                for (int i = 0; i < robots.getCooRobots().length; i++) {//création de robot
                    if(ligne == robots.getCooRobots()[i][0] && colonne == robots.getCooRobots()[i][1] ) {
                        switch (i) {
                            case 0:
                                Pane couleur = new Pane();
                                Label estvisible = new Label(""+compte);
                                couleur.setStyle("-fx-background-color: green;");
                                couleur.getChildren().add(estvisible);
                                bord.setCenter(couleur);
                                break;
                            case 1:
                                Pane couleur1 = new Pane();
                                Label estvisible1 = new Label(""+compte);
                                couleur1.setStyle("-fx-background-color: red;");
                                couleur1.getChildren().add(estvisible1);
                                bord.setCenter(couleur1);
                                break;
                            case 2:
                                Pane couleur2 = new Pane();
                                Label estvisible2 = new Label(""+compte);
                                couleur2.setStyle("-fx-background-color: blue;");
                                couleur2.getChildren().add(estvisible2);
                                bord.setCenter(couleur2);
                                break;
                            case 3:
                                Pane couleur3 = new Pane();
                                Label estvisible3 = new Label(""+compte);
                                couleur3.setStyle("-fx-background-color: yellow;");
                                couleur3.getChildren().add(estvisible3);
                                bord.setCenter(couleur3);
                                break;
                            default:
                                Pane couleur4 = new Pane();
                                Label estvisible4 = new Label(""+compte);
                                couleur4.getChildren().add(estvisible4);
                                bord.setCenter(couleur4);
                                break;

                        }

                    }
                    /*if(porteAnd==true) {
                        Pane couleur = new Pane(); //permet d'étendre la couleur
                        Label estvisible = new Label(" "); //permet de rendre visible la couleur
                        couleur.getChildren().add(estvisible);
                        bord.setCenter(couleur);
                    }*/
                }
            }
        }

        tableau.autosize();
    }
}
