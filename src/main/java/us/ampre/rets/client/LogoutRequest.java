package us.ampre.rets.client;

public class LogoutRequest extends VersionInsensitiveRequest {

	@Override
	public void setUrl(CapabilityUrls urls) {
		setUrl(urls.getLogoutUrl());
	}
}
