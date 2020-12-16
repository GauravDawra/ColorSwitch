package game;

import game.objects.Ball;
import game.objects.ColorPalette;
import game.objects.Star;
import game.obstacles.MediumRingObstacle;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.util.Duration;
import util.Vector;

import java.io.Serializable;
import java.sql.Time;
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
    private ArrayList<Obstacle> obst_list;
    private ArrayList<Star> star_list;
    private ArrayList<ColorPalette> palette_list;

    private Date date;

    public Game() {
        this.score = 0;
        this.state = State.PLAYING;
        this.date = java.util.Calendar.getInstance().getTime();
        this.component = new ArrayList<>();
        this.obst_list = new ArrayList<>();
        this.star_list = new ArrayList<>();
        this.palette_list = new ArrayList<>();
        this.ball = new Ball(WIDTH / 2, 700);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int sc) {
        if(sc >= 0 && sc > getScore())
            this.score = sc;
    }

    public void increaseScore() {
        this.score++;
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
//        launch();
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
        if(obj == null) return;
//            component.add(obj);
        if(obj instanceof Obstacle) obst_list.add((Obstacle)obj);
        else if(obj instanceof Star) star_list.add((Star)obj);
        else palette_list.add((ColorPalette)obj);

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

        root.getChildren().add(background);
        root.getChildren().add(obstacles);


        Scene sc = new Scene((Parent) root);
        primaryStage.setScene(sc);

        for(int i=0;i<3;i++){
            double x = WIDTH / 2;
            double y = 500 - i * OBSTACLE_SPACING;
            addComponent(new MediumRingObstacle(x, y));
            addComponent(new Star(x, y));
            addComponent(new ColorPalette(WIDTH/2, 500 - i * OBSTACLE_SPACING - OBSTACLE_SPACING / 2));
        }
        obstacles.getChildren().add(ball.getNode());
        for(GameObject o : component){
            obstacles.getChildren().add(o.getNode());
//            ((MediumRingObstacle)o).bindToBall(ball);
        }

        sc.setOnKeyPressed(event -> ball.jump());
        Timeline ballTime = new Timeline(
                new KeyFrame(Duration.millis(16), ActionEvent -> {
                    if(ball.getPosition().getY() + ball.getVelocity().getY() / 5.0 > HEIGHT-50){
                        ball.setPosition(WIDTH/2, HEIGHT-50);
                        ball.setVelocityY(0);
                    }
                    else {
                        ball.setPosition(ball.getPosition().getX(), ball.getPosition().getY() + ball.getVelocity().getY() / 5.0);
                        ball.setVelocityY(ball.getVelocity().getY() + 9.8 / 5.0);
                    }
                })
        );
        ballTime.setCycleCount(Timeline.INDEFINITE);
        ballTime.play();

        new AnimationTimer() {
            double Min = HEIGHT/2;
            int cnt = 0;
            @Override
            public void handle(long now) {

//                ball.setPosition(ball.getPosition().getX(), ball.getPosition().getY() + ball.getVelocity().getY() / 5.0);
//
//                ball.setVelocityY(ball.getVelocity().getY() + 9.8 / 5.0);

                if(Min > ball.getPosition().getY()) {
                    Min = ball.getPosition().getY();
                    obstacles.setTranslateY(-Min + HEIGHT / 2.0);
                }


                for(int i=0;i<obst_list.size();i++){
                    Obstacle o = obst_list.get(i);
                    if(((MediumRingObstacle)o).check(ball)){
                        System.out.println("collide"+cnt++);
                    }
                    if(o.getPosition().getY() > -obstacles.getTranslateY() + HEIGHT) {
                        obst_list.remove(i);
                        i--;
                    }
                }

                for(int i=0;i<star_list.size();i++){
                    Star o = star_list.get(i);
                    if(o.check(ball)){
                        o.remove();
                        obstacles.getChildren().remove(o.getNode());
                        increaseScore();
                    }
                    if(o.getPosition().getY() > -obstacles.getTranslateY() + HEIGHT) {
                        star_list.remove(i);
                        i--;
                    }
                }

                for(int i=0;i<palette_list.size();i++){
                    ColorPalette o = palette_list.get(i);
                    if(!o.done && o.check(ball)){
//                        System.out.println("color change"+cnt++);
                        ball.setColor(ColorPalette.getRandomColor(ball.getColor()));
                        o.remove();
                        obstacles.getChildren().remove(o.getNode());
                    }
                    if(o.getPosition().getY() > -obstacles.getTranslateY() + HEIGHT) {
                        palette_list.remove(i);
                        i--;
                    }
                }

                if(obst_list.get(obst_list.size()-1).getPosition().getY() - OBSTACLE_SPACING + 100 > Min - HEIGHT/2) {
//                    component.add(new MediumRingObstacle(WIDTH/2, component.get(component.size()-1).getPosition().getY()-OBSTACLE_SPACING));
                    double x = WIDTH / 2;
                    double y = obst_list.get(obst_list.size()-1).getPosition().getY()-OBSTACLE_SPACING;
                    addComponent(new MediumRingObstacle(x, y));
                    obstacles.getChildren().add(component.get(component.size()-1).getNode());
                    addComponent(new ColorPalette(x, y-OBSTACLE_SPACING/2));
                    obstacles.getChildren().add(component.get(component.size()-1).getNode());
                    addComponent(new Star(WIDTH/2, y));
                    obstacles.getChildren().add(component.get(component.size()-1).getNode());
//                    ((MediumRingObstacle)component.get(component.size()-1)).bindToBall(ball);
                }
//                System.out.println(obst_list.size() + palette_list.size());
            }
        }.start();
//        timer.start();

        primaryStage.show();
    }
}
