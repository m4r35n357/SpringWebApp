package uk.me.doitto.mypackage.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.me.doitto.mypackage.rss.object.AggregatedFeed;
import uk.me.doitto.mypackage.rss.object.CachedRssFeed;
import uk.me.doitto.mypackage.rss.service.RSSServiceIf;

public class ServiceIntegrationTests {
	
	RSSServiceIf rssService;
	AggregatedFeed aggregatedFeed;
	CachedRssFeed cachedRssFeed;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		rssService = (RSSServiceIf)new FileSystemXmlApplicationContext(new String[] {
						"WEB-INF/config/spring/applicationContext.xml",
						"WEB-INF/config/spring/applicationContext-admin.xml",
						"WEB-INF/config/spring/applicationContext-security.xml",
						"WEB-INF/config/spring/applicationContext-rss.xml" }).getBean("rssServiceProxy");
		aggregatedFeed = new AggregatedFeed();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGenerateStringFromAggregate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateAggregatedFeed() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDestroyAggregatedFeed() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddRssFeedToAggregate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveRssFeedFromAggregate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateCache() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testLoadAllAggregateData() {
		rssService.getAllAggregateData();
	}

	@Test
	public void testGetAllAggregateMetaData() {
		rssService.getAllAggregateMetaData();
	}

	@Test
	public void testGetFeedUrlMap() {
		rssService.getFeedUrlMap();
	}

}
