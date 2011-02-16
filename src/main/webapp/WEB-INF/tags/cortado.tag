<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<%@ attribute name="url" required="true" %>
<%@ attribute name="video" required="false" %>
<%@ attribute name="duration" required="false" %>

<c:if test="${video == null}">
	<c:set var="video" value="false"/>
</c:if>

<c:if test="${duration == null}">
	<c:set var="duration" value="300"/>
</c:if>

<object classid="java:com.fluendo.player.Cortado.class"
		archive="<c:url value="/applet/cortado.jar" />"
		width="512"
		<c:choose>
			<c:when test="${video == true}">height="288"</c:when>
			<c:otherwise>height="12"</c:otherwise>
		</c:choose>
		type="application/x-java-applet">
	<param name="url" value="${url}" />
	<param name="live" value="false" />
	<param name="autoPlay" value="true" />
	<param name="showStatus" value="auto" />
	<param name="audio" value="true" />
	<param name="video" value="${video}" />
	<c:choose>
		<c:when test="${video == true}">
			<param name="keepAspect" value="true" />
			<param name="bufferSize" value="256" />
		</c:when>
		<c:otherwise>
			<param name="bufferSize" value="128" />
		</c:otherwise>
	</c:choose>
	<c:if test="${duration != null}">
		<param name="duration" value="${duration}" />
		<param name="seekable" value="true" />
	</c:if>
	your browser does not appear to support the object tag
</object>
