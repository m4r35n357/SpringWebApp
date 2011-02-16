<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<script src='<c:url value="/dwr/interface/JMusicManager.js" />' type="text/javascript"></script>

<fmt:message var="label_actions" key="gen.label.actions" />
<fmt:message var="label_name" key="gen.label.name" />
<fmt:message var="label_delete" key="module.mm.label.delete" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_details" key="module.mm.label.details" />
<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="delete" key="gen.image.trash" />
<fmt:message var="details" key="gen.image.details" />

<table>
	<thead align="left">
	    <tr>
			<th><c:out value="${label_name}" /></th>
			<th><c:out value="${label_actions}" /></th>
	    </tr>
	</thead>
	<tbody class="scrollingArea">
		<c:forEach var="artist" items="${artistList}">
		    <tr>
				<td>
					<c:out value="${artist.name}" />
				</td>							
				<td>
					<c:choose>
						<c:when test="${artist.linked == false}">
							<app:action url="deleteartist" linkText="${label_delete} ${artist.name}" linkImage="${delete}" p1key="itemId" p1value="${artist.id}" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value="${notallowed}" />" />
						</c:otherwise>
					</c:choose>
					<app:action url="editartist" linkText="${label_edit} ${artist.name}" linkImage="${edit}" p1key="itemId" p1value="${artist.id}" />
					<ajax:dwrcallbacks functionCall='JMusicManager.getArtistDetails("${artist.id}", get${artist.id}Callback);' id="${artist.id}" />
					<a class="ajaxLink" onclick='toggleDetails${artist.id}()' title="${label_details} for <c:out value="${artist.name}" />">
						<img src="<c:url value="${details}" />" />
					</a>
				</td>
		    </tr>
			<tr>
				<td colspan="3">
					<span id='target${artist.id}' class='reply'></span>
				</td>			
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>
