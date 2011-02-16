<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="ajax" tagdir="/WEB-INF/tags/ajax" %>

<script src='<c:url value="/dwr/interface/JTest.js" />' type="text/javascript" />

<ul>
	<li>
	Test Web Service
	</li>
	<li>
		<ajax:dwrcallbacks id="hello" functionCall='JTest.hello(gethelloCallback);' />
		<input class='ibutton' type='button' onclick='toggleDetailshello()' value='Say Hello'/>
		<span id='targethello' class='reply'></span>
	</li>
	
	<li>
		<ajax:dwrcallbacks id="echo" functionCall='JTest.echo(objectEval($("echoInput").value), getechoCallback);' />
		<input class='itext' type='text' size='10' id='echoInput' value='"TEST"' />		
		<input class='ibutton' type='button' onclick='toggleDetailsecho()' value='Echo'/>
		<span id='targetecho' class='reply'></span>
	</li>
</ul>
