package uk.me.doitto.mypackage.admin.object;

import uk.me.doitto.mypackage.globals.BitFieldIf;

public enum AccountFlag implements BitFieldIf {
	ENABLED(1),
	ACCOUNT_NON_EXPIRED(2),
	ACCOUNT_NON_LOCKED(4),
	CREDENTIALS_NON_EXPIRED(8);
	
	private int value;

	AccountFlag (int value) {
		this.value = value;
	}
	
	public int getValue () {
		return value;
	}
}
