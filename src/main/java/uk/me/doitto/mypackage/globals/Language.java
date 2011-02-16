package uk.me.doitto.mypackage.globals;

public enum Language implements DropDownEntry {
	DEFAULT("DEF"), en("en"), fr("fr"), de("de"), ja("ja"), ko("ko"), zh("zh");
	
	private String code;

	Language (String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
