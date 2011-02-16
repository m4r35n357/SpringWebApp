<?xml version="1.0" encoding="UTF-8"?>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@ page language="java" contentType="application/xhtml+xml" %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>			
		<script src='<c:url value="/dwr/engine.js" />' type="text/javascript" />
		<script src='<c:url value="/dwr/util.js" />' type="text/javascript" />
		<script src='<c:url value="/js/reverseAjax.js" />' type="text/javascript" />
		<script src='<c:url value="/js/objectEval.js" />' type="text/javascript" />
		<script src='<c:url value="/dwr/interface/JClock.js" />' type="text/javascript" />
		
		<c:set var="css">
			<spring:theme code="styleSheet" />
		</c:set>
		<c:if test="${!empty css}">
			<link rel="stylesheet" href="<c:url value="${css}" />" type="text/css" />
		</c:if>			
		
		<title><tiles:getAsString name="title" /></title>		
	</head>

	<body onload="reverseAjaxInit();">	
		<div id="topRow">
			<tiles:insertAttribute name="header"/>
		</div>
		
		<div id="leftColumn">
			<tiles:insertAttribute name="menu" />
		</div>
		
		<div id="rightColumn">
			<h3><tiles:getAsString name="title" /></h3>
			<tiles:insertAttribute name="content" />
		</div>
		
		<div id="bottomRow">
			<tiles:insertAttribute name="footer" />
		</div>		
	</body>	
</html>