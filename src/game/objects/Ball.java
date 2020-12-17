package game.objects;

import game.GameObject;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.shape.Circle;
import util.Color;
import util.Movable;
import util.Removable;
import util.Vector;

import java.io.Serializable;
import java.util.ResourceBundle;

public class Ball extends GameObject implements Movable, Removable {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");

    public static final double RADIUS = (Double) bundle.getObject("Ball_radius"); // in pixels // just for now
    public static final double UP_VELOCITY = (Double) bundle.getObject("Jump_velocity"); // change later

    private Color color;
    private Vector velocity;
    transient private Circle circle = null;

    public Ball(double centerX, double centerY) {
        super(centerX, centerY, true);

        this.color = Color.GREEN; // for now it's green, we can change it to random
        this.velocity = new Vector();
    }


    public Color getColor() {
        return this.color;
    }

    @Override
    public void setPosition(double centerX, double centerY) {
        super.setPosition(centerX, centerY);
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setColor(Color color) {
        this.color = color;
        this.circle.setFill(color.getColor());
    }

    public void setVelocityY(double y) {
        this.velocity.setY(y);
    }

    public void jump() {
        this.setVelocityY(-Ball.UP_VELOCITY);
    }

    public void breakBall() {
        // to be decided yet
        setVisibility(false);
    }

    @Override
    public void display() { // deal with it later

    }

    @Override
    public void createObstacle() {
        this.circle = new Circle(getPosition().getX(), getPosition().getY(), Ball.RADIUS);
        this.circle.setFill(color.getColor());
        circle.setCenterY(getPosition().getY());
        circle.setCenterX(getPosition().getX());
    }

    @Override
    public Node getNode() {
        if (circle == null) createObstacle();
        return this.circle;
    }

    @Override
    public void remove() {
        breakBall();
    }


    @Override
    public void move() {

    }
}
