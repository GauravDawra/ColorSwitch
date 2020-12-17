package game.obstacles;

import game.Obstacle;
import game.objects.Ball;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.util.ResourceBundle;

public class LargeRingObstacle extends Obstacle {

    public static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static double RADIUS = (Double) bundle.getObject("LargeRingObstacle_radius");

    transient private AnchorPane ring = null;

    public LargeRingObstacle(double centerX, double centerY) {
        super(centerX, centerY, 0, 4);
    }

    @Override
    public void createObstacle() {
        try {
            ring = FXMLLoader.load(getClass().getResource("/resources/views/ringObstacle/largeRingObstacle.fxml"));
            ring.setLayoutY(getPosition().getY());
            ring.setLayoutX(getPosition().getX());
        } catch(Exception e) {}
    }

    @Override
    public Node getNode() {
        if (ring == null) createObstacle();
        return ring;
    }


    public boolean check(Ball ball) {
        for(Node n : ring.getChildren()) {
            if (((Path) Shape.intersect((Circle) ball.getNode(), (Shape)n)).getElements().size() > 0) {
                return true;
            }
        }
        return false;
    }
}
