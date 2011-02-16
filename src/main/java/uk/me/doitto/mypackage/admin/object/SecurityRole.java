package uk.me.doitto.mypackage.admin.object;

import uk.me.doitto.mypackage.globals.DropDownEntry;

public enum SecurityRole implements DropDownEntry {
	NONE("ROLE_NONE"),
	USER("ROLE_USER"),
	REMOTE("ROLE_REMOTE"),
	SUPERVISOR("ROLE_SUPERVISOR");
	
	private String code;

	SecurityRole (String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
