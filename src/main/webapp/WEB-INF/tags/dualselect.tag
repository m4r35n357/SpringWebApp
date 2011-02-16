<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>

<%@ attribute name="field" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="options" required="true" type="java.util.Map" %>
<%@ attribute name="current" required="false" type="java.util.Collection" %>
<%@ attribute name="noOfItems" required="false" %>
<%@ attribute name="deselect" required="false" %>

<c:if test="${noOfItems == null}">
 	<c:set var="noOfItems" value="10" />
</c:if>

<ul class="field">
	<form method="post" action="${action}">
		<li>
			<select class="selectbg" name="${field}" multiple="multiple" size="${noOfItems}">
				<c:forEach var="item" items="${options}">
					<c:set var="displayItem" value="true" />
					<c:forEach var="i" items="${current}">
						<c:if test="${i.value == item.value}">
							<c:set var="displayItem" value="${!empty deselect}" />
						</c:if>
					</c:forEach>
					<c:if test="${displayItem == true}">
						<option value="${item.value}">
							<c:out value="${item.key}" />
						</option>
					</c:if>
				</c:forEach>
			</select>
		</li>
		<li>
			<c:choose>
				<c:when test="${empty deselect}">
					<c:set var="image" value="<fmt:message key="gen.image.add" />" />
				</c:when>
				<c:otherwise>
					<c:set var="image" value="<fmt:message key="gen.image.remove" />" />
				</c:otherwise>
			</c:choose>
			<input type="image" src="<c:url value="${image}" />" title="${label}" />
		</li>
	</form>
</ul>			
