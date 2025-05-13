package ua.se.sample.exception;

public class LanguageException extends AbstractGraphqlException {
    public LanguageException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}