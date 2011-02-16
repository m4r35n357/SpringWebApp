<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="javadoc" key="gen.menu.javadoc" />
<fmt:message var="licence" key="gen.menu.licence" />
<fmt:message var="testwsdl" key="gen.menu.testwsdl" />
<fmt:message var="ajaxdebug" key="gen.menu.ajaxdebug" />

<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:if test="${user.class.simpleName == 'Owner'}">
	<c:set var="modules" value="${user.currentModules}" />
</c:if>

<security:authorize ifAnyGranted="ROLE_USER,ROLE_SUPERVISOR">
	<c:forEach var="module" items="${modules}">
		<c:if test="${module.code != 'CLOCK'}">
			<ul class="menu">
				<jsp:include page="${fn:toLowerCase(module.code)}/menuitems.jsp" />
			</ul>
		</c:if>
	</c:forEach>
	<ul class="menu">
		<c:url var="pageUrl" value="/doc/index.html" />
		<li><app:action url="apiDisplay" linkText="${javadoc}" p1key="targetUrl" p1value="${pageUrl}" /></li>
		<c:url var="pageUrl" value="/LICENCE" />
		<li><app:action url="licenceDisplay" linkText="${licence}" p1key="targetUrl" p1value="${pageUrl}" /></li>
		<li><a href="<c:url value="jspvars" />"><fmt:message key="gen.menu.jspenv" /></a></li>
	</ul>
</security:authorize>
	
<security:authorize ifAllGranted="ROLE_SUPERVISOR">
	<ul class="menu">
		<c:forEach var="module" items="${modules}">
			<c:url var="pageUrl" value="/axis/${module.code}WebService?wsdl" />
			<li><app:action url="wsdlDisplay" linkText="WSDL (${fn:toLowerCase(module.code)})" p1key="targetUrl" p1value="${pageUrl}" /></li>
		</c:forEach>
		<c:url var="pageUrl" value="/axis/TESTWebService?wsdl" />
		<li><app:action url="wsdlDisplay" linkText="${testwsdl}" p1key="targetUrl" p1value="${pageUrl}" /></li>
	</ul>
	<ul class="menu">
		<c:url var="pageUrl" value="/dwr" />
		<li><app:action url="ajaxDisplay" linkText="${ajaxdebug}" p1key="targetUrl" p1value="${pageUrl}" /></li>
		<li><a href="<c:url value="ajaxTest" />"><fmt:message key="gen.menu.ajaxtest" /></a></li>
	</ul>	
	<ul class="menu">
		<li><a href="<c:url value="editowners" />"><fmt:message key="gen.menu.editusers" /></a></li>
		<li><a href="<c:url value="editresources" />"><fmt:message key="gen.menu.editresources" /></a></li>
	</ul>
</security:authorize>
