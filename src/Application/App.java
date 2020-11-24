package Application;

import game.Obstacle;
import game.Game;

import java.io.Serializable;
import java.util.ArrayList;

public class App implements Serializable {
    private int numOfStars;
    private int bestScore;
    private ArrayList<Game> pastGames;
    private ArrayList<Obstacle> OBSTACLE_LIST;
    private Game currentGame;

    App() {
        pastGames = new ArrayList<Game>();
        OBSTACLE_LIST = new ArrayList<Obstacle>();
        currentGame = new Game();
        numOfStars = 0;
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
}