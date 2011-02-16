package uk.me.doitto.mypackage.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InetService {
	
	private static final Log log = LogFactory.getLog(InetService.class);
	
	private static InetAddress inetAddress;
	
	private static String hostName;
	
	private static String ipAddress;

	static {
		try {
			inetAddress = InetAddress.getLocalHost();
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private InetService () {
	}

	public static String getHostName() {
		log.info("");
		return hostName;
	}

	public static String getIpAddress() {
		log.info("");
		return ipAddress;
	}

	public static InetAddress getInetAddress() {
		log.info("");
		return inetAddress;
	}
}
