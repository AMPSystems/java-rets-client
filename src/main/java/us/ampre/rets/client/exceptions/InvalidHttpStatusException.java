package us.ampre.rets.client.exceptions;

public class InvalidHttpStatusException extends RetsException {
    private final int statusCode;

    public InvalidHttpStatusException(int statusCode, String reasonPhrase) {
        super("Status code (" + statusCode + ") " + reasonPhrase);
        this.statusCode = statusCode;
    }

    public int getStatus() {
        return this.statusCode;
    }
}