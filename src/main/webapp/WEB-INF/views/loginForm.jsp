<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="notice.loginPage.title"/></title>
</head>
<body>
<center>
	<h2>< <spring:message code="notice.loginPage.title"/> ></h2>
	<form name="loginForm" method="post" action="/notice/login/submit">
		<spring:message code="user.id"/>   : <input type="text" name="id"><br>
		<spring:message code="user.passwd"/> : <input type="password" name="passwd"><br>
		<input type="submit" value="<spring:message code="notice.loginPage.title"/>">
	</form>
</center>
</body>
</html>