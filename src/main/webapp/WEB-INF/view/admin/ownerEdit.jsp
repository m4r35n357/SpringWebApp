<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<fmt:message var="label_username" key="module.admin.label.username" />
<fmt:message var="label_pass1" key="module.admin.label.pass1" />
<fmt:message var="label_pass2" key="module.admin.label.pass2" />
<fmt:message var="label_accountFlags" key="module.admin.label.accountFlags" />
<fmt:message var="label_enabled" key="module.admin.label.enabled" />
<fmt:message var="label_accountNonExpired" key="module.admin.label.accountNonExpired" />
<fmt:message var="label_accountNonLocked" key="module.admin.label.accountNonLocked" />
<fmt:message var="label_credentialsNonExpired" key="module.admin.label.credentialsNonExpired" />
<fmt:message var="label_modules" key="module.admin.label.modules" />
<fmt:message var="label_role" key="module.admin.label.role" />
<fmt:message var="label_notes" key="gen.label.notes" />
<fmt:message var="label_submit" key="gen.label.submit" />
<fmt:message var="label_validationFailure" key="gen.label.validationFailure" />

<form method="post">
	<spring:nestedPath path="command">
		<ul class="entitylc">
			<li>
				<app:input field="username" label="${label_username}" />
			</li>
			<li>
				<app:input field="pass1" fieldType="password" label="${label_pass1}" />
			</li>
			<li>
				<app:input field="pass2" fieldType="password" label="${label_pass2}" />
			</li>
		</ul>
		<ul class="entityrc">
			<li>
				<strong>
					<c:out value="${label_accountFlags}" />
				</strong>
			</li>
			<li>
				<app:input field="enabled" fieldType="checkbox" label="${label_enabled}" />
			</li>
			<li>
				<app:input field="accountNonExpired" fieldType="checkbox" label="${label_accountNonExpired}" />
			</li>
			<li>
				<app:input field="accountNonLocked" fieldType="checkbox" label="${label_accountNonLocked}" />
			</li>
			<li>
				<app:input field="credentialsNonExpired" fieldType="checkbox" label="${label_credentialsNonExpired}" />
			</li>
		</ul>
		<ul class="entityrc">
			<li>
				<app:enumselect field="selectedModules" options="${command.modules}" current="${command.currentModules}" label="${label_modules}" />
			</li>
		</ul>
		<ul class="entityrc">
			<li>
				<app:enumselect field="role" options="${command.roles}" label="${label_role}" />
			</li>
		</ul>
	</spring:nestedPath>
	
	<spring:hasBindErrors name="command">
		<strong>
			<c:out value="${label_validationFailure}" />
		</strong>
	</spring:hasBindErrors>
	
	<div class="entitytables">
		<ul>
			<li>
				<c:out value="${label_notes}" />
			</li>
			<li>
				<textarea class="textinput" name="notes" rows="12" cols="96"><c:out value="${command.notes}" /></textarea>
			</li>
		</ul>
		<input type="submit" value="${label_submit}" />
	</div>
</form>
