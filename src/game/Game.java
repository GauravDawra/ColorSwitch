package game;

import application.App;
import continueGame.continueGameController;
import game.objects.Ball;
import game.objects.ColorPalette;
import game.objects.Star;
import game.obstacles.MediumRingObstacle;
import game.obstacles.WindmillObstacle;
import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import pausePage.pausePageController;
import sceneLoader.SceneLoader;
import util.Vector;

import java.io.IOException;
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
    transient private AnimationTimer timer;
    transient private Timeline ballTime;
    private Date date;

    transient private Popup pausePopup;
    transient private Popup revivePopup;
    private boolean isReviveUsed;

    transient private Scene sc;
    transient AnchorPane background ;
    transient AnchorPane elements;
    transient Label scoreLbl;
    transient Node pauseBtn;

    public Game() {
        this.score = 0;
        this.state = State.PLAYING;
        this.date = java.util.Calendar.getInstance().getTime();
        this.component = new ArrayList<>();
        this.obst_list = new ArrayList<>();
        this.star_list = new ArrayList<>();
        this.palette_list = new ArrayList<>();
        this.isReviveUsed = false;
        this.ball = new Ball(WIDTH / 2, 700);
        try {
            setPausePage();
            setRevivePopup();
        } catch (Exception e) {}
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(int sc) {
        this.score = Math.max(0,sc);
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

    public void play() {
        setState(State.PLAYING);
//        timer.play();
        if(timer != null) timer.start();
        if(ballTime!=null) ballTime.play();
    }

    public void pause() {
        setState(State.PAUSED);
        timer.stop();
        ballTime.stop();
        try {
            SceneLoader.getLoader().setScore(getScore());
        } catch(Exception e){}
        pausePageController.show();
        handlePopupVisibility();
    }

    public void exit() {
        setState(State.EXIT);
        App.getInstance().setBestScore(Math.max(App.getInstance().getBestScore(), getScore()));
        App.getInstance().addNumOfStars(getScore());

        try {
            SceneLoader.getLoader().setScore(getScore());
            loadGameOverPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Popup createPopup(Node n) {
        Popup popup = new Popup();
        popup.getContent().add(n);
        popup.setX(App.getStage().getX() + WIDTH / 8);
        popup.setY(App.getStage().getY() + HEIGHT / 4);
        return popup;
    }

    private void setPausePage() throws IOException {
//        System.out.println(getScore());
        Scene pausePage = SceneLoader.getLoader().getPausePage(getScore());
        pausePopup = createPopup(pausePage.getRoot());
    }

    private void setRevivePopup() throws IOException {
        Scene continueGame = SceneLoader.getLoader().getContinueGame();
        revivePopup = createPopup(continueGame.getRoot());
        revivePopup.setX(WIDTH);
    }

    private void loadGameOverPage() throws IOException {
        Scene gameOver = SceneLoader.getLoader().getGameOverPage();
        App.getStage().setScene(gameOver);
        App.getStage().show();
    }

    public void handlePopupVisibility() {
        if (state == State.EXIT) return;

        try {
            if (pausePageController.visibility()) {
                pausePopup.show(App.getStage());
                ball.setVelocityY(0);
            } else {
                pausePopup.hide();
            }

            if (continueGameController.visibility()) {
                revivePopup.show(App.getStage());
                ball.setVelocityY(0);
                isReviveUsed = true;
            } else {
                revivePopup.hide();
                if (isReviveUsed) revivePopup = null;
            }
        } catch (Exception e) {}
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
        else {
            palette_list.add((ColorPalette) obj);
            lastPalette = obj.getPosition().getY();
        }

        component.add(obj);

    }

    private void setStageValues(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setResizable(false);
    }

    private Label getScoreLabel() {
        Label scoreLbl = new Label();
//        scoreLbl.textProperty().bind(new SimpleIntegerProperty(getScore()).asString());
        scoreLbl.setTextFill(Color.WHITE);
        scoreLbl.setLayoutX(50);
        scoreLbl.setLayoutY(50);
        scoreLbl.setFont(new Font("Arial", 40));
        return scoreLbl;
    }

    private Node getPauseBtn() {
        Ellipse pauseBtn = new Ellipse();
        Image img = new Image("/sprites/button_pause.png");
        ImagePattern ip = new ImagePattern(img);

        pauseBtn.setFill(ip);
        pauseBtn.setRadiusX((img.getWidth() / 2)+ 4);
        pauseBtn.setRadiusY((img.getHeight() / 2)+ 4);
        pauseBtn.setCenterX(WIDTH - 50);
        pauseBtn.setCenterY(50);

        return pauseBtn;
    }

    private void addInitialComponents() {
        for(int i=0;i<3;i++){
            double x = WIDTH / 2;
            double y = 400 - i * OBSTACLE_SPACING;
//            addComponent(new MediumRingObstacle(x, y));
            addComponent(ObstacleSelector.getRandomObstacle(x,y));
            addComponent(new Star(x, y));
            addComponent(new ColorPalette(WIDTH/2, y - OBSTACLE_SPACING / 2));
        }
//        addComponent(new WindmillObstacle(100, 100));
    }

    private Timeline moveBall() {
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
        return ballTime;
    }

//    private void destroy(Scene sc) {
//        sc = null;
//    }

    private double lastPalette;
    public void saveBall() {
        ball.setPosition(ball.getPosition().getX(), lastPalette);
    }

    public Group getRoot(AnchorPane background, AnchorPane elements) {
        Group root = new Group();

        background.setPrefSize(WIDTH, HEIGHT);
        background.setStyle("-fx-background-color: black;");

        root.getChildren().add(background);
        root.getChildren().add(elements);
        return root;
    }

    public void setScene() {
        setStageValues(App.getStage());

        background = new AnchorPane();
        elements = new AnchorPane();

        sc = new Scene((Parent) getRoot(background, elements));
        App.getStage().setScene(sc);

        scoreLbl = getScoreLabel();
        pauseBtn = getPauseBtn();

        background.getChildren().addAll(pauseBtn, scoreLbl);

    }

    public void startSavedGame() {
        elements.getChildren().add(ball.getNode());
        for(GameObject o : component){
            if(o.getNode()!=null)
                elements.getChildren().add(o.getNode());
        }
        sc.setOnKeyPressed(event -> ball.jump());
        pauseBtn.setOnMouseClicked(mouseEvent -> pause());
        ballTime = moveBall();

        timer = new AnimationTimer() {
            double Min = HEIGHT/2;
            int cnt = 0;

            @Override
            public void handle(long now) {
                if(getState() == State.EXIT) {
                    ballTime.stop();
//                    destroy(sc);
                    this.stop();
                }

                if(Min > ball.getPosition().getY()) {
                    Min = ball.getPosition().getY();
                    elements.setTranslateY(-Min + HEIGHT / 2.0);
                }

                handlePopupVisibility();

                for(int i=0;i<obst_list.size();i++){
                    Obstacle o = obst_list.get(i);
                    if(o.check(ball)){
//                        System.out.println("collide"+cnt++);
                        if (!isReviveUsed && score > 2) {
                            pause();
                            continueGameController.show();
                        } else if (!continueGameController.visibility()){
                            ball.setVelocityY(0);
                            exit();
                        }
                    }
                    if(o.getPosition().getY() > -elements.getTranslateY() + HEIGHT) {
                        elements.getChildren().remove(o.getNode());
                        obst_list.remove(i);
                        i--;
                    }
                }

                for(int i=0;i<star_list.size();i++){
                    Star o = star_list.get(i);
                    if(!o.done && o.check(ball)){
                        o.remove();
                        elements.getChildren().remove(o.getNode());
                        increaseScore();
                        scoreLbl.setText(getScore().toString());
//                        System.out.println("SCORE: " + score);

                    }
                    if(o.getPosition().getY() > -elements.getTranslateY() + HEIGHT) {
                        elements.getChildren().remove(o.getNode());
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
                        elements.getChildren().remove(o.getNode());
                    }
                    if(o.getPosition().getY() > -elements.getTranslateY() + HEIGHT) {
                        elements.getChildren().remove(o.getNode());
                        palette_list.remove(i);
                        i--;
                    }
                }

                if(obst_list.get(obst_list.size()-1).getPosition().getY() - OBSTACLE_SPACING + 100 > Min - HEIGHT/2) {
//                    component.add(new MediumRingObstacle(WIDTH/2, component.get(component.size()-1).getPosition().getY()-OBSTACLE_SPACING));
                    double x = WIDTH / 2;
                    double y = obst_list.get(obst_list.size()-1).getPosition().getY()-OBSTACLE_SPACING;
//                    addComponent(new MediumRingObstacle(x, y));
                    addComponent(ObstacleSelector.getRandomObstacle(x,y));
                    elements.getChildren().add(component.get(component.size()-1).getNode());
                    addComponent(new ColorPalette(x, y-OBSTACLE_SPACING/2));
                    elements.getChildren().add(component.get(component.size()-1).getNode());
                    addComponent(new Star(WIDTH/2, y));
                    elements.getChildren().add(component.get(component.size()-1).getNode());
//                    ((MediumRingObstacle)component.get(component.size()-1)).bindToBall(ball);
                }
//                System.out.println(obst_list.size() + palette_list.size());
            }
        };
        timer.start();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        /* All this code is just for testing right now*/
        play();
        setScene();

        addInitialComponents();
        elements.getChildren().add((Node) ball.getNode());
        for(GameObject o : component){
            if(o.getNode()!=null)
                elements.getChildren().add(o.getNode());
//            ((MediumRingObstacle)o).bindToBall(ball);
        }

        // Click events mentioned here
        sc.setOnKeyPressed(event -> ball.jump());
        pauseBtn.setOnMouseClicked(mouseEvent -> pause());
        ballTime = moveBall();

        timer = new AnimationTimer() {
            double Min = HEIGHT/2;
            int cnt = 0;

            @Override
            public void handle(long now) {
                if(getState() == State.EXIT) {
                    ballTime.stop();
//                    destroy(sc);
                    this.stop();
                }

                if(Min > ball.getPosition().getY()) {
                    Min = ball.getPosition().getY();
                    elements.setTranslateY(-Min + HEIGHT / 2.0);
                }

                handlePopupVisibility();

                for(int i=0;i<obst_list.size();i++){
                    Obstacle o = obst_list.get(i);
                    if(o.check(ball)){
                        System.out.println("collide"+cnt++);
                        if (!isReviveUsed && score > 2) {
                            continueGameController.show();
                        } else if (!continueGameController.visibility()){
                            ball.setVelocityY(0);
                            exit();
                        }
                    }
                    if(o.getPosition().getY() > -elements.getTranslateY() + HEIGHT) {
                        elements.getChildren().remove(o.getNode());
                        obst_list.remove(i);
                        i--;
                    }
                }

                for(int i=0;i<star_list.size();i++){
                    Star o = star_list.get(i);
                    if(!o.done && o.check(ball)){
                        o.remove();
                        elements.getChildren().remove(o.getNode());
                        increaseScore();
                        scoreLbl.setText(getScore().toString());
//                        System.out.println("SCORE: " + score);

                    }
                    if(o.getPosition().getY() > -elements.getTranslateY() + HEIGHT) {
                        elements.getChildren().remove(o.getNode());
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
                        elements.getChildren().remove(o.getNode());
                    }
                    if(o.getPosition().getY() > -elements.getTranslateY() + HEIGHT) {
                        elements.getChildren().remove(o.getNode());
                        palette_list.remove(i);
                        i--;
                    }
                }

                if(obst_list.get(obst_list.size()-1).getPosition().getY() - OBSTACLE_SPACING + 100 > Min - HEIGHT/2) {
//                    component.add(new MediumRingObstacle(WIDTH/2, component.get(component.size()-1).getPosition().getY()-OBSTACLE_SPACING));
                    double x = WIDTH / 2;
                    double y = obst_list.get(obst_list.size()-1).getPosition().getY()-OBSTACLE_SPACING;
//                    addComponent(new MediumRingObstacle(x, y));
                    addComponent(ObstacleSelector.getRandomObstacle(x,y));
                    elements.getChildren().add(component.get(component.size()-1).getNode());
                    addComponent(new ColorPalette(x, y-OBSTACLE_SPACING/2));
                    elements.getChildren().add(component.get(component.size()-1).getNode());
                    addComponent(new Star(WIDTH/2, y));
                    elements.getChildren().add(component.get(component.size()-1).getNode());
//                    ((MediumRingObstacle)component.get(component.size()-1)).bindToBall(ball);
                }

            }
        };
        timer.start();

        primaryStage.show();
    }
}
