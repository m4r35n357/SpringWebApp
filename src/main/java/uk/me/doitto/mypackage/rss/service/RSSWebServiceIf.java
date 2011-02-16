package uk.me.doitto.mypackage.rss.service;

public interface RSSWebServiceIf {
	String aggregateToString (String aggregateName, String ownerId);
	void createAggregatedFeed (String name);
	void destroyAggregatedFeed (String name);
	void addRssFeedToAggregate (String aggregateName, String feedUrl);
	void removeRssFeedFromAggregate (String aggregateName, String feedTitle);
}
