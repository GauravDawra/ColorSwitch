package game;

import java.util.Random;

import util.Color;
import util.Removable;

public class ColorPalette extends GameObject implements Removable {
    
    ColorPalette() {

    }

    public Color getRandomColor() {
        int index = new Random().nextInt(Color.values().length);
        return Color.values()[index];
    }

    public void hide() {

    }
    
    @Override
    public void remove() {
        
    }
    
    @Override
    public void display() {
        
    }
}