package com.psy7758.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simpleCalculator")
public class TestServlet4 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      PrintWriter print = response.getWriter();
      int n1 =0 , n2=0;
      
      String num1 = request.getParameter("num1");
      String num2 = request.getParameter("num2");
      String operator = request.getParameter("operator");
      
      
      
      // default 값 설정
      if(!num1.isEmpty()) {
    	  n1=Integer.parseInt(num1);
      }
      if(!num2.isEmpty()) {
    	  n2=Integer.parseInt(num2);
      }
      
      int sum = 0;
      switch (operator) {
      case "+": 
          sum=n1+n2;
          break;
      case "-":
    	  sum=n1-n2;
          break;
      case "*":
    	  sum=n1*n2;
          break;
      case "/":
    	  sum=n1/n2;
          break;
      default:
          break;
      
      }
      
      print.printf("%d %s %d %d",n1,operator,n2,sum);

   }
      
   
}