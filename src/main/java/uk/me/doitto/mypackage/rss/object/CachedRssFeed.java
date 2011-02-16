package uk.me.doitto.mypackage.rss.object;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uk.me.doitto.mypackage.globals.TimeUnits;
import uk.me.doitto.mypackage.object.PersistentClass;

public class CachedRssFeed extends PersistentClass {
	
	private String url;
	
	private String feedData;
	
	private Date lastFetch;
	
	private Set<AggregatedFeed> aggregates = new HashSet<AggregatedFeed>();
	
	private Object urlLock = new Object();
	
	public CachedRssFeed() {
		super();
	}
	public CachedRssFeed(String url) {
		this();
		this.url = url;
	}
	
	public String getUrl() {
		synchronized (urlLock) {
			return url;
		}
	}
	public void setUrl(String url) {
		synchronized (urlLock) {
			this.url = url;
		}
	}	
	
	private Object feedDataLock = new Object();
	public String getFeedData () {
		synchronized (feedDataLock) {
			return feedData;
		}
	}
	public void setFeedData (String feedData) {
		synchronized (feedDataLock) {
			this.feedData = feedData;
		}
	}

	private Object lastFetchLock = new Object();
	public Date getLastFetch () {
		synchronized (lastFetchLock) {
			return lastFetch;
		}
	}
	public void setLastFetch (Date lastFetch) {
		synchronized (lastFetchLock) {
			this.lastFetch = lastFetch;
		}
	}
	
	private Object aggregatesLock = new Object();
	public Set<AggregatedFeed> getAggregates() {
		synchronized (aggregatesLock) {
			return aggregates;
		}
	}
	public void setAggregates(Set<AggregatedFeed> aggregates) {
		synchronized (aggregatesLock) {
			this.aggregates = aggregates;
		}
	}
	
	/**
	 * 
	 * @param cacheExpiryTime in minutes
	 * @return expired status
	 */
	public boolean isCacheExpired (int cacheExpiryTime) {
		return (new Date().getTime() - getLastFetch().getTime()) > (long)cacheExpiryTime * TimeUnits.MINUTES.value;
	}
	
	public boolean isInUse () {
		return ! aggregates.isEmpty();
	}
}
