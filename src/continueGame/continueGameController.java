package continueGame;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

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
        hide();
    }
}
