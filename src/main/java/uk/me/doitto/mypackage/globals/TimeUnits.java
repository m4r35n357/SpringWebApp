package uk.me.doitto.mypackage.globals;

public enum TimeUnits {
	SECONDS(1000), MINUTES(SECONDS.value * 60), HOURS(MINUTES.value * 60), DAYS(HOURS.value * 24);
	
	public int value;

	TimeUnits (int value) {
		this.value = value;
	}

}
