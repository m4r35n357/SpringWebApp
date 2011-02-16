/**
 * 
 */
package uk.me.doitto.mypackage.rss.object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.me.doitto.mypackage.globals.TimeUnits;

/**
 * @author ian
 *
 */
public class CachedRssFeedTest {
	
	CachedRssFeed cachedRssFeed = new CachedRssFeed();
	Date now = new Date();
	Date tenMinutesAgo = new Date(now.getTime() - 10 * TimeUnits.MINUTES.value);
	Date fortyMinutesAgo = new Date(now.getTime() - 40 * TimeUnits.MINUTES.value);
	int cacheExpiryTime = 30;
	Set<AggregatedFeed> aggregates = new HashSet<AggregatedFeed>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cachedRssFeed.setName("feedName");
		cachedRssFeed.setFeedData("sdgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsfdgsdfg");
		cachedRssFeed.setLastFetch(tenMinutesAgo);
		AggregatedFeed aggregate1 = new AggregatedFeed();
		aggregate1.addFeed(cachedRssFeed);
		aggregates.add(aggregate1);
		AggregatedFeed aggregate2 = new AggregatedFeed();
		aggregate2.addFeed(cachedRssFeed);
		aggregates.add(aggregate2);
		AggregatedFeed aggregate3 = new AggregatedFeed();
		aggregate3.addFeed(cachedRssFeed);
		aggregates.add(aggregate3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(cachedRssFeed.getName(), "feedName");
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		cachedRssFeed.setName("newFeedName");
		assertEquals(cachedRssFeed.getName(), "newFeedName");
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#getFeedData()}.
	 */
	@Test
	public void testGetFeedData() {
		assertEquals(cachedRssFeed.getFeedData(), "sdgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsfdgsdfg");
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#setFeedData(java.lang.String)}.
	 */
	@Test
	public void testSetFeedData() {
		cachedRssFeed.setFeedData("zxc,vmnz.x,mncv.,mznxc.v,mznx.,cvxz");
		assertEquals(cachedRssFeed.getFeedData(), "zxc,vmnz.x,mncv.,mznxc.v,mznx.,cvxz");
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#getLastFetch()}.
	 */
	@Test
	public void testGetLastFetch() {
		assertEquals(cachedRssFeed.getLastFetch(), tenMinutesAgo);
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#setLastFetch(java.util.Date)}.
	 */
	@Test
	public void testSetLastFetch() {
		cachedRssFeed.setLastFetch(now);
		assertEquals(cachedRssFeed.getLastFetch(), now);
	}

	/**
	 * Test method for {@link uk.me.doitto.mypackage.rss.object.CachedRssFeed#isCacheExpired(int)}.
	 */
	@Test
	public void testIsCacheExpired() {
		assertFalse(cachedRssFeed.isCacheExpired(cacheExpiryTime));
		cachedRssFeed.setLastFetch(fortyMinutesAgo);
		assertTrue(cachedRssFeed.isCacheExpired(cacheExpiryTime));
	}

	@Test
	public void testGetAggregates() {
		assertEquals(aggregates, cachedRssFeed.getAggregates());
	}

	@Test
	public void testSetAggregates() {
		AggregatedFeed aggregate4 = new AggregatedFeed();
		aggregate4.addFeed(cachedRssFeed);
		aggregates.add(aggregate4);
		aggregates.add(aggregate4);
		assertEquals(aggregates, cachedRssFeed.getAggregates());
	}
}
