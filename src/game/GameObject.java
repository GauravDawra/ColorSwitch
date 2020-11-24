package game;
import util.Vector;

class GameObject {
    Vector position;
    boolean visibility;

    GameObject() {
        visibility = false;
        position = new Vector();
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public boolean isVisible() {
        return visibility;
    }

    public void display() {
        visibility = true;
    }
    
}
