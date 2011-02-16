<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<script src='<c:url value="/dwr/interface/JMusicManager.js" />' type="text/javascript"></script>

<fmt:message var="label_delete" key="module.mm.label.delete" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_details" key="module.mm.label.details" />
<fmt:message var="dateformat" key="module.mm.dateFormat" />
<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="delete" key="gen.image.trash" />
<fmt:message var="details" key="gen.image.details" />

<table>
	<thead align="left">
		<tr>
			<th><fmt:message key="module.mm.label.title" /></th>
			<th><fmt:message key="module.mm.label.label" /></th>
			<th><fmt:message key="module.mm.label.catid" /></th>
			<th><fmt:message key="module.mm.label.date" /></th>
			<th><fmt:message key="gen.label.actions" /></th>
		</tr>
	</thead>
	<tbody class="scrollingArea">
		<c:forEach var="album" items="${albumList}">
			<tr>
				<td>
					<app:trunc string="${album.title}" maxLength="30" />
					<c:out value="${truncatedString}" />
				</td>
				<td>
					<c:out value="${album.label}" />
				</td>
				<td>
					<c:out value="${album.catId}" />
				</td>
				<td>
					<fmt:formatDate value="${album.date}" pattern="${dateformat}" />
				</td>
				<td>
					<c:choose>
						<c:when test="${album.linked == false}">
							<app:action url="deletealbum" linkText="${label_delete} ${album.title}" linkImage="${delete}" p1key="itemId" p1value="${album.id}" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value="${notallowed}" />" />
						</c:otherwise>
					</c:choose>
					<app:action url="editalbum" linkText="${label_edit} ${album.title}" linkImage="${edit}" p1key="itemId" p1value="${album.id}" />
					<ajax:dwrcallbacks functionCall='JMusicManager.getAlbumDetails("${album.id}", get${album.id}Callback);' id="${album.id}" />
					<a class="ajaxLink" onclick='toggleDetails${album.id}()' title="${label_details} for <c:out value="${album.title}" />">
						<img src="<c:url value="${details}" />" />
					</a>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<span id='target${album.id}' class='reply'></span>
				</td>			
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>
