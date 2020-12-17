package sceneLoader;

import application.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pausePage.pausePageController;

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

    private SceneLoader() throws IOException {
        FXMLLoader loader = new FXMLLoader();


        Parent continueGameParent = FXMLLoader.load(getClass().getResource("/continueGame/layout.fxml"));
        continueGame = new Scene(continueGameParent);

        Parent gameOverPageParent = FXMLLoader.load(getClass().getResource("/gameOverPage/layout.fxml"));
        gameOverPage = new Scene(gameOverPageParent);

        Parent mainPageParent = FXMLLoader.load(getClass().getResource("/mainPage/layout.fxml"));
        mainPage = new Scene(mainPageParent);


        FXMLLoader loaderPause = new FXMLLoader(getClass().getResource("/pausePage/layout.fxml"));
        Parent pausePageParent = loaderPause.load();
        pausePage = new Scene(pausePageParent);
        pauseController = loaderPause.getController();
//        System.out.println("XXXX"+( instanceof pausePageController)+"XXXX");
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
    public void setScore(int score) {
        pauseController.setScore(score);
    }
}
