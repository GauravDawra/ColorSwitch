package game;

import javafx.scene.Node;

public class Obstacle extends GameObject {
    private double speed;
    private int noOfColors;

    Obstacle(double speed, int noOfColors) {
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
}
