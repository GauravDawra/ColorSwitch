package resources.views.ringObstacle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class TangentialController implements Initializable {
    @FXML
    public AnchorPane leftRing, rightRing;

    public double timeTaken = 2000;

    public TangentialController(double t) {
        this.timeTaken = t;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RotateTransition rotate1 = new RotateTransition(Duration.millis(timeTaken), leftRing);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(Animation.INDEFINITE);
        rotate1.setInterpolator(Interpolator.LINEAR);
        rotate1.play();

        RotateTransition rotate2 = new RotateTransition(Duration.millis(timeTaken), rightRing);
        rotate2.setByAngle(-360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.play();
    }

}