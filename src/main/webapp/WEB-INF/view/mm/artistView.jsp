<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="play" key="module.mm.image.media" />
<fmt:message var="unlink" key="gen.image.unlink" />
<fmt:message var="edit" key="gen.image.edit" />
<fmt:message var="notallowed" key="gen.image.noentry" />
<fmt:message var="dateformat" key="module.mm.dateFormat" />
<fmt:message var="label_name" key="gen.label.name" />
<fmt:message var="label_linkalbum" key="module.mm.label.linkalbum" />
<fmt:message var="label_linkmember" key="module.mm.label.linkmember" />
<fmt:message var="label_linkband" key="module.mm.label.linkband" />
<fmt:message var="label_linkperformance" key="module.mm.label.linkperformance" />
<fmt:message var="label_albums" key="module.mm.label.albums" />
<fmt:message var="label_members" key="module.mm.label.members" />
<fmt:message var="label_bands" key="module.mm.label.bands" />
<fmt:message var="label_performances" key="module.mm.label.performances" />
<fmt:message var="label_edit" key="gen.label.edit" />
<fmt:message var="label_unlink" key="module.mm.label.unlink" />
<fmt:message var="label_play" key="module.mm.label.play" />
<fmt:message var="label_validationFailure" key="gen.label.validationFailure" />

<form method="post">
	<spring:nestedPath path="command">
		<ul class="entitylc">
			<li>
				<app:input field="name" label="${label_name}" />
			</li>
			<c:if test="${EditMode}">
				<li>
					<app:mapselect field="linkAlbumIds" current="${command.albums}" options="${AlbumMap}" multi="true" noOfItems="15" label="${label_linkalbum}" />
				</li>
			</c:if>
		</ul>
		<c:if test="${EditMode}">
			<ul class="entityrc">
				<li>
					<app:mapselect field="linkMemberIds" current="${command.members}" options="${MemberMap}" multi="true" noOfItems="10" label="${label_linkmember}" />
				</li>
				<li>
					<app:mapselect field="linkBandIds" current="${command.bands}" options="${BandMap}" multi="true" noOfItems="10" label="${label_linkband}" />
				</li>
			</ul>
			<ul class="entityrc">
				<li>
					<app:mapselect field="linkPerformanceIds" current="${command.performances}" options="${PerformanceMap}" multi="true" noOfItems="10" label="${label_linkperformance}" />
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
						<app:action url="unlinkalbum" linkText="${label_unlink}" linkImage="${unlink}" p1key="albumId" p1value="${album.id}" p2key="fromArtistId" p2value="${command.id}" />
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
		<c:if test="${!empty command.members}">
			<hr />
			<h4><c:out value="${label_members}" /></h4>
			<table>
				<c:forEach var="member" items="${command.members}">
				<tr>
					<td>
						<app:action url="editartist" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${member.id}" />
						<app:action url="unlinkartist" linkText="${label_unlink}" linkImage="${unlink}" p1key="memberId" p1value="${member.id}" p2key="fromArtistId" p2value="${command.id}" />
					</td>				
					<td>
						<c:out value="${member.name}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>		
		<c:if test="${!empty command.bands}">
			<hr />
			<h4><c:out value="${label_bands}" /></h4>
			<table>
				<c:forEach var="band" items="${command.bands}">
				<tr>
					<td>
						<app:action url="editartist" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${band.id}" />
						<app:action url="unlinkartist" linkText="${label_unlink}" linkImage="${unlink}" p1key="bandId" p1value="${band.id}" p2key="fromArtistId" p2value="${command.id}" />
					</td>				
					<td>
						<c:out value="${band.name}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>	
		<c:if test="${!empty command.performances}">
			<hr />
			<h4><c:out value="${label_performances}" /></h4>
			<table>
				<c:forEach var="performance" items="${command.performances}">
				<tr>
					<td>
						<app:action url="edittrack" linkText="${label_edit}" linkImage="${edit}" p1key="itemId" p1value="${performance.id}" />
						<app:action url="unlinktrack" linkText="${label_unlink}" linkImage="${unlink}" p1key="performanceId" p1value="${performance.id}" p2key="fromArtistId" p2value="${command.id}" />
						<c:choose>
							<c:when test="${!empty performance.url}">
								<app:action url="playMedia" linkText="${label_play}" linkImage="${play}" p1key="url" p1value="${performance.url}" p2key="duration" p2value="${performance.duration}" p3key="title" p3value="${performance.title}" />
							</c:when>
							<c:otherwise>
								<img src="<c:url value="${notallowed}" />" />
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:out value="${performance.title}" />
					</td>
					<td>
						<fmt:formatDate value="${performance.date}" pattern="${dateformat}" />
					</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>	
	</div>
</form>
