<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="notice.detailPage.title"/></title>
</head>
<body>	
<center>	
	<h2>< <spring:message code="notice.detailPage.title"/> ></h2>
	
	<form name="noticeForm" method="post" action="/notice/save">
		<input type="hidden" name="seq" value="${notice.seq}"/>
		<table>
			<tr>
				<td width="80"><spring:message code="notice.title"/> :</td>
				<td width="400"> <input type="text" name="title" maxlength='80' size='80' value="${notice.title}" /></td>
			</tr>
			<tr>
				<td width="80"><spring:message code="notice.content"/> :</td>
				<td width="400"> <textarea name="content" cols="85" rows="15">${notice.content}</textarea></td>
			</tr>
		</table>
		
		<input type="hidden" name="function" value="${function}"/>
		<button type="submit"><spring:message code="notice.save"/></button>
	</form>
</center>
</body>
</html>