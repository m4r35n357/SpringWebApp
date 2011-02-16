package uk.me.doitto.mypackage.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService(serviceName="TESTWebService")
public class JaxWsTESTWebService extends SpringBeanAutowiringSupport implements WebServiceTestIf {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private WebServiceTestIf testWebService;
	
	@WebMethod
	public void test () {
		log.info("incoming request");
		testWebService.test();
	}
	
    @WebMethod
	public String hello () {
		log.info("incoming request");
		return testWebService.hello();
	}

    @WebMethod
	public String echo (String string) {
		log.info("incoming request");
		return testWebService.echo(string);
	}

//	public String getQuote (String symbol) {
//		log.info("incoming request");
//		return testWebService.getQuote(symbol);
//	}
}
