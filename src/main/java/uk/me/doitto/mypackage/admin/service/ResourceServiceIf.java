package uk.me.doitto.mypackage.admin.service;

import java.util.List;

import uk.me.doitto.mypackage.admin.object.ResourceMessage;

public interface ResourceServiceIf {
	void createResource (ResourceMessage resourceMessage);
	void saveResource (ResourceMessage resourceMessage);
	void deleteResource (String resourceId);
	List<ResourceMessage> getAll ();
	ResourceMessage getResource (String resourceId);
	ResourceMessage getResource (String key, String countryCode);
	void setResource (String key, String countryCode, String value);
}
