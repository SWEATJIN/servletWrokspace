package com.psy7758.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/redirectionPage")
public class RedirectionPage extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter printWriter = response.getWriter();
      HttpSession session = request.getSession();
      String tot = "0";
      
      Object tot_ = session.getAttribute("tot");
      
      if(tot_ != null) {
         tot = tot_.toString();
      }
      
      printWriter.print("<!DOCTYPE html>");
      printWriter.print("<html>");
      printWriter.print("<head>");
      printWriter.print("<meta charset=\"UTF-8\">");
      printWriter.print("<title>세션 만료에 따른 새로운 세션 생성 여부 설정 및 로그인 유지</title>");
      printWriter.print("</head>");
      printWriter.print("<body>");
      printWriter.print("   <h1>세션 만료에 따른 새로운 세션 생성 여부 설정 및 로그인 유지</h1>");
      printWriter.print("   <hr>");
      printWriter.print("   <form action=\"simpleCalculator\" method=\"post\">");
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
      printWriter.print("      <button name = \"operator\" value = \"=\">=</button>");
      printWriter.print("   </form>");
      printWriter.print("</body>");
      printWriter.print("</html>");
   }
}