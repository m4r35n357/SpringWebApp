<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="field" required="true" %>
<%@ attribute name="options" required="true" type="java.util.Set" %>
<%@ attribute name="current" required="false" type="java.util.Set" %>
<%@ attribute name="label" required="true" %>

<ul>
	<li>
		<strong>
			<c:out value="${label}" />
		</strong>
	</li>
	<spring:bind path="${field}">
		<c:forEach var="item" items="${options}">
			<c:set var="selected" value="false" />
			<c:choose>
				<c:when test="${current == null}">
					<c:set var="type" value="radio" />
					<c:if test="${status.value == item.code}">
						<c:set var="selected" value="true" />
					</c:if>
				</c:when>
				<c:otherwise>
					<c:set var="type" value="checkbox" />
					<c:forEach var="option" items="${current}">
						<c:if test="${option.code == item.code}">
							<c:set var="selected" value="true" />
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<li>
				<input type="hidden" name="_${status.expression}" />
				<input <c:if test="${selected == true}">checked="checked"</c:if> name="${status.expression}" type="${type}" value="${item.code}" />
				<fmt:message key="enum.${item.class.simpleName}.${item.code}" />
			</li>
		</c:forEach>
	</spring:bind>
</ul>			
