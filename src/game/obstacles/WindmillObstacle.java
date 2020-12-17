package game.obstacles;

import game.Obstacle;
import game.objects.Ball;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import resources.views.windmillObstacle.WindmillController;

import java.util.Random;
import java.util.ResourceBundle;

public class WindmillObstacle extends Obstacle {

    public static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
//    private static FXMLLoader loader;
    private static double RADIUS = (Double)bundle.getObject("Windmill_radius");

    transient private AnchorPane windmill = null;

    public WindmillObstacle(double centerX, double centerY) {
        super(centerX, centerY, 0, 4);
    }

    @Override
    public void createObstacle() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/windmillObstacle/windmillObstacle.fxml"));
        try {
            WindmillController controller = new WindmillController(2500 + new Random().nextInt(2000));
            loader.setController(controller);
            windmill = loader.load();
            windmill.setLayoutX(getPosition().getX() - RADIUS - 100); // offset for windmill
            windmill.setLayoutY(getPosition().getY() - RADIUS);
        } catch(Exception e) {
            System.out.println("Windmill is null");
        }
    }

    @Override
    public Node getNode() {
        if (windmill == null) createObstacle();
        return windmill;
    }

    @Override
    public boolean check(Ball ball) {
        Shape intersect;
        for(Node n : ((AnchorPane)getNode()).getChildren()) {
//            intersect = Shape.intersect((Circle)ball.getNode(), (Shape)n);
//            if(intersect.getBoundsInLocal().getWidth() != -1) {
//                System.out.println(((Shape)n).getFill() + " " + ball.getColor().getColor());
//                return true;
//            }
            if (!((Shape)n).getFill().toString().equals(ball.getColor().getColor().toString()) && ((Path) Shape.intersect((Circle) ball.getNode(), (Shape)n)).getElements().size() > 0) {
//                System.out.println(((Shape)n).getFill() + " " + ball.getColor().getColor());
                return true;
            }
        }
        return false;
    }

}
