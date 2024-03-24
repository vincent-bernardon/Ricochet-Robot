package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int tailleCaze = 40;
        ITableau tableau = new ITableau(40);
        tableau.genererTableau(tailleCaze); //on ajoute les murs dans le plateau

        Scene scene = new Scene(tableau.getGridPane(), tailleCaze * 16, tailleCaze * 16);

        tableau.handlerClavier(scene, tailleCaze, primaryStage);

        primaryStage.setScene(scene); //taille du cadre (fenêtrait)
        primaryStage.setTitle("Ricoche-Robot"); //nom de la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}