<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect</title>
</head>

<body>
   <c:set var="age" value= "${param.age}"/>
   <c:choose>
   	<c:when test="${age >=19 }">
   	<c:redirect url="adult.html"/>
   	</c:when>
   	<c:otherwise>
   	<c:redirect url="minor.html"></c:redirect>
   	</c:otherwise>
   </c:choose>
</body>
</html>