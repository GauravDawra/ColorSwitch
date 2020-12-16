package sceneLoader;

import application.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneLoader {
    private static SceneLoader loader = null;

    private Scene gameOverPage;
    private Scene gamePage;
    private Scene mainPage;
    private Scene pausePage;
    private Scene savedGamesPage;

    private SceneLoader() throws IOException {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("/mainPage/layout.fxml"));
        mainPage = new Scene(mainPageParent);

        Parent pausePageParent = FXMLLoader.load(getClass().getResource("/pausePage/layout.fxml"));
        pausePage = new Scene(pausePageParent);
    }

    public static SceneLoader getLoader() throws IOException{
        if (loader == null) {
            loader = new SceneLoader();
        }
        return loader;
    }

    public Scene getMainPage() {
        return mainPage;
    }

    public Scene getPausePage() {
        return pausePage;
    }
}
