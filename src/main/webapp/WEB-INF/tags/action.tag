<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<%@ attribute name="url" required="true" %>
<%@ attribute name="linkText" required="true" rtexprvalue="true" %>
<%@ attribute name="linkImage" required="false" %>
<%@ attribute name="newWindow" required="false" %>
<%@ attribute name="p1key" required="false" %>
<%@ attribute name="p1value" required="false" rtexprvalue="true"%>
<%@ attribute name="p2key" required="false" %>
<%@ attribute name="p2value" required="false" rtexprvalue="true"%>
<%@ attribute name="p3key" required="false" %>
<%@ attribute name="p3value" required="false" rtexprvalue="true"%>

<c:if test="${newWindow == null}">
 	<c:set var="newWindow" value="false" />
</c:if>

<%-- Encode parameters into URL --%>
<c:url var="actionUrl" value="${url}">
	<c:param name="${p1key}" value="${p1value}" />
	<c:param name="${p2key}" value="${p2value}" />
	<c:param name="${p3key}" value="${p3value}" />
</c:url>

<%-- Present URL as a hyperlink --%>
<c:choose>
	<%-- Image link, text in tooltip --%>
	<c:when test="${!empty linkImage}">
		<a href="<c:out value="${actionUrl}" />" <c:if test="${newWindow == true}">target="_blank"</c:if> title="<c:out value="${linkText}" />">
			<img src="<c:url value="${linkImage}" />" />
		</a>
	</c:when>
	<%-- Text link --%>
	<c:otherwise>
		<a href="<c:out value="${actionUrl}" />" <c:if test="${newWindow == true}">target="_blank"</c:if>>
			<c:out value="${linkText}" />
		</a>
	</c:otherwise>
</c:choose>
