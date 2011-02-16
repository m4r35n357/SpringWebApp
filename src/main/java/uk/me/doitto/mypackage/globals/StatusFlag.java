package uk.me.doitto.mypackage.globals;

public enum StatusFlag {
	OFF("off"), ON("on");
	
	public String value;

	StatusFlag (String value) {
		this.value = value;
	}
}
