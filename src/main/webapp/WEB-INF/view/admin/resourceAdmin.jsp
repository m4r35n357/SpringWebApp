<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="label_actions" key="gen.label.actions" />
<fmt:message var="label_name" key="gen.label.name" />
<fmt:message var="label_language" key="module.admin.label.language" />
<fmt:message var="label_country" key="module.admin.label.country" />
<fmt:message var="label_value" key="module.admin.label.value" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_delete" key="gen.label.delete" />
<fmt:message var="label_newresource" key="module.admin.label.newResource" />
<fmt:message var="delete" key="gen.image.trash" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="add" key="gen.image.add" />

<table>
	<thead align="left">
		<tr>
			<th><c:out value="${label_actions}" /></th>
			<th><c:out value="${label_name}" /></th>
			<th><c:out value="${label_language}" /></th>
			<th><c:out value="${label_country}" /></th>
			<th><c:out value="${label_value}" /></th>
		</tr>
	</thead>
	<tbody class="scrollingArea">
		<c:forEach var="resource" items="${resourceList}">
			<tr>
				<fmt:message var="language" key="enum.Language.${resource.language}" />
				<fmt:message var="country" key="enum.Country.${resource.countryCode}" />
				<td>					
					<app:action url="removeresource" linkText="${label_delete} ${resource.key}-${language}-${country}" linkImage="${delete}" p1key="id" p1value="${resource.id}" />					
					<app:action url="editresource" linkText="${label_edit} ${resource.key}-${language}-${country}" linkImage="${edit}" p1key="id" p1value="${resource.id}" />
				</td>
				<td>
					<c:out value="${resource.key}" />
				</td>
				<td>
					<c:out value="${language}" />
				</td>
				<td>
					<c:out value="${country}" />
				</td>
				<td>
					<app:trunc string="${resource.value}" maxLength="20" />
					<c:out value="${truncatedString}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>
<hr />
<a href="<c:url value="addresource" />">
	<img src="<c:url value="${add}" />" />
	<c:out value="${label_newresource}" />
</a>
<hr />
