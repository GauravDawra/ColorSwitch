package game.objects;

import game.GameObject;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import util.Removable;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Star extends GameObject implements Removable {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static final int VALUE = (Integer) bundle.getObject("Star_value");

    transient private Ellipse star = null;

    public Star(double centerX, double centerY) {
        super(centerX, centerY, true);
    }

    public void split() {

    }

    @Override
    public void remove() {
        hide();
    }

    private void hide() {
        this.setVisibility(false);
        star.setVisible(false);
    }

    @Override
    public void display() {

    }

    @Override
    public void createObstacle() {
        Image img = new Image("/sprites/star.png");
        ImagePattern ip = new ImagePattern(img);

        star = new Ellipse();
        star.setFill(ip);
        star.setRadiusX((img.getWidth() / 2)+ 4);
        star.setRadiusY((img.getHeight() / 2)+ 4);
        star.setCenterX(getPosition().getX());
        star.setCenterY(getPosition().getY());
    }

    @Override
    public Node getNode() {
        if (star == null) createObstacle();
        return star;
    }

    public boolean done = false;
    public boolean check(Ball ball) {
        if (((Path) Shape.intersect((Circle) ball.getNode(), ((Ellipse)getNode()))).getElements().size() > 0) {
            done = true;
            return true;
        }
        return false;
    }
}