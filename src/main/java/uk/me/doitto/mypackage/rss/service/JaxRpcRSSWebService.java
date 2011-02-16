package uk.me.doitto.mypackage.rss.service;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

public class JaxRpcRSSWebService extends ServletEndpointSupport implements RSSWebServiceIf {
	
	// don't try to inject this - server.wsdd is not a Spring config file !
	private RSSWebServiceIf rssService;
	// get a reference directly from the application context instead . . .
    protected void onInit () {
        this.rssService = (RSSWebServiceIf)getApplicationContext().getBean("rssService");
    }

	public String aggregateToString(String aggregateName, String ownerId) {
		return rssService.aggregateToString(aggregateName, ownerId);
	}

	public void createAggregatedFeed(String name) {
		rssService.createAggregatedFeed(name);
	}

	public void destroyAggregatedFeed(String name) {
		rssService.destroyAggregatedFeed(name);
	}

	public void addRssFeedToAggregate(String aggregateName, String feedUrl) {
		rssService.addRssFeedToAggregate(aggregateName, feedUrl);
	}

	public void removeRssFeedFromAggregate(String aggregateName, String feedTitle) {
		rssService.removeRssFeedFromAggregate(aggregateName, feedTitle);
	}
}
