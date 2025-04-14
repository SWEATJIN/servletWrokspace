package com.psy7758.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testEL1")
public class TestEL1 extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setAttribute("EL_data", "요청 데이터");
      request.getSession().setAttribute("EL_data", "세션 데이터");
      request.getServletContext().setAttribute("EL_data", "애플리케이션 데이터");
      
      request.getRequestDispatcher("testEL1.jsp").forward(request, response);
   }
}