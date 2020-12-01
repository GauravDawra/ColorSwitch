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

public class MediumRingObstacle extends Obstacle {

    public static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static double RADIUS = (Double) bundle.getObject("MediumRingObstacle_radius");

    private AnchorPane ring;

    public MediumRingObstacle(double centerX, double centerY) {
        super(centerX, centerY, 0, 4);
        try {
            ring = FXMLLoader.load(getClass().getResource("/resources/views/ringObstacle/mediumRingObstacle.fxml"));
            ring.setLayoutY(centerY - RADIUS - 7.5);
            ring.setLayoutX(centerX - RADIUS - 7.5);
        } catch(Exception e) {}
    }

    @Override
    public Node getNode() {
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
