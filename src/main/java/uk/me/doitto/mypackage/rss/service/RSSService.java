package uk.me.doitto.mypackage.rss.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.me.doitto.mypackage.admin.service.OwnerServiceIf;
import uk.me.doitto.mypackage.dao.GenericDAOIf;
import uk.me.doitto.mypackage.globals.TimeUnits;
import uk.me.doitto.mypackage.rss.object.AggregatedFeed;
import uk.me.doitto.mypackage.rss.object.CachedRssFeed;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

public class RSSService implements RSSServiceIf,RSSWebServiceIf {
	
	private final Log log = LogFactory.getLog(getClass());
	
	private OwnerServiceIf ownerService;
	public void setOwnerService (OwnerServiceIf ownerService) {
		this.ownerService = ownerService;
	}
	
	private GenericDAOIf<AggregatedFeed> aggregatedFeedDAO;
	public void setAggregatedFeedDAO (GenericDAOIf<AggregatedFeed> aggregatedFeedDAO) {
		this.aggregatedFeedDAO = aggregatedFeedDAO;
	}
	
	private GenericDAOIf<CachedRssFeed> cachedRssFeedDAO;
	public void setCachedRssFeedDAO (GenericDAOIf<CachedRssFeed> cachedRssFeedDAO) {
		this.cachedRssFeedDAO = cachedRssFeedDAO;
	}
	
	private int cacheExpiryTime;
	public void setCacheExpiryTime (int cacheExpiryTime) {
		this.cacheExpiryTime = cacheExpiryTime;
	}
	
	private String feedType;
	public void setFeedType (String feedType) {
		this.feedType = feedType;
	}
	
	/**
	 * If data is cached and not expired, use it
	 * If data has expired or is not cached, go to the network and then cache it
	 * 
	 * @param name feed name
	 * @param url feed url
	 * @return the feed data
	 */
	private SyndFeed loadData (CachedRssFeed feed) {
		log.info(feed.getName() + " ," + feed.getUrl());
		SyndFeed feedData = null;
		if (feed.getFeedData() != null) {
			// use cached data
			log.info("Fetching data for *" + feed.getName() + "* from CACHE");
			try {
				feedData = new SyndFeedInput().build(new StringReader(feed.getFeedData()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (FeedException e) {
				e.printStackTrace();
			}
		} else {
			// go onto net
			log.info("Fetching data for *" + feed.getName() + "* from NET");
			feedData = urlToFeed(feed.getUrl());
			try {
				feed.setFeedData(new SyndFeedOutput().outputString(feedData));
			} catch (Exception e) {
				feed.setFeedData(null);
				e.printStackTrace();
			}
			feed.setLastFetch(new Date());
		}
		return feedData;
	}

	/**
	 * If anything goes wrong, just return null!
	 */
	private SyndFeed urlToFeed (String url) {
		log.info(url);
		SyndFeed syndFeed = null;
		try {
			syndFeed = new SyndFeedInput().build(new XmlReader(new URL(url)));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return syndFeed;
	}

	/**
	 * Publishes an aggregated feed eg. via a hyperlink
	 * 
	 * @param name
	 * @return the feed string
	 */
	public String aggregateToString (String name, String ownerId) {
		log.info(name + " ," + ownerId);
		try {
			return new SyndFeedOutput().outputString(buildSortedRssAggregate(name, ownerId));
		} catch (Exception e) {
			e.printStackTrace();
//			return e.getMessage();
			return null;
		}
	}

	private AggregatedFeed findByNameAndOwner (String name) {
		log.info(name);
		return aggregatedFeedDAO.findByExample(new AggregatedFeed(name, ownerService.getCurrentOwnerId())).get(0);
	}
	
	private CachedRssFeed findFeedByName (String name) {
		log.info(name);
		CachedRssFeed template = new CachedRssFeed();
		template.setName(name);
		return cachedRssFeedDAO.findByExample(template).get(0);
	}
	
	/**
	 * Only creates if named aggregated feed does not already exist
	 */
	public void createAggregatedFeed (String name) {
		log.info(name);
		AggregatedFeed template = new AggregatedFeed(name, ownerService.getCurrentOwnerId());
		if (aggregatedFeedDAO.findByExample(template).isEmpty()) {
			aggregatedFeedDAO.persist(template);
		}
	}
	
	/**
	 * Only destroys if named aggregated feed is empty
	 */
	public void destroyAggregatedFeed (String name) {
		log.info(name);
		AggregatedFeed aggregatedFeed = findByNameAndOwner(name);
		if (! aggregatedFeed.isInUse()) {
			aggregatedFeedDAO.delete(aggregatedFeed);
		}
	}
	
	public void addRssFeedToAggregate (String aggregateName, String feedUrl) {
		log.info(aggregateName + " ," + feedUrl);
		SyndFeed syndFeed = urlToFeed(feedUrl);
		if (syndFeed != null) {
			CachedRssFeed feed = new CachedRssFeed(feedUrl);
			List<CachedRssFeed> results = cachedRssFeedDAO.findByExample(feed);
			if (results.size() == 0) {
				feed.setName(syndFeed.getTitle());
				feed.setLastFetch(new Date(new Date().getTime() - (2 * cacheExpiryTime * TimeUnits.MINUTES.value)));
				cachedRssFeedDAO.persist(feed);
			} else {
				feed = results.get(0);
			}
			findByNameAndOwner(aggregateName).addFeed(feed);
		}
	}
	
	public void removeRssFeedFromAggregate (String name, String title) {
		log.info(name + " ," + title);
		findByNameAndOwner(name).removeFeed(findFeedByName(title));
	}
	
	public void updateCache () {
		for (CachedRssFeed feed : cachedRssFeedDAO.findAll()) {
			if (! feed.isInUse()) {
				log.info("Deleting unused feed: " + feed.getName());
				cachedRssFeedDAO.delete(feed);
			} else {
				if (feed.isCacheExpired(cacheExpiryTime)) {
					log.info("Flushing cached data for: " + feed.getName());
					feed.setFeedData(null);
				}
				loadData(feed);
			}			
		}		
	}
	
	/**
	 * Retrieves the aggregated RSS feed metadata from the database, then populates the aggregate from its online sources,
	 * or the local cache, sorting the entries by date
	 * 
	 * @param name the local name for the RSS feed metadata
	 * @return the populated RSS aggregate
	 */
	@SuppressWarnings("unchecked")
	private SyndFeed buildSortedRssAggregate (String name, String ownerId) {
		log.info(name + " ," + ownerId);
		SyndFeed aggregatedFeed = new SyndFeedImpl();
		aggregatedFeed.setFeedType(feedType);
		aggregatedFeed.setTitle(name);
		aggregatedFeed.setDescription("Aggregated RSS Feed - Name:" + name);
		aggregatedFeed.setAuthor("Various Sources");
		aggregatedFeed.setLink("publishfeed?aggregateName=" + name + "&id=" + ownerId);	
		// Generate an unsorted list of entries by concatenating the constituent feeds	
		List<SyndEntry> unsortedEntries = new ArrayList<SyndEntry>();		
		for (CachedRssFeed feed : aggregatedFeedDAO.findByExample(new AggregatedFeed(name, ownerId)).get(0).getFeeds()) {
			unsortedEntries.addAll(loadData(feed).getEntries());
		}		
		// Copy entries into a sorted (tree) map, with date as key
		SortedMap<Date, SyndEntry> sortedEntries = new TreeMap<Date, SyndEntry>(Collections.reverseOrder());	
		for (SyndEntry entry : unsortedEntries) {
			// Find/define a suitable date for sorting
			Date date;
			if (entry.getUpdatedDate() != null) {
				date = entry.getUpdatedDate();
			} else {
				if (entry.getPublishedDate() != null) {
					date = entry.getPublishedDate();
				} else {
					date = new Date(new Date().getTime() - (48 * TimeUnits.HOURS.value));
				}
				entry.setUpdatedDate(date);
			}
			sortedEntries.put(date, entry);			
		}
		// Populate a new entry list from sorted tree map values
		aggregatedFeed.setEntries(new ArrayList<SyndEntry>(sortedEntries.values()));
	    return aggregatedFeed;
	}
	
	/**
	 * Loads view data for the RSS Feed Display
	 */
	public List<SyndFeed> getAllAggregateData () {
		log.info("");
		List<SyndFeed> aggregateList = new ArrayList<SyndFeed>();
		for (AggregatedFeed feed : getAllAggregateMetaData()) {
			aggregateList.add(buildSortedRssAggregate(feed.getName(), ownerService.getCurrentOwnerId()));
		}
		return aggregateList;
	}
	
	/**
	 * Loads view data for the RSS Editing screen
	 */
	public List<AggregatedFeed> getAllAggregateMetaData () {
		log.info("");
		List<AggregatedFeed> list = aggregatedFeedDAO.findByExample(new AggregatedFeed(ownerService.getCurrentOwnerId()));
		Collections.sort(list);
		return list;
	}
	
	/**
	 * Loads menu data for the RSS Editing screen
	 */
	public Map<String, String> getFeedUrlMap () {
		log.info("");
		SortedMap<String, String> feedUrlMap = new TreeMap<String, String>();
		for (CachedRssFeed feed : cachedRssFeedDAO.findAll()) {
			feedUrlMap.put(feed.getName(), feed.getUrl());
		}
		return feedUrlMap;
	}	
}
