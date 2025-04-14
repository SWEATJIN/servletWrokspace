<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formatting tags - formatNumber</title>
</head>

<body>
   <c:set var="date" value="<%=new java.util.Date() %>"/>
   
   <div>
   	(1) ${date} <br>
   	(2) ${date.time} <br>
   	(3) ${date.year} <br>
   	(4) ${date.month} <br>
   	(5) ${date.date} <br>
   	(6) ${date.hours} <br>
   	(7) ${date.minutes} <br>
   	(8) ${date.seconds} <br>
   	(10) ${date.day} <br>
   	(11) ${date.toLocaleString()} <br>
   	(12) ${date.toGMTString()} <br>
   	(13) ${date.toString()} <br>
   	(15) ${date.getTime()} <br>
   	(16) ${date.getTimezoneOffset()} <br>
   	(17) ${date.getYear()} <br>
   	(18) ${date.getMonth()} <br>
   	(19) ${date.getDate()} <br>
   	(20) ${date.getHours()} <br>
   	(21) ${date.getMinutes()} <br>
   	(22) ${date.getSeconds()} <br>
   </div>
</body>
</html>