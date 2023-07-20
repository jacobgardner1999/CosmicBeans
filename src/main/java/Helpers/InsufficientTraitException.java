package Helpers;

public class InsufficientTraitException extends RuntimeException{
    public InsufficientTraitException(String errorMessage) {
        super(errorMessage);
    }
}
