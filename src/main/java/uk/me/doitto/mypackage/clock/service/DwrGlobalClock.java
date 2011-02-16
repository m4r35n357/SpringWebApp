package uk.me.doitto.mypackage.clock.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import uk.me.doitto.mypackage.service.DwrGlobalBase;
import uk.me.doitto.mypackage.service.DwrGlobalWebServiceIf;

public final class DwrGlobalClock extends DwrGlobalBase implements DwrGlobalWebServiceIf {
	
	public DwrGlobalClock () {
		super("Clock_Thread", "clockDisplay", 1000);
	}

	public String getData () {
		return new SimpleDateFormat("EEE MMM dd hh:mm:ss aa", Locale.getDefault()).format(new Date());
	}
}
