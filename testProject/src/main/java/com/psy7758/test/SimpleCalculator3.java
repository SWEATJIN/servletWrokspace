package com.psy7758.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simpleCalculator3")
public class SimpleCalculator3 extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int num = 0;
      String num_ = request.getParameter("num");
      
      if( !num_.equals("") ) {
         Cookie[] cookies = request.getCookies();
         
         num = Integer.parseInt(num_);
         
         String addCookieStr_ = null;
         
         if( cookies == null ) {
            addCookieStr_ =  num + "";
         } else {
            for(Cookie c: cookies) {
               if(c.getName().equals("tot")) {
                  addCookieStr_ = num + Integer.parseInt(c.getValue()) + ""; 
                  
                  break;
               }
            }
         }
         
         Cookie cookie = new Cookie("tot", addCookieStr_);
         
         /*
          * "/simpleCalculator3" 맵핑 경로에서만 쿠키가 전달되도록 설정.
          */
         cookie.setPath("/simpleCalculator3");
         response.addCookie(cookie);
      }
      
      /*
       * sendRedirect 메서드는 기본적으로 GET 요청으로 처리.
       * 
       * 리디렉션 페이지가 자기 자신(SimpleCalculator3)이므로 맵핑 경로를 자기
       * 자신(/simpleCalculator3)으로 설정함으로써, 자신의 doGet 메서드가
       * 호출되도록 처리.
       */
      response.sendRedirect("/simpleCalculator3");
      
   }
   	
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=utf-8");
      PrintWriter printWriter = response.getWriter();
      Cookie[] cookies = request.getCookies();
      String tot = "0";
      
      if(cookies != null) {
         for(Cookie c: cookies) {
            if(c.getName().equals("tot")) {
               tot = c.getValue();
               
               break;
            }
         }
      }
      /*
	   * 쿠키가 존재하지 않는 경우, tot 의 초기값을 0으로 설정.
	   */
      
      printWriter.print("<!DOCTYPE html>");
      printWriter.print("<html>");
      printWriter.print("<head>");
      printWriter.print("<meta charset=\"UTF-8\">");
      printWriter.print("<title>doGet, doPost 메서드를 통한 서블릿 통합</title>");
      printWriter.print("</head>");
      printWriter.print("<body>");
      printWriter.print("   <h1>doGet, doPost 메서드를 통한 서블릿 통합</h1>");
      printWriter.print("   <hr>");
      
      /*
       * form 요청 대상이 자기 자신이므로 action 속성의 경로 생략 가능.
       */
      printWriter.print("   <form method=\"post\">");
      printWriter.print("      <div>");
      printWriter.print("         <label>누적합 : ");
      printWriter.printf("            <input type=\"text\" name = \"tot\" disabled=\"disabled\" value = %s>", tot);
      printWriter.print("         </label>");
      printWriter.print("      </div>");
      printWriter.print("      <div>");
      printWriter.print("         <label>NUM : ");
      printWriter.print("            <input type=\"text\" name = \"num\">");
      printWriter.print("         </label>");
      printWriter.print("      </div>");
      printWriter.print("      <button name = \"operator\" value = \"+\">+</button>");
      printWriter.print("   </form>");
      printWriter.print("</body>");
      printWriter.print("</html>");
   }
}