<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="label_key" key="module.admin.label.key" />
<fmt:message var="label_language" key="module.admin.label.language" />
<fmt:message var="label_country" key="module.admin.label.country" />
<fmt:message var="label_value" key="module.admin.label.value" />
<fmt:message var="label_submit" key="gen.label.submit" />
<fmt:message var="label_validationFailure" key="gen.label.validationFailure" />

<form method="post">
	<spring:nestedPath path="command">
		<ul class="entitylc">
			<li><app:input field="key" label="${label_key}" /></li>
			<li><app:input field="value" label="${label_value}" /></li>
		</ul>
		<ul class="entityrc">
			<li><app:enumselect field="language" options="${command.languages}" label="${label_language}" /></li>
		</ul>
		<ul class="entityrc">
			<li><app:enumselect field="countryCode" options="${command.countries}" label="${label_country}" /></li>
		</ul>
	</spring:nestedPath>
	
	<spring:hasBindErrors name="command">
		<strong>
			<c:out value="${label_validationFailure}" />
		</strong>
	</spring:hasBindErrors>
	
	<div class="entitytables">
		<input type="submit" value="${label_submit}" />
	</div>
</form>
