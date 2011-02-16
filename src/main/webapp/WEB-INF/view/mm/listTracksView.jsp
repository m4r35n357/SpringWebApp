<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<script src='<c:url value="/dwr/interface/JMusicManager.js" />' type="text/javascript"></script>

<fmt:message var="label_actions" key="gen.label.actions" />
<fmt:message var="label_title" key="module.mm.label.title" />
<fmt:message var="label_date" key="module.mm.label.date" />
<fmt:message var="label_delete" key="module.mm.label.delete" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_play" key="module.mm.label.play" />
<fmt:message var="label_details" key="module.mm.label.details" />
<fmt:message var="dateformat" key="module.mm.dateFormat" />
<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="delete" key="gen.image.trash" />
<fmt:message var="details" key="gen.image.details" />
<fmt:message var="play" key="module.mm.image.media" />

<table>
	<thead align="left">
	    <tr>
			<th><c:out value="${label_title}" /></th>
			<th><c:out value="${label_date}" /></th>
			<th><c:out value="${label_actions}" /></th>
	    </tr>
	</thead>
	<tbody class="scrollingArea">
	  	<c:forEach var="track" items="${trackList}">
		    <tr>
				<td>
					<c:out value="${track.title}" />
				</td>
				<td>
					<fmt:formatDate value="${track.date}" pattern="${dateformat}" />
				</td>				
				<td>
					<c:choose>
						<c:when test="${track.linked == false}">
							<app:action url="deletetrack" linkText="${label_delete} ${track.title}" linkImage="${delete}" p1key="itemId" p1value="${track.id}" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value="${notallowed}" />" />
						</c:otherwise>
					</c:choose>
					<app:action url="edittrack" linkText="${label_edit} ${track.title}" linkImage="${edit}" p1key="itemId" p1value="${track.id}" />
					<c:choose>
						<c:when test="${!empty track.url}">
						<app:action url="playMedia" linkText="${label_play} ${track.title}" linkImage="${play}" p1key="url" p1value="${track.url}" p2key="duration" p2value="${track.duration}" p3key="title" p3value="${track.title}" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value="${notallowed}" />" />
						</c:otherwise>
					</c:choose>
					<ajax:dwrcallbacks functionCall='JMusicManager.getTrackDetails("${track.id}", get${track.id}Callback);' id="${track.id}" />
					<a class="ajaxLink" onclick='toggleDetails${track.id}()' title="${label_details} for <c:out value="${track.title}" />">
						<img src="<c:url value="${details}" />" />
					</a>
				</td>
		    </tr>
			<tr>
				<td colspan="4">
					<span id='target${track.id}' class='reply'></span>
				</td>			
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	</tfoot>
</table>
