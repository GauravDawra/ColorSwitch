package gameOverPage;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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

}

