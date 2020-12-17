package resources.views.windmillObstacle;

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

public class WindmillController implements Initializable {
    @FXML
    public AnchorPane windmill;

    public double timeTaken = 2000;

    public WindmillController(double t) {
        this.timeTaken = t;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RotateTransition rotate = new RotateTransition(Duration.millis(timeTaken), windmill);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();
    }

}