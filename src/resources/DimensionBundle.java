package resources;

import java.util.ListResourceBundle;

public class DimensionBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"SCREEN_HEIGHT", new Integer(800)},
            {"SCREEN_WIDTH", new Integer(500)},
            {"Ball_radius", new Double(10.0)},
            {"Jump_velocity", new Double(40.0)},
            {"Star_value", new Integer(1)},
            {"Gravity", new Double(5.0)},
            {"ColorPallete_radius", new Double(20.0)},
            {"MediumRingObstacle_radius", new Double(80.0)},
            {"LargeRingObstacle_radius", new Double(100.0)},
            {"Obstacle_spacing", new Double(350.0)},
            {"Windmill_radius", new Double(107.5)},
            {"Num_of_obstacles", new Integer(2)} // update manually when adding new obstacle
    };
}
