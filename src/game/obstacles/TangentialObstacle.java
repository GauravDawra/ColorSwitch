package game.obstacles;

import game.Obstacle;
import game.objects.Ball;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import resources.views.ringObstacle.RingController;
import resources.views.ringObstacle.TangentialController;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class TangentialObstacle extends Obstacle {

    public static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static double RADIUS = (Double) bundle.getObject("MediumRingObstacle_radius");

    transient private AnchorPane ring = null;

    public TangentialObstacle(double centerX, double centerY) {
        super(centerX, centerY, 0, 4);
    }

    @Override
    public void createObstacle() {
        try {
//            ring = FXMLLoader.load(getClass().getResource("/resources/views/ringObstacle/mediumRingObstacle2.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/tangentialObstacle/tangentialObstacle.fxml"));
            TangentialController controller = new TangentialController(5000 + new Random().nextInt(2000));
            loader.setController(controller);
            ring = loader.load();
            ring.setLayoutY(getPosition().getY() - RADIUS - 7.5);
            ring.setLayoutX(getPosition().getX() - 2*RADIUS - 15);
        } catch(Exception e) {}
    }

    @Override
    public Node getNode() {
        if (ring == null) createObstacle();
        return ring;
    }

    @Override
    public boolean check(Ball ball) {
        Shape intersect;
        for(Node n : ((AnchorPane)ring.getChildren().get(0)).getChildren()) {
//            intersect = Shape.intersect((Circle)ball.getNode(), (Shape)n);
//            if(intersect.getBoundsInLocal().getWidth() != -1) {
//                System.out.println(((Shape)n).getFill() + " " + ball.getColor().getColor());
//                return true;
//            }
            if (!((Shape)n).getFill().toString().equals(ball.getColor().getColor().toString()) && ((Path) Shape.intersect((Circle) ball.getNode(), (Shape)n)).getElements().size() > 0) {
                System.out.println(((Shape)n).getFill() + " " + ball.getColor().getColor());
                return true;
            }
        }
//        return check(ring.getChildren().get(1))
//        System.out.println(ring.getChildren().get(1).getClass());
        return false;
    }
}
