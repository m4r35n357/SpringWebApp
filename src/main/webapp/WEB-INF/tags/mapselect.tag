<%@ tag body-content="scriptless" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="field" required="true" %>
<%@ attribute name="options" required="true" type="java.util.Map" %>
<%@ attribute name="current" required="false" type="java.util.Collection" %>
<%@ attribute name="noOfItems" required="false" %>
<%@ attribute name="multi" required="false" %>
<%@ attribute name="label" required="true" %>

<c:if test="${noOfItems == null}">
 	<c:set var="noOfItems" value="1" />
</c:if>

<c:if test="${multi == null}">
 	<c:set var="multi" value="false" />
</c:if>

<ul class="field">
	<li>
		<c:out value="${label}" />
	</li>
	<li>
		<spring:bind path="${field}">
			<select class="selectbg" name="${status.expression}" <c:if test="${multi == true}">multiple="multiple"</c:if> size="${noOfItems}">
				<c:if test="${noOfItems == 1}">
					<option value="" />
				</c:if>
				<c:forEach var="item" items="${options}">
					<c:set var="displayItem" value="true" />
					<c:forEach var="option" items="${current}">
						<c:if test="${option.name == item.key}">
							<c:set var="displayItem" value="false" />
						</c:if>
					</c:forEach>
					<c:if test="${displayItem == true}">
						<option <c:if test="${item.value == status.value}">selected="selected"</c:if> value="${item.value}">
							<c:out value="${item.key}" />
						</option>
					</c:if>			
				</c:forEach>
			</select>
		</spring:bind>
	</li>
</ul>			
