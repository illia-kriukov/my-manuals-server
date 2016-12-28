package se.lnu.agile.mymanuals.exception;

/**
 * Created by ilyakruikov on 11/21/16.
 */
public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductException(Throwable cause) {
        super(cause);
    }

}