package ua.se.sample.exception;

public class UserException extends AbstractGraphqlException {
    public UserException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}