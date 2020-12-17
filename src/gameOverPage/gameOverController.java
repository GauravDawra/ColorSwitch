package gameOverPage;

import application.App;
import game.Game;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class gameOverController implements Initializable {

    @FXML
    public ImageView restartButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scaleAnimation(restartButton, 0.12f);

    }

    private void scaleAnimation(ImageView image, double val) {
        ScaleTransition scalePlay = new ScaleTransition(Duration.millis(750), image);
        scalePlay.setByX(val);
        scalePlay.setByY(val);
        scalePlay.setCycleCount(Animation.INDEFINITE);
        scalePlay.setInterpolator(Interpolator.EASE_BOTH);
        scalePlay.setAutoReverse(true);
        scalePlay.play();
    }

    public void restartPressed(MouseEvent mouseEvent){
        App.getInstance().createNewGame();
        App.getInstance().loadGame();
    }

    public void backPressed(MouseEvent mouseEvent){
        try {
            App.getStage().setHeight(703);
            App.getStage().setWidth(665);
            App.getInstance().start(App.getStage());
        } catch (Exception e) {}

    }
}

