package resources;

import java.util.ListResourceBundle;

public class DimensionBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"SCREEN_HEIGHT", new Integer(900)},
            {"SCREEN_WIDTH", new Integer(500)},
            {"Ball_radius", new Double(10.0)},
            {"Jump_velocity", new Double(40.0)},
            {"Star_value", new Integer(1)},
            {"Gravity", new Double(5.0)},
            {"ColorPallete_radius", new Double(20.0)},
            {"MediumRingObstacle_radius", new Double(80.0)}
    };
}
