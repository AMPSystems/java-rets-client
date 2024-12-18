package us.ampre.rets.client;

public class InvalidReplyCodeException extends RetsException {
    private final ReplyCode mReplyCode;
    private String mMsg;
    private String mReqinfo;

    public InvalidReplyCodeException(int replyCodeValue) {
        this.mReplyCode = ReplyCode.fromValue(replyCodeValue);
    }

    public InvalidReplyCodeException(ReplyCode replyCode) {
        this.mReplyCode = replyCode;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder(this.mReplyCode.toString());
        if (this.mMsg != null) {
            sb.append(System.lineSeparator()).append(this.mMsg);
        }
        if (this.mReqinfo != null) {
            sb.append(System.lineSeparator()).append(this.mReqinfo);
        }
        return sb.toString();
    }

    public int getReplyCodeValue() {
        return this.mReplyCode.getValue();
    }

    public void setRemoteMessage(String msg) {
        this.mMsg = msg;
    }

    public void setRequestInfo(String reqinfo) {
        this.mReqinfo = reqinfo;
    }

}
