package com.psy7758.insertMain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertMain")
public class InsertMain extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setAttribute("mainPage", "메인 페이지");
      request.setAttribute("section", "섹션");
      request.setAttribute("aside", "어사이드");
      
      /*
       * 포워딩시에는 웹 애플리케이션 내의 webapp 폴더를 기준으로 한 절대 또는 상대 경로를 지정.
       */
      request.getRequestDispatcher("/WEB-INF/insertPage/insertMain.jsp").forward(request, response);
   }
}