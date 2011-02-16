package uk.me.doitto.mypackage.clock.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import uk.me.doitto.mypackage.service.DwrGlobalWebServiceIf;

public class JaxRpcCLOCKWebService extends ServletEndpointSupport implements DwrGlobalWebServiceIf {

	private final Log log = LogFactory.getLog(getClass());
	
	// don't try to inject this - server.wsdd is not a Spring config file !
	private DwrGlobalWebServiceIf dwrClock;
	// get a reference directly from the application context instead . . .
    protected void onInit () {
        this.dwrClock = (DwrGlobalWebServiceIf)getApplicationContext().getBean("dwrClock");
    }

	public String getData() {
		log.info("incoming request");
		return dwrClock.getData();
	}

	public boolean isActive() {
		log.info("incoming request");
		return dwrClock.isActive();
	}

	public void toggle() {
		log.info("incoming request");
		dwrClock.toggle();
	}
}
