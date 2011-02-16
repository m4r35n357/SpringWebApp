<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>

<li><a href="<c:url value="listalbums" />"><fmt:message key="module.mm.menu.listalbums" /></a></li>
<li><a href="<c:url value="addalbum" />"><fmt:message key="module.mm.menu.newalbum" /></a></li>
<li><a href="<c:url value="listtracks" />"><fmt:message key="module.mm.menu.listtracks" /></a></li>
<li><a href="<c:url value="addtrack" />"><fmt:message key="module.mm.menu.newtrack" /></a></li>
<li><a href="<c:url value="listartists" />"><fmt:message key="module.mm.menu.listartists" /></a></li>
<li><a href="<c:url value="addartist" />"><fmt:message key="module.mm.menu.newartist" /></a></li>
