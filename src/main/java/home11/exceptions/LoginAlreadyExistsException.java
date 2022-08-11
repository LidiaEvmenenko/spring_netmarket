package home11.exceptions;

public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
