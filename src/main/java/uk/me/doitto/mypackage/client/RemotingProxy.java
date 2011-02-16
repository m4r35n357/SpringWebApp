package uk.me.doitto.mypackage.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.ServiceFactory;
import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.FactoryBeanNotInitializedException;
import org.springframework.remoting.caucho.BurlapProxyFactoryBean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;

/**
* These methods are useful for creating proxies on-the-fly in dynamic applications where the remote host
* is discovered at runtime, and a singleton spring bean is therefore inappropriate.
* 
* NB. Generic methods are used, rather than wildcards, to tie the interface class to its interface, and the return type.)
* 
* @author Ian Smith
*
*/
public class RemotingProxy {
	/**
	 * @param ifClass is the class of the proxy interface you are trying to obtain
	 * @param hostPort is the hostname:port of the web service host
	 * @param contextName is the deployed context of the web service application
	 * @param username for basic authentication
	 * @param password for basic authentication
	 * @param serviceName is the first part of the bean name for the remoting facility you are invoking - see remoting-servlet.xml
	 * @return a proxy interface for accessing the service via Caucho's Hessian protocol
	 * @throws FactoryBeanNotInitializedException
	 */
	@SuppressWarnings("unchecked")
	public static <S> S getHessianProxy (Class<S> ifClass, String hostPort, String contextName, String serviceName, String username, String password) {
		HessianProxyFactoryBean hessianProxyFactoryBean  = new HessianProxyFactoryBean();
		hessianProxyFactoryBean.setServiceInterface(ifClass);
		hessianProxyFactoryBean.setServiceUrl("http://" + hostPort + "/" + contextName + "/remoting/" + serviceName + "-hessian");
		hessianProxyFactoryBean.setUsername(username);
		hessianProxyFactoryBean.setPassword(password);
		hessianProxyFactoryBean.afterPropertiesSet();
		return (S)hessianProxyFactoryBean.getObject();
	}

	/**
	 * @param ifClass is the class of the proxy interface you are trying to obtain
	 * @param hostPort is the hostname:port of the web service host
	 * @param contextName is the deployed context of the web service application
	 * @param username for basic authentication
	 * @param password for basic authentication
	 * @param serviceName is the first part of the bean name for the remoting facility you are invoking - see remoting-servlet.xml
	 * @return a proxy interface for accessing the service via Caucho's Burlap protocol
	 * @throws FactoryBeanNotInitializedException
	 */
	@SuppressWarnings("unchecked")
	public static <S> S getBurlapProxy (Class<S> ifClass, String hostPort, String contextName, String serviceName, String username, String password) {
		BurlapProxyFactoryBean burlapProxyFactoryBean  = new BurlapProxyFactoryBean();
		burlapProxyFactoryBean.setServiceInterface(ifClass);
		burlapProxyFactoryBean.setServiceUrl("http://" + hostPort + "/" + contextName + "/remoting/" + serviceName + "-burlap");
		burlapProxyFactoryBean.setUsername(username);
		burlapProxyFactoryBean.setPassword(password);
		burlapProxyFactoryBean.afterPropertiesSet();
		return (S)burlapProxyFactoryBean.getObject();
	}

	/**
	 * @param ifClass is the class of the proxy interface you are trying to obtain
	 * @param hostPort is the hostname:port of the web service host
	 * @param contextName is the deployed context of the web service application
	 * @param username for basic authentication
	 * @param password for basic authentication
	 * @param serviceName is the first part of the bean name for the remoting facility you are invoking - see remoting-servlet.xml
	 * @return a proxy interface for accessing the service via Spring's HTTP-invoker
	 * @throws FactoryBeanNotInitializedException
	 */
	@SuppressWarnings("unchecked")
	public static <S> S getHttpInvokerProxy (Class<S> ifClass, String hostPort, String contextName, String serviceName, String username, String password) {
		HttpClientFactoryBean httpClientFactoryBean = new HttpClientFactoryBean();
		httpClientFactoryBean.setUsername(username);
		httpClientFactoryBean.setPassword(password);
		try {
			httpClientFactoryBean.afterPropertiesSet();
		} catch (Exception e) {
			System.out.println("WARNING: non-authenticated proxy generated - prepare for meltdown!");
		} 
		HttpClient httpClient = (HttpClient)httpClientFactoryBean.getObject();
		CommonsHttpInvokerRequestExecutor requestExecutor = new CommonsHttpInvokerRequestExecutor();
		requestExecutor.setHttpClient(httpClient);
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		httpInvokerProxyFactoryBean.setServiceInterface(ifClass);
		httpInvokerProxyFactoryBean.setServiceUrl("http://" + hostPort + "/" + contextName + "/remoting/" + serviceName + "-http-invoker");
		httpInvokerProxyFactoryBean.setHttpInvokerRequestExecutor(requestExecutor);
		httpInvokerProxyFactoryBean.afterPropertiesSet();
		return (S)httpInvokerProxyFactoryBean.getObject();
	}

	/**
	 * @param ifClass is the class of the proxy interface you are trying to obtain
	 * @param hostPort is the hostname:port of the web service host
	 * @param contextName is the deployed context of the web service application
	 * @param serviceName is the name attribute of the service tag in server-config.wsdd
	 * @return a proxy interface for accessing the service via JAX_RPC
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public static <S> S getJaxRpcProxy (Class<S> ifClass, String hostPort, String contextName, String serviceName, String username, String password) {
		JaxRpcPortProxyFactoryBean jaxRpcPortProxyFactoryBean = new JaxRpcPortProxyFactoryBean();
		jaxRpcPortProxyFactoryBean.setServiceFactoryClass(ServiceFactory.class);
		try {
			jaxRpcPortProxyFactoryBean.setWsdlDocumentUrl(new URL("http://" + hostPort + "/" + contextName + "/axis/" + serviceName + "?wsdl"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		jaxRpcPortProxyFactoryBean.setNamespaceUri("http://" + hostPort + "/" + contextName + "/axis/" + serviceName);
		jaxRpcPortProxyFactoryBean.setServiceName("JaxRpc" + serviceName + "Service");
		jaxRpcPortProxyFactoryBean.setPortName(serviceName);
		jaxRpcPortProxyFactoryBean.setServiceInterface(ifClass);
		jaxRpcPortProxyFactoryBean.setUsername(username);
		jaxRpcPortProxyFactoryBean.setPassword(password);
		jaxRpcPortProxyFactoryBean.afterPropertiesSet();
		return (S)jaxRpcPortProxyFactoryBean.getObject();
	}
}
