package uk.me.doitto.mypackage.rss.object;

import java.util.HashSet;
import java.util.Set;

import uk.me.doitto.mypackage.object.Ownable;

public class AggregatedFeed extends Ownable {
	
	private Set<CachedRssFeed> feeds = new HashSet<CachedRssFeed>();
	
	private Object feedsLock = new Object();
	
	// for hibernate
	public AggregatedFeed() {
		super();
	}
	// for search templates
	public AggregatedFeed(String ownerId) {
		this();
		this.ownerId = ownerId;
	}
	// for normal object creation
	public AggregatedFeed(String name, String ownerId) {
		this();
		this.name = name;
		this.ownerId = ownerId;
	}

	/**
	 * @return Returns the feeds.
	 */
	public Set<CachedRssFeed> getFeeds () {
		synchronized (feedsLock) {
			return feeds;
		}
	}	
	/**
	 * @param feeds The feeds to set.
	 */
	public void setFeeds (Set<CachedRssFeed> feeds) {
		synchronized (feedsLock) {
			this.feeds = feeds;
		}
	}
	
	/**
	 * Creates a two-way link between this and a CachedRssFeed object
	 * 
	 * @param feed
	 */
	public void addFeed (CachedRssFeed feed) {
		feeds.add(feed);
		feed.getAggregates().add(this);
	}
	/**
	 * Destroys a two-way link between this and a CachedRssFeed object
	 * 
	 * @param feed
	 */
	public void removeFeed (CachedRssFeed feed) {
		feeds.remove(feed);
		feed.getAggregates().remove(this);
	}
	
	/**
	 * Tests if aggregated feed is populated eg. prior to attempted removal
	 * @return true if populated
	 */
	public boolean isInUse () {
		return ! getFeeds().isEmpty();
	}
}
