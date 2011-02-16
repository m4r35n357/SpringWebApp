package uk.me.doitto.mypackage.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.me.doitto.mypackage.mm.service.MusicManagerWebServiceIf;
import uk.me.doitto.mypackage.rss.service.RSSWebServiceIf;
import uk.me.doitto.mypackage.service.DwrGlobalWebServiceIf;

public class RemotingTests {

	private static final String hostPort = "grace:8080";
//	private static final String hostPort = "server";
	private static final String appName = "MusicManager";
	private static final String clockServiceName = "XXXXWebService";
	private static final String rssServiceName = "RSSWebService";
	private static final String mmServiceName = "MusicManagerWebService";
	private static final String username = "remoteuser";
	private static final String password = "remoteuser";
//	private static final String albumId = "8a80807e1374724a01137643d4db0001";
	
	DwrGlobalWebServiceIf clockHessianProxy = RemotingProxy.getHessianProxy(DwrGlobalWebServiceIf.class, hostPort, appName, clockServiceName, username, password);
	DwrGlobalWebServiceIf clockBurlapProxy = RemotingProxy.getBurlapProxy(DwrGlobalWebServiceIf.class, hostPort, appName, clockServiceName, username, password);
	DwrGlobalWebServiceIf clockHttpInvokerProxy = RemotingProxy.getHttpInvokerProxy(DwrGlobalWebServiceIf.class, hostPort, appName, clockServiceName, username, password);
	DwrGlobalWebServiceIf clockJaxRpcProxy = RemotingProxy.getJaxRpcProxy(DwrGlobalWebServiceIf.class, hostPort, appName, clockServiceName, username, password);
	
	RSSWebServiceIf rssHessianProxy = RemotingProxy.getHessianProxy(RSSWebServiceIf.class, hostPort, appName, rssServiceName, username, password);
	RSSWebServiceIf rssBurlapProxy = RemotingProxy.getBurlapProxy(RSSWebServiceIf.class, hostPort, appName, rssServiceName, username, password);
	RSSWebServiceIf rssHttpInvokerProxy = RemotingProxy.getHttpInvokerProxy(RSSWebServiceIf.class, hostPort, appName, rssServiceName, username, password);
	RSSWebServiceIf rssJaxRpcProxy = RemotingProxy.getJaxRpcProxy(RSSWebServiceIf.class, hostPort, appName, rssServiceName, username, password);
	
	MusicManagerWebServiceIf mmHessianProxy = RemotingProxy.getHessianProxy(MusicManagerWebServiceIf.class, hostPort, appName, mmServiceName, username, password);
	MusicManagerWebServiceIf mmBurlapProxy = RemotingProxy.getBurlapProxy(MusicManagerWebServiceIf.class, hostPort, appName, mmServiceName, username, password);
	MusicManagerWebServiceIf mmHttpInvokerProxy = RemotingProxy.getHttpInvokerProxy(MusicManagerWebServiceIf.class, hostPort, appName, mmServiceName, username, password);	
	MusicManagerWebServiceIf mmJaxRpcProxy = RemotingProxy.getJaxRpcProxy(MusicManagerWebServiceIf.class, hostPort, appName, mmServiceName, username, password);
	
	String helloMatch = "^hello from .*$";
	String echoMatch = "^.* says, You typed: .*$";
	
	String ownerId = "ff7f7f7e14c163570114c164531a0001";
	String aggregatedFeedName = "RemotingTest";
	String slashDotTitle = "Slashdot";
	String slashDotRssUrl = "http://rss.slashdot.org/Slashdot/slashdot";
	
	String titleMatch = ".*Title: .*";
	String nameMatch = ".*Name: .*";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testXXXXGetHessianProxy() {
//		assertTrue(clockHessianProxy.hello().matches(helloMatch));
//		assertTrue(clockHessianProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testXXXXGetBurlapProxy() {
//		assertTrue(clockBurlapProxy.hello().matches(helloMatch));
//		assertTrue(clockBurlapProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testXXXXGetHttpInvokerProxy() {
//		assertTrue(clockHttpInvokerProxy.hello().matches(helloMatch));
//		assertTrue(clockHttpInvokerProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testXXXXGetJaxRpcProxy() {
//		assertTrue(clockJaxRpcProxy.hello().matches(helloMatch));
//		assertTrue(clockJaxRpcProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testRssGetHessianProxy() {
//		assertTrue(rssHessianProxy.hello().matches(helloMatch));
//		assertTrue(rssHessianProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testRssGetBurlapProxy() {
//		assertTrue(rssBurlapProxy.hello().matches(helloMatch));
//		assertTrue(rssBurlapProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testRssGetHttpInvokerProxy() {
//		assertTrue(rssHttpInvokerProxy.hello().matches(helloMatch));
//		assertTrue(rssHttpInvokerProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testRssGetJaxRpcProxy() {
//		assertTrue(rssJaxRpcProxy.hello().matches(helloMatch));
//		assertTrue(rssJaxRpcProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testMusicManagerGetHessianProxy() {
//		assertTrue(mmHessianProxy.hello().matches(helloMatch));
//		assertTrue(mmHessianProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testMusicManagerGetBurlapProxy() {
//		assertTrue(mmBurlapProxy.hello().matches(helloMatch));
//		assertTrue(mmBurlapProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testMusicManagerGetHttpInvokerProxy() {
//		assertTrue(mmHttpInvokerProxy.hello().matches(helloMatch));
//		assertTrue(mmHttpInvokerProxy.echo("hi!!").matches(echoMatch));
//	}
//
//	@Test
//	public void testMusicManagerGetJaxRpcProxy() {
//		assertTrue(mmJaxRpcProxy.hello().matches(helloMatch));
//		assertTrue(mmJaxRpcProxy.echo("hi!!").matches(echoMatch));
//	}

	@Test
	public void testRssDbGetHessianProxy() {
		rssHessianProxy.createAggregatedFeed(aggregatedFeedName);
		rssHessianProxy.addRssFeedToAggregate(aggregatedFeedName, slashDotRssUrl);
		rssHessianProxy.removeRssFeedFromAggregate(aggregatedFeedName, slashDotTitle);
		rssHessianProxy.destroyAggregatedFeed(aggregatedFeedName);
	}
	
	@Test
	public void testMusicManagerDbGetHessianProxy() {
//		String results = mmHessianProxy.printAlbums();
//		assertTrue(results.matches(titleMatch));
//		assertTrue(mmHessianProxy.printTracks().matches(titleMatch));
//		assertTrue(mmHessianProxy.printArtists().matches(nameMatch));
		mmHessianProxy.printAlbums();
		mmHessianProxy.printTracks();
		mmHessianProxy.printArtists();
	}

}
