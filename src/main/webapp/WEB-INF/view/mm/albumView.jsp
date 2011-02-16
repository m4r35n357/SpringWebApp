<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="play" key="module.mm.image.media" />
<fmt:message var="unlink" key="gen.image.unlink" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="dateformat" key="module.mm.dateFormat" />
<fmt:message var="label_title" key="module.mm.label.title" />
<fmt:message var="label_label" key="module.mm.label.label" />
<fmt:message var="label_catid" key="module.mm.label.catid" />
<fmt:message var="label_date" key="module.mm.label.date" />
<fmt:message var="label_linkartist" key="module.mm.label.linkartist" />
<fmt:message var="label_linktrack" key="module.mm.label.linktrack" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_unlink" key="module.mm.label.unlink" />
<fmt:message var="label_play" key="module.mm.label.play" />
<fmt:message var="label_validationFailure" key="gen.label.validationFailure" />

<form method="post">
	<spring:nestedPath path="command">
		<ul class="entitylc">
			<li>
				<app:input field="title" label="${label_title}" />
			</li>
			<li>
				<app:input field="label" label="${label_label}" />
			</li>
			<li>
				<app:input field="catId" label="${label_catid}" />
			</li>
			<li>
				<app:input field="date" label="${label_date}" />
			</li>
		</ul>
		<c:if test="${EditMode}">
			<ul class="entityrc">
				<li>
					<app:mapselect field="linkArtistIds" current="${command.artists}" options="${ArtistMap}" multi="true" noOfItems="10" label="${label_linkartist}" />
				</li>
			</ul>
			<ul class="entityrc">
				<li>
					<app:mapselect field="linkTrackIds" current="${command.tracks}" options="${TrackMap}" multi="true" noOfItems="10" label="${label_linktrack}" />
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
	
		<c:if test="${!empty command.artists}">
			<hr />
			<h4><fmt:message key="module.mm.label.artists" /></h4>
			<table>
				<c:forEach var="artist" items="${command.artists}">
				<tr>
					<td>
						<app:action url="editartist" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${artist.id}" />
						<app:action url="unlinkartist" linkText="${label_unlink}" linkImage="${unlink}" p1key="artistId" p1value="${artist.id}" p2key="fromAlbumId" p2value="${command.id}" />
					</td>				
					<td>
						<c:out value="${artist.name}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>	
		<c:if test="${!empty command.tracks}">
			<hr />
			<h4><fmt:message key="module.mm.label.tracks" /></h4>
			<table>
				<c:forEach var="track" items="${command.tracks}">
				<tr>
					<td>
						<app:action url="edittrack" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${track.id}" />
						<app:action url="unlinktrack" linkText="${label_unlink}" linkImage="${unlink}" p1key="trackId" p1value="${track.id}" p2key="fromAlbumId" p2value="${command.id}" />
						<c:choose>
							<c:when test="${!empty track.url}">
								<app:action url="playMedia" linkText="${label_play}" linkImage="${play}" p1key="url" p1value="${track.url}" p2key="duration" p2value="${track.duration}" p3key="title" p3value="${track.title}" />
							</c:when>
							<c:otherwise>
								<img src="<c:url value="${notallowed}" />" />
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:out value="${track.title}" />
					</td>
					<td>
						<fmt:formatDate value="${track.date}" pattern="${dateformat}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</form>
