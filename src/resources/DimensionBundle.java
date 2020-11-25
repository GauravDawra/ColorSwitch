package resources;

import java.util.ListResourceBundle;

public class DimensionBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"Ball_radius", new Double(10.0)},
            {"Jump_velocity", new Double(20.0)}
    };
}
