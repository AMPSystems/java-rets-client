package us.ampre.rets.client;

public class InvalidHttpStatusException extends RetsException {
    public InvalidHttpStatusException(int statusCode, String reasonPhrase) {
        super("Status code (" + statusCode + ") " + reasonPhrase);
    }
}