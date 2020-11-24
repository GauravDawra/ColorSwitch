package game;
import util.Vector;

public abstract class GameObject {
    private Vector position;
    private boolean visibility;

    public GameObject() {
        visibility = true;
        position = new Vector();
    }

    public GameObject(Vector pos, boolean vis) {
        this.position = pos;
        this.visibility = vis;
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

//    public void display() {
//        visibility = true;
//    }
    public abstract void display();
}
