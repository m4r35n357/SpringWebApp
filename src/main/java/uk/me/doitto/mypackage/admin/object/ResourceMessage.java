package uk.me.doitto.mypackage.admin.object;

import java.util.EnumSet;

import uk.me.doitto.mypackage.globals.Country;
import uk.me.doitto.mypackage.globals.Language;
import uk.me.doitto.mypackage.object.PersistentClass;

public class ResourceMessage extends PersistentClass {
	
	private static final EnumSet<Language> languages = EnumSet.allOf(Language.class);
	
	private static final EnumSet<Country> countries = EnumSet.allOf(Country.class);
	
	private String language;
	
	private String countryCode;
	
	private String value;
	
	// hibernate
	public ResourceMessage () {
		super();
	}
	// dao search
	public ResourceMessage (String key) {
		this();
		this.name = key;
	}
	// dao search
	public ResourceMessage (String key, String countryCode) {
		this(key);
		this.countryCode = countryCode;
	}

	public String getKey () {
		return name;
	}
	public void setKey (String key) {
		this.name = key;
	}

	public String getLanguage() {
		return language;
	}	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCountryCode () {
		return countryCode;
	}
	public void setCountryCode (String countryCode) {
		this.countryCode = countryCode;
	}

	public String getValue () {
		return value;
	}
	public void setValue (String value) {
		this.value = value;
	}
	
	public EnumSet<Language> getLanguages() {
		return languages;
	}
	
	public EnumSet<Country> getCountries() {
		return countries;
	}
}
