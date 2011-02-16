package uk.me.doitto.mypackage.rss.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.rss.service.RSSServiceIf;

public class RSSController implements ThrowawayController {
	
	private RSSServiceIf rssService;
	public void setRssService (RSSServiceIf rssService) {
		this.rssService = rssService;
	}
	
	private String actionType;
	public void setActionType (String actionType) {
		this.actionType = actionType;
	}
	
	private String aggregateName;
	public void setAggregateName (String aggregateName) {
		this.aggregateName = aggregateName;
	}
	
	private String id;
	public void setId (String id) {
		this.id = id;
	}
	
	private String feedName;
	public void setFeedName (String feedName) {
		this.feedName = feedName;
	}

	private String feedUrl;	
	public void setFeedUrl (String feedUrl) {
		this.feedUrl = feedUrl;
	}

	private String selectedUrl;
	public void setSelectedUrl (String selectedUrl) {
		this.selectedUrl = selectedUrl;
	}
	
	private ModelAndView buildEditView () {
		ModelAndView modelAndView = new ModelAndView("editFeedsView");
		modelAndView.addObject("aggregateList", rssService.getAllAggregateMetaData());
		modelAndView.addObject("feedUrlMap", rssService.getFeedUrlMap());
		return modelAndView;
	}
	
	public ModelAndView execute () {
		if (actionType.equals("displayFeeds")) {
			return new ModelAndView("listFeedsView", "aggregateList", rssService.getAllAggregateData());
		} else  if (actionType.equals("publishFeed")) {
			return new ModelAndView("renderFeedView", "feedString", rssService.aggregateToString(aggregateName, id));
		} else  if (actionType.equals("editFeeds")) {
		} else  if (actionType.equals("addFeed")) {
			if (! feedUrl.equals("")) {
				rssService.addRssFeedToAggregate(aggregateName, feedUrl);
			} else if (! selectedUrl.equals("")) {
				rssService.addRssFeedToAggregate(aggregateName, selectedUrl);
			}
		} else  if (actionType.equals("removeFeed")) {
			rssService.removeRssFeedFromAggregate(aggregateName, feedName);
		} else  if (actionType.equals("addAggregate")) {
			rssService.createAggregatedFeed(aggregateName);
		} else  if (actionType.equals("removeAggregate")) {
			rssService.destroyAggregatedFeed(aggregateName);
		} else {
			return new ModelAndView("error");
		}
		return buildEditView();
	}
}
