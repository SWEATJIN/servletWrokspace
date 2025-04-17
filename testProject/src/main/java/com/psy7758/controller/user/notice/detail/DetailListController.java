package com.psy7758.controller.user.notice.detail;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psy7758.service.imp.AdminService;

@WebServlet("/notice/detail/page")
public class DetailListController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int id = Integer.parseInt(request.getParameter("id"));
      
      try {
         request.setAttribute("noticeModel", new AdminService().getNotice(id));
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      /*
       * WEB-INF 폴더 내 JSP 파일은 반드시 절대경로를 통해 포워딩해야 함에 주의.
       */
      request.getRequestDispatcher("/WEB-INF/view/notice/detail/page.jsp").forward(request, response);
   }
}