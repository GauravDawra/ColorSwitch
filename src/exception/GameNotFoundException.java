package exception;

public class GameNotFoundException extends Exception {
    GameNotFoundException(String message) {
        super(message);
    }
}