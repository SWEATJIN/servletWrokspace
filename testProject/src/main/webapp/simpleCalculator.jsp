<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   Cookie[] cookies = request.getCookies();
   String num_ = request.getParameter("num");
   String tot = "0";
   
   if( cookies == null ) {
      if( !num_.isEmpty() ) {      // isEmpty 메서드를 활용 빈 문자열 체크 대체.
         tot = num_;
      }
   } else {
      String tot_ = null;
      
      for(Cookie c: cookies) {
         if(c.getName().equals("tot")) {
            tot_ = c.getValue();
            
            break;
         }
      }
      
      if( num_.isEmpty() ) {
         tot = tot_;
      } else {
         tot = Integer.parseInt(tot_) + Integer.parseInt(num_) + "";
      }
   }
   
   response.addCookie(new Cookie("tot", tot));
%>

<!-- -------------------------------------------------------------- -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SimpleCalculator.java 를 JSP 로 전환</title>
</head>
<body>
   <h1>SimpleCalculator.java 를 JSP 로 전환</h1>
   <hr>
   
   <!--
      자기 자신 페이지(simpleCalculator.jsp)로 다시 요청이 되므로 action 속성 생략 가능.
   -->
   <form method="post">
      <div>
         <label>누적합 :
            <input type="text" name = "tot" disabled="disabled" value = <%=tot %>>
         </label>
      </div>
      <div>
         <label>NUM :
            <input type="text" name = "num">
         </label>
      </div>
      
      <button name = "operator" value = "+">+</button>
   </form>
</body>
</html>