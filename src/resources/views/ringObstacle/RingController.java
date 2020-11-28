package RingObstacle;

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

public class RingController implements Initializable {
    @FXML
    public AnchorPane innerCircle2;
    @FXML
    public AnchorPane outerCircle;
    @FXML
    public AnchorPane outerMostCircle;
    @FXML
    public ImageView playButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("hello");
        RotateTransition rotate = new RotateTransition(Duration.millis(2000), innerCircle2);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();


        RotateTransition rotateOuter = new RotateTransition(Duration.millis(3500), outerCircle);
        rotateOuter.setByAngle(-360);
        rotateOuter.setCycleCount(Animation.INDEFINITE);
        rotateOuter.setInterpolator(Interpolator.LINEAR);
        rotateOuter.play();

        RotateTransition rotateOuterMost = new RotateTransition(Duration.millis(2800), outerMostCircle);
        rotateOuterMost.setByAngle(360);
        rotateOuterMost.setCycleCount(Animation.INDEFINITE);
        rotateOuterMost.setInterpolator(Interpolator.LINEAR);
        rotateOuterMost.play();

        scaleAnimation(playButton, 0.25f);

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

    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("hello");
    }

    public void hello(KeyEvent keyEvent) {
        System.out.println("hello");
        System.out.println(keyEvent.getSource().toString());
    }

    public void playPressed(MouseEvent mouseEvent) {
        System.out.println("play pressed");
    }

}