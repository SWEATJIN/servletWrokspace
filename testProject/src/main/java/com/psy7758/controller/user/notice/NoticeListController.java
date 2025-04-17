package com.psy7758.controller.user.notice;

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
       * 당 서블릿의 맵핑 경로가 "/notice/list" 이므로, 상대경로를 찾는 기준이 "/notice/"
       * 디렉토리 내가 되고, list.jsp 파일이 실제 루트폴터(webapp) 아래 notice 폴더내에
       * 존재하므로, 상대경로 적용이 되어 아래와같이 파일명만 지정 가능.
       */
      request.getRequestDispatcher("list.jsp").forward(request, response);
   }
}