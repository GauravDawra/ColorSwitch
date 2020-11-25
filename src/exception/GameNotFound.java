package exception;

public class GameNotFound extends Exception {
    GameNotFound(String message) {
        super(message);
    }
}