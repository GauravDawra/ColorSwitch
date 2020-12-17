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

import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class MediumRingObstacle extends Obstacle {

    public static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static double RADIUS = (Double) bundle.getObject("MediumRingObstacle_radius");

    transient private AnchorPane ring;

    public MediumRingObstacle(double centerX, double centerY) {
        super(centerX, centerY, 0, 4);
        try {
//            ring = FXMLLoader.load(getClass().getResource("/resources/views/ringObstacle/mediumRingObstacle2.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/ringObstacle/mediumRingObstacle2.fxml"));
            RingController controller = new RingController(1500 + new Random().nextInt(1000));
            loader.setController(controller);
            ring = loader.load();
            ring.setLayoutY(centerY - RADIUS - 7.5);
            ring.setLayoutX(centerX - RADIUS - 7.5);
        } catch(Exception e) {}
    }

    @Override
    public Node getNode() {
        return ring;
    }
    private BooleanBinding boo;

    public void bindToBall(Ball ball) {
        boo = Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
//                System.out.println("Rotating");
                for(Node n : ring.getChildren()) {
                    if(((Path) Shape.intersect((Circle) ball.getNode(), (Shape)n)).getElements().size() > 0) {
//                        System.out.println("YOYOYOYO" + ((Shape) n).getFill());

                        return true;
                    }
                }
                return false;
//                return check(ball);
            }
        }, ((Circle)ball.getNode()).centerYProperty());
        boo.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(newValue) System.out.println("takkar");
            }
        });
    }

    @Override
    public boolean check(Ball ball) {
        Shape intersect;
        for(Node n : ring.getChildren()) {
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
