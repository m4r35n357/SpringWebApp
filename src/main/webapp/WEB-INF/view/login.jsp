<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>

<h3><fmt:message key="gen.host" /><c:out value="${inetAddress.canonicalHostName} (${inetAddress.hostAddress})" /></h3>

<c:set var="error" value="${!empty param.login_error}" />

<%-- this form-login-page form is also used as the form-error-page to ask for a login again. --%>
<c:if test="${error == true}">
	<div class="errorMessage">
		<strong><fmt:message key="security.label.tryagain" /></strong>
		<br />
		<strong><fmt:message key="security.label.reason" /><c:out value="${ACEGI_SECURITY_LAST_EXCEPTION}" /></strong>
		<br />
		<br />
	</div>
</c:if>

<form action="<c:url value='/j_spring_security_check' />" method="post">
  <table>
    <tr>
     	<td><fmt:message key="security.label.username" /></td>
     	<td><input class="textinput" type='text' name='j_username' <c:if test="${error == true}">value="${ACEGI_SECURITY_LAST_USERNAME}"</c:if> /></td>
 	</tr>
    <tr>
     	<td><fmt:message key="security.label.password" /></td>
     	<td><input class="textinput" type='password' name='j_password' /></td>
    </tr>
    <tr>
     	<td><input type="checkbox" name="_spring_security_remember_me" /></td>
     	<td><fmt:message key="security.label.rememberme" /></td>
    </tr>
    <tr>
    	<td><input name="reset" class="ibutton" type="reset" /></td>
    	<td><input name="submit" class="ibutton" type="submit" value="<fmt:message key="security.label.login" />" /></td>
    </tr>
  </table>
</form>
