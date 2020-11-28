package game.objects;

import java.util.Random;
import java.util.ResourceBundle;

import game.GameObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import util.Color;
import util.Removable;

public class ColorPalette extends GameObject implements Removable {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    public static final double RADIUS = (Double) bundle.getObject("ColorPallete_radius");

    private Random random;
    private AnchorPane pallete;
    public ColorPalette(double centerX, double centerY) {
        super(centerX, centerY, true);
        random = new Random();
        try {
            pallete = FXMLLoader.load(getClass().getResource("/resources/views/colorPallete/colorPallete.fxml"));
            pallete.setLayoutX(centerX - RADIUS);

            pallete.setLayoutY(centerY - RADIUS);
        } catch(Exception e) {}
    }

    public Color getRandomColor(Color c) {
        Color cur = c;
        while(cur == c) {
            int index = random.nextInt(Color.values().length);
            cur = Color.values()[index];
        }
        return cur;
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
        return pallete;
    }

    public boolean check(Ball ball) {
        for(Node n : pallete.getChildren()) {
                if (((Path) Shape.intersect((Circle) ball.getNode(), (Shape)n )).getElements().size() > 0) {
                return true;
            }
        }
        return false;
    }
}