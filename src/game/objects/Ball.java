package game.objects;

import game.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.shape.Circle;
import util.Color;
import util.Vector;

public class Ball extends GameObject {

    public static final double RADIUS = 10.0; // in pixels // just for now
    public static final double UP_VELOCITY = 20.0; // change later
    private Color color;
    private Vector velocity;
    private Circle circle;

    public Ball(double centerX, double centerY) {
        super(centerX, centerY, true);
        this.color = Color.GREEN; // for now it's green, we can change it to random
        this.velocity = new Vector();

        // define the circle here
        this.circle = new Circle(this.getPosition().getX(), this.getPosition().getY(), Ball.RADIUS);
        this.circle.setFill(color.getColor());
        this.circle.setBlendMode(BlendMode.ADD);
    }

    public Color getColor() {
        return this.color;
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
        this.setVelocityY(Ball.UP_VELOCITY);
    }

    @Override
    public void display() { // deal with it later

    }

    @Override
    public Node getNode() {
        return this.circle;
    }
}
