package uk.me.doitto.mypackage.globals;

public enum Country implements DropDownEntry {
	DEFAULT("DEF"), GB("GB"), FR("FR"), DE("DE"), US("US"), IN("IN"), CN("CN");
	
	private String code;

	Country (String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
