package game;

import game.objects.Ball;
import javafx.scene.Node;
import util.Movable;

public class Obstacle extends GameObject implements Movable {
    private double speed;
    private int noOfColors;

    public Obstacle(double centerX, double centerY, double speed, int noOfColors) {
        super(centerX, centerY, true);
        this.speed = speed;
        this.noOfColors = noOfColors;
    }

    public double getSpeed() {
        return speed;
    }

    public int getNoOfColors() {
        return noOfColors;
    }

    @Override
    public void display() {

    }

    @Override
    public Node getNode() {
        return null; // for now it is returning null
    }

    @Override
    public void move() {
        
    }

    public boolean check(Ball ball) {
        return false;
    }
}
