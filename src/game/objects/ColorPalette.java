package game.objects;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import game.GameObject;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private static Random random;
    transient private AnchorPane pallete = null;
    public ColorPalette(double centerX, double centerY) {
        super(centerX, centerY, true);
        if(random == null) random = new Random();
    }

    public static Color getRandomColor(Color c) {
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
        pallete.setVisible(false);
    }
    
    @Override
    public void remove() {
        hide();
    }
    
    @Override
    public void display() {
        
    }

    @Override
    public void createObstacle() {
        try {
            pallete = FXMLLoader.load(getClass().getResource("/resources/views/colorPallete/colorPallete.fxml"));
            pallete.setLayoutX(getPosition().getX() - RADIUS);

            pallete.setLayoutY(getPosition().getY() - RADIUS);
        } catch(Exception e) {}
    }

    @Override
    public Node getNode() {
        if (pallete == null) createObstacle();
        return pallete;
    }

    private BooleanBinding boo;
    public boolean done = false;

    public void bindToBall(Ball ball) {
        boo = Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
//                return null;
                if(check(ball) && !done) {
//                    System.out.println("Takkar");
                    ball.setColor(getRandomColor(ball.getColor()));
                    remove();
                    System.out.println("done");
                    boo = null;
                    done = true;
                    return true;
                }
                else return false;
            }
        }, ((Circle)ball.getNode()).centerYProperty());
        boo.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(newValue) System.out.println("takkar");

            }
        });
    }

    public boolean check(Ball ball) {
        for(Node n : ((AnchorPane)getNode()).getChildren()) {
            if (((Path) Shape.intersect((Circle) ball.getNode(), (Shape)n)).getElements().size() > 0) {
                done = true;
                return true;
            }
        }
        return false;
    }
}