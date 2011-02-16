<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<div id="navigation">
	<ul>
		<li>
			<a href="<c:url value="welcome" />">
				<img src="<c:url value="/images/gohome.png" />" />
				<fmt:message key="nav.home" />
			</a>
		</li>
		<li>
			<a href="<c:url value="/j_spring_security_logout" />">
				<img src="<c:url value="/images/exit.png" />" />
				<fmt:message key="security.label.logout" /><security:authentication property="principal.username"/>
			</a>
		</li>
	</ul>
</div>

<div id="pageInfo">
	<ul>
		<li>
			<a href="http://validator.w3.org/check?uri=referer">
				<img src="http://www.w3.org/Icons/valid-xhtml11" alt="Valid XHTML 1.1" height="31" width="88" />
			</a>
		</li>
		<li>
			<a href="http://jigsaw.w3.org/css-validator/">
				<img style="border:0;width:88px;height:31px" src="http://jigsaw.w3.org/css-validator/images/vcss" alt="Valid CSS!" />
			</a>
		</li>
	</ul>
</div>	

<div id="themeselectors">
	<ul>
		<c:if test="${not (rc.theme.name == 'ics')}">
			<fmt:message key="nav.css.ics" var="name" />
			<fmt:message key="nav.css.ics.info" var="info" />
			<li><app:action url="welcome" linkText="${name} (${info})" p1key="theme" p1value="ics" /></li>		
		</c:if>
		<c:if test="${not (rc.theme.name == 'jnw')}">
			<fmt:message key="nav.css.jnw" var="name" />
			<fmt:message key="nav.css.jnw.info" var="info" />
			<li><app:action url="welcome" linkText="${name} (${info})" p1key="theme" p1value="jnw" /></li>
		</c:if>
		<c:if test="${not (rc.theme.name == 'white')}">
			<fmt:message key="nav.css.white" var="name" />
			<fmt:message key="nav.css.white.info" var="info" />
			<li><app:action url="welcome" linkText="${name} (${info})" p1key="theme" p1value="white" /></li>
		</c:if>
		<c:if test="${not (rc.theme.name == 'none')}">
			<fmt:message key="nav.css.none" var="name" />
			<fmt:message key="nav.css.none.info" var="info" />
			<li><app:action url="welcome" linkText="${name} (${info})" p1key="theme" p1value="none" /></li>
		</c:if>
	</ul>
</div>

<div id="localeselectors">
	<ul>
		<c:if test="${not (rc.locale.language == 'en')}">
			<fmt:message key="img.en" var="img" />
			<fmt:message key="nav.lang.en" var="alt" />
			<fmt:message key="nav.lang.en.info" var="info" />
			<c:url value="welcome" var="url">
				<c:param name="locale" value="en_GB" />
			</c:url>
			<li>
				<a href="<c:url value="${url}" />">
					<img src="<c:url value="/${img}" />" alt="${alt}" />
					<span>${info}</span>
				</a>
			</li>
		</c:if>
		<c:if test="${not (rc.locale.language == 'fr')}">
			<fmt:message key="img.fr" var="img" />
			<fmt:message key="nav.lang.fr" var="alt" />
			<fmt:message key="nav.lang.fr.info" var="info" />
			<c:url value="welcome" var="url">
				<c:param name="locale" value="fr_FR" />
			</c:url>
			<li>
				<a href="<c:url value="${url}" />">
					<img src="<c:url value="/${img}" />" alt="${alt}" />
					<span>${info}</span>
				</a>
			</li>
		</c:if>
		<c:if test="${not (rc.locale.language == 'de')}">
			<fmt:message key="img.de" var="img" />
			<fmt:message key="nav.lang.de" var="alt" />
			<fmt:message key="nav.lang.de.info" var="info" />
			<c:url value="welcome" var="url">
				<c:param name="locale" value="de_GE" />
			</c:url>
			<li>
				<a href="<c:url value="${url}" />">
					<img src="<c:url value="/${img}" />" alt="${alt}" />
					<span>${info}</span>
				</a>
			</li>
		</c:if>
	</ul>
</div>

