<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="field" required="true" %>
<%@ attribute name="fieldType" required="false" %>
<%@ attribute name="label" required="true" %>

<c:if test="${fieldType == null}">
	<c:set var="fieldType" value="text" />
</c:if>

<spring:bind path="${field}">
	<c:if test="${status.error}">
		<span class="errorMessage">
			<strong>
				<c:out value="${status.errorMessage}" />
			</strong>
		</span>
	</c:if>
	<c:choose>
		<c:when test="${fieldType == 'checkbox' || fieldType == 'radio'}">
			<input type="hidden" name="_${status.expression}" />					
			<input type="${fieldType}" name="${status.expression}" value="true" <c:if test="${status.value}">checked="checked"</c:if> />
			<c:out value="${label}" />
		</c:when>
		<c:otherwise>
			<ul>				
				<li>
					<c:out value="${label}" />
				</li>
				<li>
					<input class="textinput" type="${fieldType}" name="${status.expression}" value="<c:out value="${status.value}" />" />
				</li>
			</ul>			
		</c:otherwise>
	</c:choose>
</spring:bind>
