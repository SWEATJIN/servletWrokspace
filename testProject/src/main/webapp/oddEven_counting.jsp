<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forTokens</title>
</head>

<body>
   <%
      String staffName = "이순신, 김유신, 홍길동";
   
      pageContext.setAttribute("staffName", staffName);
      String name = "1-2-3-4-5-6-7-8-9-10";
      pageContext.setAttribute("name", name);
      
   %>
   <c:forTokens var="stName" items="${staffName}" delims=", " varStatus="status">
   ${status.count}. 직원명 : ${stName }
   <hr>
   </c:forTokens>
   <c:forTokens var = "name" items="${name}" delims="-" varStatus="stat">
   ${stat.count} 숫자 쪼개기 : ${name}
   <hr>
   </c:forTokens>
      
 
</body>
</html>