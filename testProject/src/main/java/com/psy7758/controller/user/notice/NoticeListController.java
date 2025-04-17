package com.psy7758.controller.user.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psy7758.service.Service;
import com.psy7758.service.imp.UserService;

@WebServlet("/user/notice/list")
public class NoticeListController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int defaultPageNum = 1;

      String pageNum = request.getParameter("pageNum");
      String searchField = request.getParameter("searchField");
      String searchWord = request.getParameter("searchWord");
      
      Service service = new UserService();

      if (searchWord == null) {
         request.setAttribute("noticesModel", service.getNotices(defaultPageNum));
      } else if (searchWord.equals("")) {
         request.setAttribute("noticesModel", service.getNotices(Integer.parseInt(pageNum)));
      } else {
         request.setAttribute("noticesModel",
               service.getNotices(Integer.parseInt(pageNum), searchField, searchWord));
      }
      
      request.setAttribute("pagingSizeValue", getServletContext().getInitParameter("pagingSizeValue"));
      request.setAttribute("pagenationSet", getServletContext().getInitParameter("pagenationSet"));
      
      // 구조 변경에 따른 포워딩 경로 변경.
      request.getRequestDispatcher("/WEB-INF/view/user/notice/list.jsp").forward(request, response);
   }
}