package game;

import game.obstacles.MediumRingObstacle;
import game.obstacles.TangentialObstacle;
import game.obstacles.WindmillObstacle;

import java.util.Random;
import java.util.ResourceBundle;

public class ObstacleSelector {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.DimensionBundle");
    private static int NUM_OF_OBSTACLES = (Integer) bundle.getObject("Num_of_obstacles");
    private static Random random = new Random();

    public static Obstacle getRandomObstacle(double centerX, double centerY){
        int num = random.nextInt(NUM_OF_OBSTACLES);
        switch (num) {
            case 0:
                return new MediumRingObstacle(centerX, centerY);
            case 1:
                return new WindmillObstacle(centerX, centerY);
            case 2:
                return new TangentialObstacle(centerX, centerY);
        }
        return new MediumRingObstacle(centerX, centerY);
    }

}
