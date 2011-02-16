package uk.me.doitto.mypackage.service;

public interface DwrGlobalWebServiceIf {

	/**
	 * Call via javascript using DWR.
	 * 
	 * Stop/start thread
	 */
	void toggle ();

	boolean isActive ();

	/**
	 * Override this method in your subclasses
	 * 
	 * @return data string
	 */
	String getData ();
}
