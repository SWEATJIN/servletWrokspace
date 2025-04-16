package com.psy7758.controller.notice;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psy7758.service.imp.AdminService;

/*
 * NoticeListController 에서 실행.
 */
@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         request.setAttribute("noticesModel", new AdminService().getNotices());
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      /*
       * WEB-INF 폴더 내 JSP 파일은 반드시 절대경로를 통해 포워딩해야 함에 주의.
       */
      request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
   }
}