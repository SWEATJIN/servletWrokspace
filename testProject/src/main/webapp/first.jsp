<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
      < 페이지 지시자 >
      
- 상기 코드는 페이지 지시자로써 코드블럭태그(<%@ %>)로 둘러쌈으로서 문서의 컨텐트 타입과 
  인코딩 형식등을 지정.
  pageEncoding 속성은 서블릿에서의 setCharacterEncoding 메서드에 대응되고
  contentType 속성은 서블릿에서의 setContentType 메서드에 대응되는 역할을 담당하는데
  JSP 에서는 setCharacterEncoding 과 setContentType 메서드를 직접 적용하면,
  예외가 발생되므로 반드시 위와 같이 페이지 지시자를 이용.
  그 외에도 페이지 지시자는 외부 라이브러리를 포함하는 import 문 적용시에도 활용.
--%>

<!-- -------------------------------------------------------------- -->

<!--
   아래 내용은 단순히 문서 형태로 맵핑되어 출력되는 것을 브라우저 페이지 화면과
   변환된 서블릿 파일 내의 _jspService 메서드 내에서 확인 가능.
-->
int n1 = 5;
int n2 = 10;
ㄴㄴ
out.print(n1 + n2);

<!-- -------------------------------------------------------------- -->

   <!--
   
      <%-- --%> : JSP 에서 사용되는 주석으로 HTML 주석과 달리 브라우저에 전송되는
               HTML 소스   코드에 포함되지 않음.  
   -->   

<!-- -------------------------------------------------------------- -->

<%--
         < 스크립트릿(scriptlet) >

   코드블럭태그(<% %>)로 둘러쌈으로서 변환된 서블릿 파일 내의 _jspService 메서드의
   지역 변수로, 그대로 삽입되어 실행되는 것을 확인 가능.
--%>
<%
   int n1 = 9;
   int n2 = 6;
   
   out.print("<br/>" + (n1 + n2) + "<br/>");
%>

<!-- -------------------------------------------------------------- -->

<%--
   우변에 적용 가능한 표현식 코드블럭태그(<%= %>)로 둘러쌈으로서 변환된 서블릿 파일 내의
   _jspService 메서드의 지역적 표현식 형태로 적용된 값을 페이지 출력코드로 바로 변환된
   것을 확인 가능.
--%>
<%=n1 + n2%>

<!-- -------------------------------------------------------------- -->

<%--
   선언문 형식의 코드블럭태그(<%! %>)로 둘러쌈으로서_jspService 메서드의 지역적 변수나
   코드가 아닌, 변환된 서블릿 클래스의 멤버 필드나 메서드를 정의 가능.
   아래 선언으로 실제 first_jsp 클래스 내에 메서드가 정의된 것을 확인 가능.
--%>
<%!
   private int sum(int n1, int n2){
      return n1 + n2;
   }
%>
<br/>
<%=sum(10,20)%>

<!-- -------------------------------------------------------------- -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 파일 코드에서 서블릿 파일 코드로의 변환 맵핑 구조와 코드블럭</title>
</head>
<body>
   <h1>JSP 파일 코드에서 서블릿 파일 코드로의 변환 맵핑 구조와 코드블럭</h1>
</body>
</html>