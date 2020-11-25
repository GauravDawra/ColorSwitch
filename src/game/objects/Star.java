package game.objects;

import game.GameObject;
import javafx.scene.Node;
import util.Removable;

import java.util.ResourceBundle;

public class Star extends GameObject implements Removable {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");

    public static final int VALUE = (Integer) bundle.getObject("Star_value");

    public Star(int centerX, int centerY) {
        super(centerX, centerY, true);
    }

    public void split() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void display() {

    }

    @Override
    public Node getNode() {
        return null;
    }
}