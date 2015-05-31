<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="notice.listPage.title"/></title>
</head>
<body>
<center>
	<h2>< <spring:message code="notice.listPage.title"/> ></h2>

	<table border="1">
		<tr height="40">
			<th><spring:message code="notice.seq"/></th>
			<th><spring:message code="notice.title"/></th>
			<th><spring:message code="notice.userId"/></th>
		</tr>
		<c:forEach var="noticeList" items="${noticeList}">
			<tr height="40">
				<td align="center" width="120"> ${noticeList.seq}</td>
				<td align="center" width="650"> <a href="/notice/${noticeList.seq}">${noticeList.title}</a></td>
				<td align="center" width="120"> ${noticeList.userId}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/notice/form"><spring:message code="notice.listPage.addNotice"/></a>
	<br><br><br>
	<c:forEach var="pageNumber" begin="1" end="${totalPage}">
     	<c:if test="${pageNumber != totalPage}">
     		<a href="/notice/list?page=${pageNumber}&count=${count}">${pageNumber}</a>&nbsp;&nbsp;&nbsp;&nbsp;
     	</c:if>
     	<c:if test="${pageNumber == totalPage}">
			<a href="/notice/list?page=${pageNumber}&count=${count}">${pageNumber}</a>
	    </c:if>
	</c:forEach> 
</center>
</body>
</html>