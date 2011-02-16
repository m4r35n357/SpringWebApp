<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="unlink" key="gen.image.unlink" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="dateformat" key="module.mm.dateFormat" />
<fmt:message var="label_title" key="module.mm.label.title" />
<fmt:message var="label_duration" key="module.mm.label.duration" />
<fmt:message var="label_url" key="module.mm.label.url" />
<fmt:message var="label_date" key="module.mm.label.date" />
<fmt:message var="label_linkalbum" key="module.mm.label.linkalbum" />
<fmt:message var="label_linkperformer" key="module.mm.label.linkperformer" />
<fmt:message var="label_albums" key="module.mm.label.albums" />
<fmt:message var="label_performers" key="module.mm.label.performers" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_unlink" key="module.mm.label.unlink" />
<fmt:message var="label_validationFailure" key="gen.label.validationFailure" />

<form method="post">
	<spring:nestedPath path="command">
		<ul class="entitylc">
			<li>
				<app:input field="title" label="${label_title}" />
			</li>
			<li>
				<app:input field="duration" label="${label_duration}" />
			</li>
			<li>
				<app:input field="url" label="${label_url}" />
			</li>
			<li>
				<app:input field="date" label="${label_date}" />
			</li>
		</ul>
		<c:if test="${EditMode}">
			<ul class="entityrc">
				<li>
					<app:mapselect field="linkAlbumIds" current="${command.albums}" options="${AlbumMap}" multi="true" noOfItems="10" label="${label_linkalbum}" />
				</li>
			</ul>
			<ul class="entityrc">
				<li>
					<app:mapselect field="linkPerformerIds" current="${command.performers}" options="${PerformerMap}" multi="true" noOfItems="5" label="${label_linkperformer}" />
				</li>
			</ul>
		</c:if>
	</spring:nestedPath>
	
	<spring:hasBindErrors name="command">
		<strong>
			<c:out value="${label_validationFailure}" />
		</strong>
	</spring:hasBindErrors>
	
	<div class="entitytables">
	
		<input type="submit" value="<fmt:message key="gen.label.submit" />" />
			
		<c:if test="${!empty command.albums}">
			<hr />
			<h4><c:out value="${label_albums}" /></h4>
			<table>
				<c:forEach var="album" items="${command.albums}">
				<tr>    
					<td>
						<app:action url="editalbum" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${album.id}" />
						<app:action url="unlinkalbum" linkText="${label_unlink}" linkImage="${unlink}" p1key="albumId" p1value="${album.id}" p2key="fromTrackId" p2value="${command.id}" />
					</td>				
					<td>
						<c:out value="${album.title}" />
					</td>
					<td>
						<fmt:formatDate value="${album.date}" pattern="${dateformat}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>			
		<c:if test="${!empty command.performers}">
			<hr />
			<h4><c:out value="${label_performers}" /></h4>
			<table>
				<c:forEach var="performer" items="${command.performers}">
				<tr>    
					<td>
						<app:action url="editartist" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${performer.id}" />
						<app:action url="unlinkartist" linkText="${label_unlink}" linkImage="${unlink}" p1key="performerId" p1value="${performer.id}" p2key="fromTrackId" p2value="${command.id}" />
					</td>				
					<td>
						<c:out value="${performer.name}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</form>
