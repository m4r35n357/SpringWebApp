package uk.me.doitto.mypackage.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebServiceTest implements WebServiceTestIf {
	
	private final Log log = LogFactory.getLog(getClass());
	
	public void test () {
		log.info("incoming request");
	}
	
	public String hello () {
		log.info("incoming request");
		return "hello from " + getClass().getName() + ", on: " + InetService.getHostName();
	}

	public String echo (String string) {
		log.info("incoming request");
		return InetService.getHostName() + " says, You typed: " + string;
	}

//	public String getQuote(String symbol) {
//		log.info("incoming request");
////		return "£" + symbol + "_price";
//		return "#" + symbol + "_price";
//	}
}
