package util;

public enum Color {
    GREEN(0, 255, 0),
    BLUE(0, 255, 255),
    PINK(255,0,148),
    YELLOW(255, 255, 0);

    private java.awt.Color col;
    Color(int r, int g, int b) {
        this.col = new java.awt.Color(r, g, b);
    }

    public java.awt.Color getColor() {
        return this.col;
    }

}
