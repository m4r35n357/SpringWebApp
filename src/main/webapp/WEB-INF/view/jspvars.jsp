<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<div class="scrollingArea">
	<h3>Request Parameters</h3>
	<code>
		<c:forEach var="aParam" items="${paramValues}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>values: </strong>
			<c:forEach var="aValue" items="${aParam.value}">
				<c:out value="${aValue}" />
			</c:forEach>
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Request Scope</h3>
	<code>
		<c:forEach var="aParam" items="${requestScope}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>value: </strong><c:out value="${aParam.value}" />
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Page Scope</h3>
	<code>
		<c:forEach var="aParam" items="${pageScope}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>value: </strong><c:out value="${aParam.value}" />
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Session Scope</h3>
	<code>
		<c:forEach var="aParam" items="${sessionScope}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>value: </strong><c:out value="${aParam.value}" />
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Application Scope</h3>
	<code>
		<c:forEach var="aParam" items="${applicationScope}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>value: </strong><c:out value="${aParam.value}" />
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Init Parameters</h3>
	<code>
		<c:forEach var="aParam" items="${initParam}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>value: </strong><c:out value="${aParam.value}" />
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Cookies</h3>
	<code>
		<c:forEach var="aParam" items="${cookie}">
			<strong>param: </strong><c:out value="${aParam.key}" />
			<br />
			<strong>value: </strong><c:out value="${aParam.value}" />
			<br /><br />
		</c:forEach>
	</code>
	<hr />
	<h3>Headers</h3>
	<code>
		<c:forEach var="aHeader" items="${headerValues}">
			<strong>param: </strong><c:out value="${aHeader.key}" />
			<br />
			<strong>values: </strong>
			<c:forEach var="aValue" items="${aHeader.value}">
				<c:out value="${aValue}" />
			</c:forEach>
			<br /><br />
		</c:forEach>
	</code>
	<hr />
</div>