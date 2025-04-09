package com.psy7758.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sessionOut")
public class SessionOut extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter printWriter = response.getWriter();
      printWriter.print("<!DOCTYPE html>");
      printWriter.print("<html>");
      printWriter.print("<head>");
      printWriter.print("<meta charset=\"UTF-8\">");
      printWriter.print("<title>세션 만료</title>");
      printWriter.print("</head>");
      printWriter.print("<body>");
      printWriter.print("   <h1>야 나가!! ~ 시간 다 됐어</h1>");
      printWriter.print("</body>");
      printWriter.print("</html>");
   }
}