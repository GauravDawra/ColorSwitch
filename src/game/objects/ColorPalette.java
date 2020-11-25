package game.objects;

import java.util.Random;
import java.util.ResourceBundle;

import game.GameObject;
import javafx.scene.Node;
import util.Color;
import util.Removable;

public class ColorPalette extends GameObject implements Removable {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static final double RADIUS = (Double) bundle.getObject("ColorPallete_radius");
    private Random random;
    ColorPalette(double centerX, double centerY) {
        super(centerX, centerY, true);
        random = new Random();
    }

    public Color getRandomColor() {
        int index = random.nextInt(Color.values().length);
        return Color.values()[index];
    }

    public void hide() {
        // code elided
        this.setVisibility(false);
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