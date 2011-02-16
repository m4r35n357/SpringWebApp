package uk.me.doitto.mypackage.admin.service;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

public class JaxRpcResourceWebService extends ServletEndpointSupport implements ResourceWebServiceIf {

	// don't try to inject this - server.wsdd is not a Spring config file !
	private ResourceWebServiceIf resourceService;
	// get a reference directly from the application context instead . . .
    protected void onInit () {
        this.resourceService = (ResourceWebServiceIf)getApplicationContext().getBean("resourceService");
    }
    
	public String getResourceString (String key, String countryCode) {
		return resourceService.getResourceString(key, countryCode);
	}
	public void setResourceString (String key, String countryCode, String value) {
		resourceService.setResourceString(key, countryCode, value);
	}
}
