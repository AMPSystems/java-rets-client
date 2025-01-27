package us.ampre.rets.client;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginResponse extends KeyValueResponse {
    private static final String BROKER_KEY = "Broker";
    private static final String MEMBER_NAME_KEY = "MemberName";
    private static final String METADATA_VER_KEY = "MetadataVersion";
    private static final String MIN_METADATA_VER_KEY = "MinMetadataVersion";
    private static final String USER_INFO_KEY = "User";
    private static final String OFFICE_LIST_KEY = "OfficeList";
    private static final String BALANCE_KEY = "Balance";
    private static final String TIMEOUT_KEY = "TimeoutSeconds";
    private static final String PWD_EXPIRE_KEY = "Expr";
    private static final String METADATA_TIMESTAMP_KEY = "MetadataTimestamp";
    private static final String MIN_METADATA_TIMESTAMP_KEY = "MinMetadataTimestamp";
    private static final Log LOG = LogFactory.getLog(LoginResponse.class);

    @Setter
    @Getter
    private String sessionId;
    @Getter
    private String memberName;
    @Getter
    private String userInformation;
    @Getter
    private String broker;
    @Getter
    private String metadataVersion;
    @Getter
    private String minMetadataVersion;
    @Getter
    private String metadataTimestamp;
    @Getter
    private String minMetadataTimestamp;
    @Getter
    private String officeList;
    @Getter
    private String balance;
    @Getter
    private int sessionTimeout;
    @Getter
    private String passwordExpiration;
    @Getter
    private final CapabilityUrls capabilityUrls;
    private Set<String[]> brokerCodes;

    public LoginResponse(String loginUrl) {
        super();
        this.brokerCodes = new HashSet<>();
        URL url = null;
        try {
            url = new URL(loginUrl);
        } catch (MalformedURLException e) {
            LOG.warn("Bad URL: " + loginUrl);
        }
        this.capabilityUrls = new CapabilityUrls(url);
    }

    public LoginResponse() {
        super();
        this.capabilityUrls = new CapabilityUrls();
    }

    @Override
    public void parse(InputStream stream, RetsVersion version) throws RetsException {
        super.parse(stream, version);
        if (ReplyCode.BROKER_CODE_REQUIRED.equals(this.mReplyCode)) {
            throw new BrokerCodeRequredException(this.brokerCodes);
        }
    }

    @Override
    protected boolean isValidReplyCode(int replyCode) {
        return (super.isValidReplyCode(replyCode) || ReplyCode.BROKER_CODE_REQUIRED.equals(replyCode));
    }

    @Override
    protected void handleKeyValue(String key, String value) throws RetsException {
        if (ReplyCode.BROKER_CODE_REQUIRED.equals(this.mReplyCode)) {
            if (matchKey(key, BROKER_KEY)) {
                String[] strings = StringUtils.split(value, ",");
                if (strings.length > 0 && strings.length < 3) {
                    this.brokerCodes.add(strings);
                } else {
                    throw new RetsException("Invalid broker/branch code: " + value);
                }
            }
        }

        if (matchKey(key, BROKER_KEY)) {
            this.broker = value;
        } else if (matchKey(key, MEMBER_NAME_KEY)) {
            this.memberName = value;
        } else if (matchKey(key, METADATA_VER_KEY)) {
            this.metadataVersion = value;
        } else if (matchKey(key, MIN_METADATA_VER_KEY)) {
            this.minMetadataVersion = value;
        } else if (matchKey(key, METADATA_TIMESTAMP_KEY)) {
            this.metadataTimestamp = value;
        } else if (matchKey(key, MIN_METADATA_TIMESTAMP_KEY)) {
            this.minMetadataTimestamp = value;
        } else if (matchKey(key, USER_INFO_KEY)) {
            this.userInformation = value;
        } else if (matchKey(key, OFFICE_LIST_KEY)) {
            this.officeList = value;
        } else if (matchKey(key, BALANCE_KEY)) {
            this.balance = value;
        } else if (matchKey(key, TIMEOUT_KEY)) {
            this.sessionTimeout = NumberUtils.toInt(value);
        } else if (matchKey(key, PWD_EXPIRE_KEY)) {
            this.passwordExpiration = value;
        } else if (matchKey(key, CapabilityUrls.ACTION_URL)) {
            this.capabilityUrls.setActionUrl(value);
        } else if (matchKey(key, CapabilityUrls.CHANGE_PASSWORD_URL)) {
            this.capabilityUrls.setChangePasswordUrl(value);
        } else if (matchKey(key, CapabilityUrls.GET_OBJECT_URL)) {
            this.capabilityUrls.setGetObjectUrl(value);
        } else if (matchKey(key, CapabilityUrls.LOGIN_URL)) {
            this.capabilityUrls.setLoginUrl(value);
        } else if (matchKey(key, CapabilityUrls.LOGIN_COMPLETE_URL)) {
            this.capabilityUrls.setLoginCompleteUrl(value);
        } else if (matchKey(key, CapabilityUrls.LOGOUT_URL)) {
            this.capabilityUrls.setLogoutUrl(value);
        } else if (matchKey(key, CapabilityUrls.SEARCH_URL)) {
            this.capabilityUrls.setSearchUrl(value);
        } else if (matchKey(key, CapabilityUrls.GET_METADATA_URL)) {
            this.capabilityUrls.setGetMetadataUrl(value);
        } else if (matchKey(key, CapabilityUrls.UPDATE_URL)) {
            this.capabilityUrls.setUpdateUrl(value);
        } else if (matchKey(key, CapabilityUrls.SERVER_INFO_URL)) {
            this.capabilityUrls.setServerInfo(value);
            LOG.warn("Depreciated: " + key + " -> " + value);
        } else if (matchKey(key, "Get")) {
            LOG.warn("Found bad key: Get -> " + value);
            // FIX ME: Should not get this
        } else {
            if (key.substring(0, 2).equalsIgnoreCase("X-")) {
                LOG.warn("Unknown experimental key: " + key + " -> " + value);
            } else {
                LOG.trace("Unknown login response key: " + key + " -> " + value);
            }
        }
    }
}
