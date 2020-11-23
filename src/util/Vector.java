package util;

public class Vector {
    private int x, y;

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public void add(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "Vector [" + getX() + ", " + getY() + "]";
    }

}
