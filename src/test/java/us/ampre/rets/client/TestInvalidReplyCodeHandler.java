package us.ampre.rets.client;

final class TestInvalidReplyCodeHandler implements InvalidReplyCodeHandler {
    private int replyCode;
    private String replyText;

    public void invalidRetsReplyCode(int code, String text) throws InvalidReplyCodeException {
        throw new InvalidReplyCodeException(code, text);
    }

    public void invalidRetsStatusReplyCode(int code, String text) {
        this.replyCode = code;
        this.replyText = text;
    }

    public int getReplyCode() {
        return this.replyCode;
    }

    public String getReplyText() {
        return this.replyText;
    }
}