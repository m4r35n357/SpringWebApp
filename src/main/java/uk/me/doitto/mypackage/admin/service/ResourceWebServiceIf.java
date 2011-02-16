package uk.me.doitto.mypackage.admin.service;

public interface ResourceWebServiceIf {
	String getResourceString (String key, String countryCode);
	void setResourceString (String key, String countryCode, String value);
}
