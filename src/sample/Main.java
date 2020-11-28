package sample;

import game.Game;
import game.objects.Ball;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import util.Vector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        AnchorPane pallete = (AnchorPane) FXMLLoader.load(getClass().getResource("/resources/views/colorPallete/colorPallete.fxml"));
        root.getChildren().add(pallete);
        primaryStage.setTitle("Hello World");
        Ball b = new Ball(100, 100);
        root.getChildren().add(b.getNode());
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
//        System.out.println(Color.BLUE.getColor());
//        Vector v = new Vector();
//        v.add(new Vector(1,2));
//        System.out.println(v);
//        launch(args);
        Game g = new Game();

        g.play(args);
    }
}
