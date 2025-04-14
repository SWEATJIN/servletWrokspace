package com.psy7758.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simpleCalculator2/redirectionPage2")
public class RedirectionPage2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=utf-8");
      PrintWriter printWriter = response.getWriter();
      Cookie[] cookies = request.getCookies();
      String tot = "0";
      
      for(Cookie c: cookies) {
         if(c.getName().equals("tot")) {
            tot = c.getValue();
            
            break;
         }
      }
      
      printWriter.print("<!DOCTYPE html>");
      printWriter.print("<html>");
      printWriter.print("<head>");
      printWriter.print("<meta charset=\"UTF-8\">");
      printWriter.print("<title>Cookie 의 맵핑 경로에 따른 저장 및 추출 - setPath</title>");
      printWriter.print("</head>");
      printWriter.print("<body>");
      printWriter.print("   <h1>Cookie 의 맵핑 경로에 따른 저장 및 추출 - setPath</h1>");
      printWriter.print("   <hr>");
      printWriter.print("   <form action=\"/simpleCalculator2\" method=\"post\">");      // 절대 경로로 변경
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