package us.ampre.rets.client;

public interface InvalidReplyCodeHandler {
    InvalidReplyCodeHandler FAIL = new InvalidReplyCodeHandler() {
        public void invalidRetsReplyCode(int replyCode, String replyText) throws InvalidReplyCodeException {
            throw new InvalidReplyCodeException(replyCode, replyText);
        }

        public void invalidRetsStatusReplyCode(int replyCode, String replyText) throws InvalidReplyCodeException {
            throw new InvalidReplyCodeException(replyCode, replyText);
        }
    };

    void invalidRetsReplyCode(int replyCode, String replyText) throws InvalidReplyCodeException;

    void invalidRetsStatusReplyCode(int replyCode, String replyText) throws InvalidReplyCodeException;
}
