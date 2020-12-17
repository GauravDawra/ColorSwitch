package savedGamesPage;

import application.App;
import game.Game;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.ResourceBundle;

//class SavedGameLabel {
//
//    private static ResourceBundle bundle = ResourceBundle.getBundle("/resources/DimensionBundle");
//
//    public static Label getLabel(Game g, int ind) {
//        Label lbl = new Label(g.getScore().toString());
//        lbl.setLayoutX(10);
//        lbl.setLayoutY(ind * 100);
//        lbl.setPrefHeight(90);
//        lbl.setPrefWidth((Double)bundle.getObject("SCREEN_WIDTH") - 10);
//        return lbl;
//    }
//}
public class SavedGamesPage {
    private static SavedGamesPage page = null;

    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    private Scene sc;
    private AnchorPane par;

    private Label getLabel(Game g, int ind) {
        Label lbl = new Label(g.getScore().toString());
        lbl.setLayoutX(10);
        lbl.setLayoutY(100 + ind * 100);
        lbl.setPrefHeight(90);
        lbl.setPrefWidth(500);
        lbl.setFont(new Font("Arial", 32));
        lbl.setTextFill(Color.WHITE);
        return lbl;
    }

    public SavedGamesPage() {

    }

    public static SavedGamesPage getInstance() {
        if(page == null) page = new SavedGamesPage();
        return page;
    }

    public Scene getScene() {
        par = new AnchorPane();
//        par.setPrefHeight((Double)bundle.getObject("SCREEN_HEIGHT"));
//        par.setPrefHeight((Double)bundle.getObject("SCREEN_WIDTH"));
//        par.set
//        App app = App.getInstance();
        par.setStyle("-fx-background-color: black");
        int ind = 0;
        Label topLbl = new Label("Saved Games");
        par.getChildren().add(topLbl);
        topLbl.setLayoutX(Game.WIDTH/2);
        topLbl.setLayoutY(100);
        topLbl.setFont(new Font("Arial", 32));
        topLbl.setTextFill(Color.WHITE);
        par.setPrefHeight(800);
        par.setPrefWidth(500);
        ArrayList<Game> games = App.getInstance().getPastGames();
        for(Game g : games) {
            par.getChildren().add(getLabel(g, ind++));
            System.out.println("Another game");
        }
        sc = new Scene(par);
        return sc;
    }
}
