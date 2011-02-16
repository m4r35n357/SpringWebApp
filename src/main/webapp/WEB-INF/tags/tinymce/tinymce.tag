<%@ tag body-content="scriptless" %>

<%@ attribute name="mode" required="false" %>
<%@ attribute name="theme" required="false" %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<c:if test="${mode == null}">
	<c:set var="mode" value="textareas"/>
</c:if>
<c:if test="${theme == null}">
	<c:set var="theme" value="simple"/>
</c:if>

<script language="javascript" type="text/javascript" src='<c:url value="/js/tinymce/jscripts/tiny_mce/tiny_mce.js" />'></script>
<script language="javascript" type="text/javascript">
	tinyMCE.init({
		mode : "${mode}",
		theme : "${theme}"
	});
</script>
