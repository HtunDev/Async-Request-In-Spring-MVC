<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Async Home Page</title>
</head>
<body>
	
	<h1>Async Demo</h1>
	
	<ul>
		<li>
			<c:url value="/async/callable" var="callable"></c:url>
			<a href="${callable}">Callable Result</a>
		</li>
		<li>
			<c:url value="/async/deferred" var="deferred"></c:url>
			<a href="${deferred}">Deferred Result</a>
		</li>
	</ul>

</body>
</html>