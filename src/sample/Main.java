package sample;

import game.objects.Ball;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import util.Color;
import util.Vector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Ball b = new Ball(100, 100);
        Ball b2 = new Ball(101, 104);
        System.out.println(Ball.UP_VELOCITY);
        System.out.println(Ball.cnt);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
//        System.out.println(Color.BLUE.getColor());
//        Vector v = new Vector();
//        v.add(new Vector(1,2));
//        System.out.println(v);
        launch(args);
    }
}
