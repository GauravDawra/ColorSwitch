package util;

public enum Color {
    GREEN(0, 255, 0),
    BLUE(0, 255, 255),
    PINK(255,0,148),
    YELLOW(255, 255, 0);

    private javafx.scene.paint.Color col;
    Color(int r, int g, int b) {
        this.col = javafx.scene.paint.Color.rgb(r, g, b);
    }

    public javafx.scene.paint.Color getColor() {
        return this.col;
    }

}
