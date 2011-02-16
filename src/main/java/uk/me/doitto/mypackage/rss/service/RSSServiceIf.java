package uk.me.doitto.mypackage.rss.service;

import java.util.List;
import java.util.Map;

import uk.me.doitto.mypackage.rss.object.AggregatedFeed;

import com.sun.syndication.feed.synd.SyndFeed;

public interface RSSServiceIf {
	// for publish action
	String aggregateToString (String aggregateName, String ownerId);
	// for editing actions
	void createAggregatedFeed (String name);
	void destroyAggregatedFeed (String name);
	void addRssFeedToAggregate (String aggregateName, String feedUrl);
	void removeRssFeedFromAggregate (String aggregateName, String feedTitle);
	void updateCache ();
	// for building views
	List<SyndFeed> getAllAggregateData ();
	List<AggregatedFeed> getAllAggregateMetaData ();
	Map<String, String> getFeedUrlMap ();
}
