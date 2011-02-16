<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="delete" key="gen.image.trash" />
<fmt:message var="add" key="gen.image.add" />
<fmt:message var="publish" key="module.rss.image.feed" />
<fmt:message var="newAggregate" key="module.rss.label.newAggregate" />
<fmt:message var="newFeed" key="module.rss.label.newFeed" />
<fmt:message var="removeAggregate" key="module.rss.label.removeAggregate" />
<fmt:message var="removeFeed" key="module.rss.label.removeFeed" />
<fmt:message var="publishFeed" key="module.rss.label.publishFeed" />
<fmt:message var="feedUrl" key="module.rss.label.feedUrl" />

<div class="scrollingArea">
	<c:forEach var="aggregate" items="${aggregateList}">
		<c:choose>
			<c:when test="${aggregate.inUse == true}">
				<img src="<c:url value="${notallowed}" />" />
			</c:when>
			<c:otherwise>
				<app:action url="removeaggregate" linkText="${removeAggregate}" linkImage="${delete}" p1key="aggregateName" p1value="${aggregate.name}" />
			</c:otherwise>
		</c:choose>
		<c:out value="${aggregate.name}" />
		<ul>
			<c:forEach var="cachedFeed" items="${aggregate.feeds}">
				<li>
					<app:action url="removefeed" linkText="${removeFeed}" linkImage="${delete}" p1key="aggregateName" p1value="${aggregate.name}" p2key="feedName" p2value="${cachedFeed.name}" />
					<app:action url="${cachedFeed.url}" linkText="${publishFeed}" linkImage="${publish}" newWindow="true" />
					<app:action url="rssDisplay" linkText="${cachedFeed.name}" p1key="targetUrl" p1value="${cachedFeed.url}" />
				</li>
			</c:forEach>
		</ul>
		<form method="post" action="addfeed">
			<input type="hidden" name="aggregateName" value="${aggregate.name}" />			
			<c:out value="${newFeed}" />
			<select class="selectbg" name="selectedUrl">
				<option value="" />
				<c:forEach var="item" items="${feedUrlMap}">
					<c:set var="displayItem" value="true" />
					<c:forEach var="feed" items="${aggregate.feeds}">
						<c:if test="${feed.url == item.value}">
							<c:set var="displayItem" value="false" />
						</c:if>
					</c:forEach>
					<c:if test="${displayItem == true}">
						<option value="${item.value}">
							<c:out value="${item.key}" />
						</option>
					</c:if>
				</c:forEach>
			</select>
			<fmt:message key="module.rss.label.selectOrText" />
			<input class="textinput" type="text" name="feedUrl" />
			<input type="image" src="<c:url value="${add}" />" title="${feedUrl}" />
		</form>
		<hr />
	</c:forEach>
</div>
<hr />
<form method="post" action="addaggregate">
	<c:out value="${newAggregate}" />
	<input class="textinput" type="text" name="aggregateName" />
	<input type="image" src="<c:url value="${add}" />" title="${newAggregate}" />
</form>
