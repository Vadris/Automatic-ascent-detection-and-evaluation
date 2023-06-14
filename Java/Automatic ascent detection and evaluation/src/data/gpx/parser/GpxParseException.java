package data.gpx.parser;

/**
 * Exception class for handling errors that occur during GPX parsing.
 * 
 * <p>The GpxParseException class is a custom exception class that extends Throwable.
 * It is used to handle exceptions that occur during GPX parsing and provides a custom error message.</p>
 * 
 * <p>Version: 1.0</p>
 * <p>Author: [Your Name]</p>
 */
public class GpxParseException extends Throwable {
    
    /**
     * Constructs a new GpxParseException with the specified error message.
     * 
     * @param message The error message.
     */
    public GpxParseException(String message) {
        super(message);
    }
}
