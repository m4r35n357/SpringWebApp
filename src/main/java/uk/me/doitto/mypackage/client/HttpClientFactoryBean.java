package uk.me.doitto.mypackage.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class HttpClientFactoryBean implements FactoryBean, InitializingBean {
	
	private HttpClient httpClient;
	
	private String username;
	
	private String password;
	
	private String authenticationHost;

	private String authenticationRealm;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthenticationHost(String authenticationHost) {
		this.authenticationHost = authenticationHost;
	}

	public void setAuthenticationRealm(String authenticationRealm) {
		this.authenticationRealm = authenticationRealm;
	}

	public Object getObject() {
		return httpClient;
	}

	public Class<HttpClient> getObjectType() {
		return HttpClient.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		if ((username == null) || (password == null)) {
			throw new IllegalArgumentException("You must set the username and password!");
		}	
		httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		httpClient.getState().setCredentials(new AuthScope(authenticationHost, -1, authenticationRealm), new UsernamePasswordCredentials(username, password));
	}
}
