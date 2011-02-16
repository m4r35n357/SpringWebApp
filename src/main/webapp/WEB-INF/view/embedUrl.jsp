<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<c:set var="targetUrl" value="${param.targetUrl}" />

<object id="rightobject" data="<c:out value="${targetUrl}" />" type="text/html">
	<c:out value="[ Embedded object - URL: ${targetUrl} ]" />
</object>
