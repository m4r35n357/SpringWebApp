package uk.me.doitto.mypackage.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

public class JaxRpcTESTWebService extends ServletEndpointSupport implements WebServiceTestIf {

	private final Log log = LogFactory.getLog(getClass());
	
	// don't try to inject this - server.wsdd is not a Spring config file !
	private WebServiceTestIf testWebService = new WebServiceTest();
	// get a reference directly from the application context instead . . .
    protected void onInit () {
        this.testWebService = (WebServiceTestIf)getApplicationContext().getBean("testWebService");
    }

	public void test () {
		log.info("incoming request");
		testWebService.test();
	}
	
	public String hello () {
		log.info("incoming request");
		return testWebService.hello();
	}

	public String echo (String string) {
		log.info("incoming request");
		return testWebService.echo(string);
	}

//	public String getQuote (String symbol) {
//		log.info("incoming request");
//		return testWebService.getQuote(symbol);
//	}
}
