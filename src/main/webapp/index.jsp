<%-- 
	Only included because the Servlet API doesn't allow us
	to set the welcome page in web.xml to a virtual URL.
	We set the welcome page in web.xml to be this page as follows:
	
	<welcome-file-list>
	 	<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>

format: /servlet-name/bean
<jsp:forward page="/mvc/welcome" />
<jsp:forward page="/mvc/login" />
--%>

<jsp:forward page="/mvc/welcome" />
