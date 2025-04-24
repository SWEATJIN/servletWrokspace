package com.psy7758.controller.admin.board;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psy7758.dto.Notice;
import com.psy7758.service.imp.AdminService;

@WebServlet("/admin/notice/board/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminService servie = new AdminService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String pageNum = request.getParameter("pageNum");
	      String searchField = request.getParameter("searchField");
	      String searchWord = request.getParameter("searchWord");

		
	      request.setAttribute("pageNum", pageNum);
	      request.setAttribute("searchField", searchField);
	      request.setAttribute("searchWord", searchWord);
		request.getRequestDispatcher("/WEB-INF/view/admin/notice/board/reg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Notice notice = new ObjectMapper().readValue(request.getInputStream(), Notice.class);
		notice.setRegDate(LocalDateTime.now());
		System.out.println(notice);

		servie.insertNotice(notice);

		
	}

}
