package uk.me.doitto.mypackage.service;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

import uk.me.doitto.mypackage.globals.StatusFlag;

public abstract class DwrGlobalBase implements Runnable {

	protected final Log log = LogFactory.getLog(getClass());
	
	private ServerContext serverContext;

	private boolean active = false;
	
	private static final String PAGE_ATTRIBUTE="currentPage";
	
	private static final String SSID_ATTRIBUTE="ssId";
	
	/**
	 * Set this in your subclass constructor - 
	 * Stored in http session, should be unique among service threads
	 */
	protected String threadId;

	/**
	 * Set this in your subclass constructor - 
	 * Data are sent to HTML items with this id attribute
	 */
	protected String htmlId;
	
	/**
	 * Set this in your subclass constructor - 
	 * How frequently do you want to update your data?
	 */
	protected int interval;
	
	/**
	 * DWR reverse AJAX - base class constructor
	 */
	public DwrGlobalBase (String threadId, String htmlId, int interval) {
		this.threadId = threadId;
		this.htmlId = htmlId;
		this.interval = interval;
		serverContext = ServerContextFactory.get(getWebContext().getServletContext());
	}

	/**
	 * Call via javascript using DWR.
	 * 
	 * Stop/start thread
	 */
	public void toggle () {
		active = !active;
		if (active) {
			new Thread(this).start();
		}
	}

	public boolean isActive () {
		return active;
	}

	/**
	 * Should be called for new pages via onload attribute in the HTML body tag
	 * 
	 * Get a fresh script session, storing its ID and page in the http session
	 */
	public void pageInit () {
		WebContext wc = getWebContext();
		HttpSession session = wc.getSession();
		// invalidate old script session for the page we have just left
		ScriptSession ss = getSsFromHttp(session);
		if ((ss != null) && (! ss.isInvalidated())) {
			ss.invalidate();
		}
		// and store data for the new one in the session
		session.setAttribute(PAGE_ATTRIBUTE, wc.getCurrentPage());
		session.setAttribute(SSID_ATTRIBUTE, wc.getScriptSession().getId());
		if (active) {
			session.setAttribute(threadId + "_Global", StatusFlag.ON.value);
		} else {
			session.setAttribute(threadId + "_Global", StatusFlag.OFF.value);
		}
		setServiceWantedStatus(session);
	}
	
	/**
	 * Call via javascript using DWR.
	 * 
	 * Stores service wanted status in the http session
	 */
	public void toggleServiceWanted () {
		HttpSession session = getWebContext().getSession();
		if ((session.getAttribute(threadId) == null) || ((String)session.getAttribute(threadId)).equals(StatusFlag.OFF.value)) {
			session.setAttribute(threadId, StatusFlag.ON.value);
		} else {
			session.setAttribute(threadId, StatusFlag.OFF.value);
		}			
		setServiceWantedStatus(session);
	}
	
	/**
	 * Main thread loop
	 */
	public void run () {
		try {
			log.debug(threadId + ": Starting server-side thread");
			while (active) {
				sendData(getData());
				log.debug("Sent message(s)");
				Thread.sleep(interval);
			}
			sendData("");
			log.debug(threadId + ": Stopping server-side thread");
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Override this method in your subclasses
	 * 
	 * @return data string
	 */
	protected abstract String getData ();

	/**
	 * Call via javascript using DWR
	 * 
	 * Updates service wanted status from the http session to the script session
	 */
	private void setServiceWantedStatus (HttpSession session) {
		getSsFromHttp(session).setAttribute(threadId, (String)session.getAttribute(threadId));
	}
	
	/**
	 * Send data to the named HTML Id in registered script sessions
	 * 
	 * @param data
	 */
	private void sendData (String data) {
		for (Object item : serverContext.getAllScriptSessions()) {
			ScriptSession ss = (ScriptSession)item;
			if ((ss != null) && (! ss.isInvalidated())) {
				Util util = new Util(ss);
				Object dataWanted = ss.getAttribute(threadId);
				if ((dataWanted != null) && ((String)dataWanted).equals(StatusFlag.ON.value)) {
					util.setValue(htmlId, data);
				} else {
					util.setValue(htmlId, null);
				}
			}
		}
	}

	/**
	 * Retrieve script session using identifiers stored in an HTTP session
	 * @param session
	 * @return
	 */
	private ScriptSession getSsFromHttp (HttpSession session) {
		ScriptSession result = null;
		for (Object item : serverContext.getScriptSessionsByPage((String)session.getAttribute(PAGE_ATTRIBUTE))) {
			ScriptSession ss = (ScriptSession)item;
			if ((ss != null) && (! ss.isInvalidated()) && (ss.getId().equals(session.getAttribute(SSID_ATTRIBUTE)))) {
				result = ss;
				break;
			}
		}
		return result;
	}
	
	private WebContext getWebContext () {
		return WebContextFactory.get();
	}
}
