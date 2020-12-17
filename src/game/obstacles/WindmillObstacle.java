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

    transient private AnchorPane windmill;

    public WindmillObstacle(double centerX, double centerY) {
        super(centerX, centerY, 0, 4);
//        if(loader == null)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/windmillObstacle/windmillObstacle.fxml"));
        try {
            WindmillController controller = new WindmillController(2500 + new Random().nextInt(1000));
            loader.setController(controller);
            windmill = loader.load();
            windmill.setLayoutX(centerX - RADIUS - 100); // offset for windmill
            windmill.setLayoutY(centerY - RADIUS);
        } catch(Exception e) {
            System.out.println("Windmill is null");
        }
    }

    @Override
    public Node getNode() {
        return windmill;
    }

    @Override
    public boolean check(Ball ball) {
        Shape intersect;
        for(Node n : windmill.getChildren()) {
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
