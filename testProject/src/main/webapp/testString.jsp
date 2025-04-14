<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTML 이스케이핑 - fn:escapeXml()</title>
</head>
<body>
   <div>escapeXml 함수 : ${fn:escapeXml(param.input)}</div>
   
</body>
</html>