<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:cortado url="${param.url}" video="true" duration="${param.duration}" />
<p>
<c:out value="${param.title}" />
</p>

