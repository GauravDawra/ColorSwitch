package sceneLoader;

import application.App;
import gameOverPage.gameOverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pausePage.pausePageController;
import savedGamesPage.SavedGamesPage;

import java.io.IOException;

public class SceneLoader {
    private static SceneLoader loader = null;

    private Scene continueGame;
    private Scene gameOverPage;
    private Scene gamePage;
    private Scene mainPage;
    private Scene pausePage;
    private Scene savedGamesPage;


//    private FXMLLoader continueLoader, gameOverLoader, gamePageLoader, mainPageLoader, pausePageLoader, savedGamesLoader;

    private pausePageController pauseController;
    private gameOverController overController;

    private SceneLoader() throws IOException {
        FXMLLoader loader = new FXMLLoader();


        Parent continueGameParent = FXMLLoader.load(getClass().getResource("/continueGame/layout.fxml"));
        continueGame = new Scene(continueGameParent);

        FXMLLoader loaderOver = new FXMLLoader(getClass().getResource("/gameOverPage/layout.fxml"));
        Parent gameOverPageParent = loaderOver.load();
        gameOverPage = new Scene(gameOverPageParent);
        overController = loaderOver.getController();

        Parent mainPageParent = FXMLLoader.load(getClass().getResource("/mainPage/layout.fxml"));
        mainPage = new Scene(mainPageParent);


        FXMLLoader loaderPause = new FXMLLoader(getClass().getResource("/pausePage/layout.fxml"));
        Parent pausePageParent = loaderPause.load();
        pausePage = new Scene(pausePageParent);
        pauseController = loaderPause.getController();
//        System.out.println("XXXX"+( instanceof pausePageController)+"XXXX");

//        savedGamesPage = new SavedGamesPage().getScene();

    }

    public static SceneLoader getLoader() throws IOException{
        if (loader == null) {
            loader = new SceneLoader();
        }
        return loader;
    }

    public Scene getContinueGame() {
        return continueGame;
    }

    public Scene getGameOverPage() {
        return gameOverPage;
    }

    public Scene getMainPage() {
        return mainPage;
    }

    public Scene getPausePage(int score) {
//        System.out.println("YOOOOOOOOOOO");
        pauseController.setScore(score);
        return pausePage;
    }

    public Scene getSavedGamesPage() {
        return SavedGamesPage.getInstance().getScene();
    }

    public void setScore(int score) {
        pauseController.setScore(score);
        overController.setScore(score);
        overController.setBestScore(App.getInstance().getBestScore());
    }
}
