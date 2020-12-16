package game;

import game.objects.Ball;
import game.objects.ColorPalette;
import game.obstacles.MediumRingObstacle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import util.Vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import game.objects.ColorPalette;

public class Game extends Application implements Serializable {
    public enum State {
        PLAYING,
        PAUSED,
        EXIT
    }

    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");

    public static final double GRAVITY = (Double) bundle.getObject("Gravity");
    public static final int HEIGHT = (Integer) bundle.getObject("SCREEN_HEIGHT");
    public static final int WIDTH = (Integer) bundle.getObject("SCREEN_WIDTH");
    public static final double OBSTACLE_SPACING = (Double) bundle.getObject("Obstacle_spacing");

    private Ball ball;
    private int score;
    private State state;
    private ArrayList<GameObject> component;
    private Date date;

    public Game() {
        this.score = 0;
        this.state = State.PLAYING;
        this.date = java.util.Calendar.getInstance().getTime();
        this.component = new ArrayList<>();
        this.ball = new Ball(WIDTH / 2, 700);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int sc) {
        if(sc >= 0 && sc > getScore())
            this.score = sc;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State s) {
        this.state = s;
    }

    public void play(String []args) {
        setState(State.PLAYING);
        // ...
        launch();
    }

    public void pause() {
        setState(State.PAUSED);
    }

    public void exit() {
        setState(State.EXIT);
    }

    public GameObject getComponent(int ind) {
        if(ind >= 0 && ind < component.size())
            return component.get(ind);
        return null;
    }

    public void addComponent(GameObject obj) {
        if(obj != null)
            component.add(obj);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* All this code is just for testing right now*/

        primaryStage.setTitle("Color Switch");
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setResizable(false);

        Group root = new Group();

        AnchorPane background = new AnchorPane();
        AnchorPane obstacles = new AnchorPane();
        AnchorPane player = new AnchorPane();

        background.setPrefSize(WIDTH, HEIGHT);
        background.setStyle("-fx-background-color: black;");

//        ColorPalette pallete = new ColorPalette(WIDTH / 2, 500);
//        //background.getChildren().add(pallete.getNode());
//        MediumRingObstacle ring = new MediumRingObstacle(WIDTH / 2, 500);
//        MediumRingObstacle ring1 = new MediumRingObstacle(WIDTH / 2, 150);
//        addComponent(ring);
//        addComponent(ring1);
//
        root.getChildren().add(background);
        root.getChildren().add(obstacles);
//
//
////        ring.bindToBall(ball);
////        ring1.bindToBall(ball);
//        MediumRingObstacle  ring2 = new MediumRingObstacle(WIDTH/2, -300);
//        ColorPalette pal =new ColorPalette(WIDTH/2, 300);
//        addComponent(pal);
//        addComponent(ring2);
////        pal.bindToBall(ball);

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);

        for(int i=0;i<3;i++){
            addComponent(new MediumRingObstacle(WIDTH/2, 500 - i * OBSTACLE_SPACING));
        }

        obstacles.getChildren().add(ball.getNode());
        for(GameObject o : component){
            obstacles.getChildren().add(o.getNode());
//            if(o instanceof MediumRingObstacle)
//                ((MediumRingObstacle)o).bindToBall(ball);
        }

        sc.setOnKeyPressed(event -> ball.jump());
        new AnimationTimer() {
            double Min = HEIGHT/2;
            @Override
            public void handle(long now) {

                ball.setPosition(ball.getPosition().getX(), ball.getPosition().getY() + ball.getVelocity().getY() / 5.0);

                ball.setVelocityY(ball.getVelocity().getY() + 9.8 / 5.0);

                if(Min > ball.getPosition().getY()) {
                    Min = ball.getPosition().getY();
                    obstacles.setTranslateY(-Min + HEIGHT / 2.0);
                }
//                ring.getNoOfColors(); // ignore
//                if(pal.isVisible()); // ignore
//                System.out.println(obstacles.getTranslateY() + ", " + ball.getPosition().getY());
//                for(GameObject o : component) {
                for(int i=0;i<component.size();i++){
                    GameObject o = component.get(i);
                    if(o.getPosition().getY() > -obstacles.getTranslateY() + HEIGHT) {
//                        o.setVisibility(false);
                        component.remove(i);
                        i--;
//                        System.out.println("YOHI");
                    }

                }
                if(component.get(component.size()-1).getPosition().getY() - OBSTACLE_SPACING + 100 > Min - HEIGHT/2) {
                    component.add(new MediumRingObstacle(WIDTH/2, component.get(component.size()-1).getPosition().getY()-OBSTACLE_SPACING));
                    obstacles.getChildren().add(component.get(component.size()-1).getNode());
                }
                System.out.println(component.size());
            }
        }.start();
//        timer.start();

        primaryStage.show();
    }
}
