package uk.me.doitto.mypackage.admin.object;

import uk.me.doitto.mypackage.globals.BitFieldIf;
import uk.me.doitto.mypackage.globals.DropDownEntry;

public enum ModuleFlag implements DropDownEntry,BitFieldIf {
	CLOCK("CLOCK", 16),
	MM("MM", 1),
	RSS("RSS", 2);
	
	private String code;

	private int value;

	ModuleFlag (String code, int value) {
		this.code = code;
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}
	
	public int getValue () {
		return value;
	}
}
