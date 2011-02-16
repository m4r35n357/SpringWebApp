<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="string" required="true" %>
<%@ attribute name="maxLength" required="true" %>

<%@ variable name-given="truncatedString" scope="AT_END" %>

<c:choose>
	<c:when test="${fn:length(string) gt maxLength}">
		<c:set var="truncatedString" value="${fn:substring(string, 0, maxLength)}..." />
	</c:when>
	<c:otherwise>
		<c:set var="truncatedString" value="${string}" />
	</c:otherwise>
</c:choose>
