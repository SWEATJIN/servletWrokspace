package com.psy7758.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psy7758.service.imp.AdminService;
@WebServlet("/test1")
public class TestServlet1 extends HttpServlet {
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   response.setContentType("text/html; charset=utf-8");
      PrintWriter printWriter = response.getWriter();
      response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
      response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
      /*
       * service 메서드는 추상 메서드 원형에 throws 가
       * 정의되어 있지 않아, 메서드 내부에서 직접 예외처리를 해야함에 주의.
       */
      try {
         printWriter.println(new AdminService().getClient());
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}