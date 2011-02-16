package uk.me.doitto.mypackage.admin.service;

import java.util.List;

import uk.me.doitto.mypackage.admin.object.Owner;

public interface OwnerServiceIf {
	void createOwner (Owner owner);
	void saveOwner (Owner owner);
	void deleteOwner (String ownerId);	
	Owner getOwner (String ownerId);
	List<Owner> getAllOwners ();
	void toggleEnabled (String ownerId);
	String encodePassword (String password, Object salt);
	String getCurrentOwnerId ();
}
