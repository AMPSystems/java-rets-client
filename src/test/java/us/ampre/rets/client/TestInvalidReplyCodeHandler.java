package us.ampre.rets.client;

final class TestInvalidReplyCodeHandler implements InvalidReplyCodeHandler {
    private int replyCode;
    private String replyText;

    public void invalidRetsReplyCode(int code) throws InvalidReplyCodeException {
        throw new InvalidReplyCodeException(code);
    }

    public void invalidRetsStatusReplyCode(int code) {
        this.replyCode = code;
    }

    public int getReplyCode() {
        return this.replyCode;
    }

    public String getReplyText() {
        return this.replyText;
    }
}