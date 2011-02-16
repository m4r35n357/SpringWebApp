package uk.me.doitto.mypackage.rss.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AggregatedFeedTest {
	
	AggregatedFeed aggregatedFeed = new AggregatedFeed();
	String id = "ASDFGHJK";
	String ownerId = "ABCDEFGH";
	String name = "XXXXXXXX";
	Set<CachedRssFeed> feeds = new HashSet<CachedRssFeed>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		aggregatedFeed.setId(id);
		aggregatedFeed.setOwnerId(ownerId);
		aggregatedFeed.setName(name);
		CachedRssFeed feed1 = new CachedRssFeed();
		feed1.setName("name1");
		feed1.setUrl("url1");
		aggregatedFeed.addFeed(feed1);
		feeds.add(feed1);
		CachedRssFeed feed2 = new CachedRssFeed();
		feed2.setName("name2");
		feed2.setUrl("url2");
		aggregatedFeed.addFeed(feed2);
		feeds.add(feed2);
		CachedRssFeed feed3 = new CachedRssFeed();
		feed3.setName("name3");
		feed3.setUrl("url3");
		aggregatedFeed.addFeed(feed3);
		feeds.add(feed3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAggregatedFeed() {
		aggregatedFeed = new AggregatedFeed();
		assertNull(aggregatedFeed.getId());
		assertNull(aggregatedFeed.getOwnerId());
		assertNull(aggregatedFeed.getName());
		assertEquals(0, aggregatedFeed.getFeeds().size());
	}

	@Test
	public void testAggregatedFeedString() {
		aggregatedFeed = new AggregatedFeed(ownerId);
		assertNull(aggregatedFeed.getId());
		assertEquals(ownerId, aggregatedFeed.getOwnerId());
		assertNull(aggregatedFeed.getName());
		assertEquals(0, aggregatedFeed.getFeeds().size());
	}

	@Test
	public void testAggregatedFeedStringString() {
		aggregatedFeed = new AggregatedFeed(name, ownerId);
		assertNull(aggregatedFeed.getId());
		assertEquals(ownerId, aggregatedFeed.getOwnerId());
		assertEquals(name, aggregatedFeed.getName());
		assertEquals(0, aggregatedFeed.getFeeds().size());
	}

	@Test
	public void testGetFeeds() {
		assertEquals(feeds, aggregatedFeed.getFeeds());
	}

	@Test
	public void testSetFeeds() {
		CachedRssFeed feed4 = new CachedRssFeed();
		feed4.setName("name4");
		feed4.setUrl("url4");
		aggregatedFeed.addFeed(feed4);
		feeds.add(feed4);
		assertEquals(feeds, aggregatedFeed.getFeeds());
	}

	@Test
	public void testAddFeed() {
		String name = "nameA";
		String url = "urlA";
		CachedRssFeed feed = new CachedRssFeed();
		feed.setName(name);
		feed.setUrl(url);
		aggregatedFeed.addFeed(feed);
		assertEquals(true, aggregatedFeed.getFeeds().contains(feed));
	}

	@Test
	public void testRemoveFeed() {
		CachedRssFeed feed = new CachedRssFeed();
		feed.setName("name2");
		feed.setUrl("url2");
		aggregatedFeed.removeFeed(feed);
		assertEquals(false, aggregatedFeed.getFeeds().contains(feed));
	}

	@Test
	public void testGetName() {
		assertEquals(name, aggregatedFeed.getName());
	}

	@Test
	public void testSetName() {
		String name = "YYYYYYYY";
		aggregatedFeed.setName(name);
		assertEquals(name, aggregatedFeed.getName());
	}

	@Test
	public void testIsInUse() {
		assertEquals(! aggregatedFeed.getFeeds().isEmpty(), aggregatedFeed.isInUse());
	}

}
