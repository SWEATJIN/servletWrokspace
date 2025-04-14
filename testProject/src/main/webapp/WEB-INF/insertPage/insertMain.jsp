<!--
   코드블럭 내에서 PostJson 클래스 사용을 위한 클래스 임포트.
-->
<%@page import="com.psy7758.postJson.PostJson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   PostJson.Client client = ((PostJson.Client)request.getAttribute("client"));
%>    
<div><%=client.getName()%></div>
<div><%=client.getClientNumber()%></div>
<div><%=client.getAddress()%></div>
<div><%=client.getPhoneNumber()%></div>

<!-- ======================================================================= -->

<!-- EL 문에서는 별도의 임포트 필요 없음. -->

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> --%>
<!--
   Client 클래스의 getter 메서드들이 매개변수가 없는 형태이므로 EL 에서 각 메서드 접근 형식은
   본래의 메서드 호출 형식이 아닌, 아래와같이 필드 형태로 접근해야함에 주의. 
-->
<%-- <div>${client.name}</div>
<div>${client.clientNumber}</div>
<div>${client.address}</div> --%>