package continueGame;

import application.App;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import sceneLoader.SceneLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class continueGameController implements Initializable {

    private static boolean isVisible = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static boolean visibility() {
        return isVisible;
    }

    public static void hide() {
        isVisible = false;
    }

    public static void show() {
        isVisible = true;
    }

    public void playPressed(MouseEvent mouseEvent){
        App.getInstance().getCurrentGame().setScore(App.getInstance().getCurrentGame().getScore() - 3);
        App.getInstance().getCurrentGame().saveBall();
        hide();
    }


    public void exit(MouseEvent mouseEvent) {
        try {
            SceneLoader.getLoader().setScore(App.getInstance().getCurrentGame().getScore());
            App.getStage().setScene(SceneLoader.getLoader().getGameOverPage());
            App.getInstance().getCurrentGame().exit();
            hide();
        } catch(Exception e){}
    }
}
