<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="banner">
	<a href="<c:url value="welcome" />"><fmt:message key="gen.title" /></a> :: Welcome !
</div>

<div id="clock">
	<security:authorize ifAnyGranted="ROLE_USER,ROLE_SUPERVISOR">
		<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}" />
		<c:if test="${user.class.simpleName == 'Owner'}">
			<c:set var="enabled" value="false" />
			<c:forEach var="module" items="${user.currentModules}">
				<c:if test="${module.code == 'CLOCK'}">
					<c:set var="enabled" value="true" />
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${enabled == true}">
			<span id="clockDisplay" class="reply"></span>
			<input type="image" src="<c:url value="/images/appointment.png" />" onclick="JClock.toggleServiceWanted();" title="<fmt:message key="module.clock.toggleUser" />" />
		</c:if>
	</security:authorize>
	<security:authorize ifAllGranted="ROLE_SUPERVISOR">
		<input type="image" src="<c:url value="/images/stock_media-rec.png" />" onclick="JClock.toggle();" title="<fmt:message key="module.clock.toggleThread" />" />
	</security:authorize>
</div>
