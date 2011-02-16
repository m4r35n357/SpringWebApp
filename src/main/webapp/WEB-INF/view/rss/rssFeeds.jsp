<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="publish" key="module.rss.image.feed" />
<fmt:message var="publishFeed" key="module.rss.label.publishFeed" />
<fmt:message var="dateFormat" key="module.rss.entry.dateFormat" />

<div class="scrollingArea">
	<c:forEach var="feed" items="${aggregateList}">
		<c:url var="feedUrl" value="${feed.link}" />
		<app:action url="${feedUrl}" linkText="${publishFeed}" linkImage="${publish}" newWindow="true" />
		<app:action url="rssDisplay" linkText="${feed.title}" p1key="targetUrl" p1value="${feedUrl}" />
		<ul class="rssEntry">
			<c:forEach var="entry" items="${feed.entries}" end="9">
				<li>
					<c:url var="entryUrl" value="${entry.link}" />
					<span class="dateTime1">
						<fmt:formatDate value="${entry.updatedDate}" pattern="${dateFormat}" />
					</span>
					<c:out value=" - " />
					<app:trunc string="${entry.title}" maxLength="80" />
					<app:action url="rssDisplay" linkText="${truncatedString}" p1key="targetUrl" p1value="${entryUrl}" />
				</li>
			</c:forEach>
		</ul>
		<hr />
	</c:forEach>
</div>
