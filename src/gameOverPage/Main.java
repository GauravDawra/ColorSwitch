package gameOverPage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainPage = FXMLLoader.load(getClass().getResource("layout.fxml"));
        Scene sc = new Scene(mainPage);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}