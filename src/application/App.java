package application;

import game.Obstacle;
import game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sceneLoader.SceneLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class App extends Application implements Serializable {
    private static App colorSwitch = null;
    private static Stage stage = null;
//    private static final long serialVersionUID = 1L;

    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");

    public static final int HEIGHT = (Integer) bundle.getObject("SCREEN_HEIGHT");
    public static final int WIDTH = (Integer) bundle.getObject("SCREEN_WIDTH");

    private int numOfStars;
    private int bestScore;
    private ArrayList<Game> pastGames;
    private ArrayList<Obstacle> OBSTACLE_LIST;
    transient private Game currentGame;

    public App() {
        pastGames = new ArrayList<Game>();
        OBSTACLE_LIST = new ArrayList<Obstacle>();
        currentGame = new Game();
        numOfStars = 0;
    }

    public void startApp(String []args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Color Switch");
//        stage.setHeight(HEIGHT);
//        stage.setWidth(WIDTH);
        stage.setResizable(false);

        Scene sc = SceneLoader.getLoader().getMainPage();
        stage.setScene(sc);
        stage.show();
    }

    public static App getInstance() {
        if (colorSwitch == null) {
            colorSwitch = new App();
        }
        return colorSwitch;
    }

    public static Stage getStage() {
        return stage;
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    public int getBestScore() {
        return bestScore;
    }

    public ArrayList<Game> getPastGames() {
        return pastGames;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setNumOfStars(int numOfStars) {
        this.numOfStars = numOfStars;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void loadGame() {
        //no game exception
        try {
            currentGame.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game createNewGame() {
        Game newGame = new Game();
        currentGame = newGame;
        return newGame;
    }

    public void loadExistingGame(Game g) {
        currentGame = g;
    }

    public void saveCurrentGame() {
        pastGames.add(currentGame);
    }

    public void reviveGame() {

    }

    public void saveState() {

    }

    public void retrieveState() {

    }

    public void serialise() throws IOException {
        String filename = ".cswitch";
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
        } finally {
            out.close();
        }
    }

    public void deserialize() throws IOException{
        String filename = ".cswitch";
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            colorSwitch = (App) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    public static void main(String[] args) {
        colorSwitch = getInstance();
        try {
            colorSwitch.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("nope");
        }

        colorSwitch.startApp(args);
        try {
            colorSwitch.serialise();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}