<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="label_actions" key="gen.label.actions" />
<fmt:message var="label_name" key="gen.label.name" />
<fmt:message var="label_notes" key="gen.label.notes" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_delete" key="gen.label.delete" />
<fmt:message var="label_toggle_enabled" key="gen.label.toggle.enabled" />
<fmt:message var="label_toggle_disabled" key="gen.label.toggle.disabled" />
<fmt:message var="label_newuser" key="module.admin.label.newUser" />
<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="enabled" key="gen.image.add" />
<fmt:message var="disabled" key="gen.image.remove" />
<fmt:message var="delete" key="gen.image.trash" />
<fmt:message var="edit" key="gen.image.edit" />

<table>
	<thead align="left">
		<tr>
			<th><c:out value="${label_actions}" /></th>
			<th><c:out value="${label_name}" /></th>
			<th><c:out value="${label_notes}" /></th>
		</tr>
	</thead>
	<tbody class="scrollingArea">
		<c:forEach var="owner" items="${ownerList}">
			<tr>
				<td>
					<c:choose>
						<c:when test="${owner.enabled == true}">
							<img src="<c:url value="${notallowed}" />" />
							<app:action url="toggleownerenabled" linkText="${owner.username} ${label_toggle_enabled}" linkImage="${enabled}" p1key="id" p1value="${owner.id}" />
						</c:when>
						<c:otherwise>
							<app:action url="removeowner" linkText="${label_delete} ${owner.username}" linkImage="${delete}" p1key="id" p1value="${owner.id}" />
							<app:action url="toggleownerenabled" linkText="${owner.username} ${label_toggle_disabled}" linkImage="${disabled}" p1key="id" p1value="${owner.id}" />
						</c:otherwise>
					</c:choose>
					<app:action url="editowner" linkText="${label_edit} ${owner.username}" linkImage="${edit}" p1key="id" p1value="${owner.id}" />
				</td>
				<td>
					<fmt:message var="role" key="enum.SecurityRole.image.${owner.role}" />
					<img src="<c:url value="${role}" />" />
					<c:out value="${owner.username}" />
				</td>
				<td>
					<app:trunc string="${owner.notes}" maxLength="30" />
					<c:out value="${truncatedString}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>
<hr />
<a href="<c:url value="addowner" />">
	<img src="<c:url value="${enabled}" />" />
	<c:out value="${label_newuser}" />
</a>
<hr />
