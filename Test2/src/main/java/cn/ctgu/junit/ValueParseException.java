package cn.ctgu.junit;

public class ValueParseException extends RuntimeException {
    /**
     * 
    */
    private static final long serialVersionUID = 1L;

    public ValueParseException() {
    }

    public ValueParseException(String message) {
        super(message);
    }

    public ValueParseException(Throwable cause) {
        super(cause);
    }

    public ValueParseException(String message, Throwable cause) {
        super(message, cause);
    }
}