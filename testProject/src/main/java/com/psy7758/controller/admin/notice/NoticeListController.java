package com.psy7758.controller.admin.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psy7758.service.imp.AdminService;

/*
 * 프로젝트 내 패키지가 달라도 동일한 서블릿 클래스명이 존재하는 경우에는 서블릿에서 실행시,
 * 로딩 경로가 패키지 경로로 맵핑되어 정상적 실행 불가.
 * 이러한 경우, URL 경로에 실제 맵핑 경로(/admin/notice/list)를 직접 입력하던지 또는,
 * webapp 폴더 내에 맵핑 경로와 동일한 구조를 가지는 더미 폴더와 파일을 생성하여 실행 가능. 
 */
@WebServlet("/admin/notice/list")
public class NoticeListController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int defaultPageNum = 1;
      
      String pageNum = request.getParameter("pageNum");
      String searchField = request.getParameter("searchField");
      String searchWord = request.getParameter("searchWord");
      
      AdminService service = new AdminService();
      
      /*
       *       < getNotices 메서드 호출에 대한 경우의 수 >
       * 
       * 1> 최초 로딩시 : pageNum 또는 searchField 또는 searchWord 중 하나라도 null 로 평가되면 최초 로딩시를 의미하므로,
       *              getNotices(defaultPageNum) 형식으로 호출하여 검색어 없이 최초 1 페이지를 로딩.
       * 
       * 2> 검색어 입력없이 페이지 네이션만 클릭시 : searchWord 가 null 이 아니면서 searchWord 가 빈 문자열("")로 평가되면, 검색어 입력없이
       *                             페이지 네이션만 클릭한 것을 의미하므로 클릭한 페이지 번호만 필요.
       *                              따라서 getNotices(pageNum) 형식으로 파라미터를 통해 전달된 pageNum 만 전달하여 호출.
       *                             단, 검색어 입력없이 검색 버튼을 누른 경우도, searchWord 가 null 이 아니면서 searchWord 가
       *                             빈 문자열("")로 평가되지만, 이 경우에도 검색 버튼을 클릭하는 시점에서의 pageNum 과 빈 문자열이
       *                             담겨진 searchWord 를 그대로 전달할 것이므로, 파라미터를 통해 전달받은 pageNum 을 당 경우와
       *                             동일하게 getNotices(pageNum) 형식으로 그대로 넘겨 처리.
       *                             단, 이러한 경우 페이지 이동 요청이 전혀 필요 없으므로, 효율적인 처리를 위해서는 실제 이벤트가 발생
       *                             하는 프론트단의 뷰페이지(list.jsp) 내에서 버튼 클릭시, 직접 빈 문자열인지를 측정하여 페이지 이동
       *                             요청이 발생지 않도록 하여 당 서블릿이 실행되지 않도록 처리하는 것이 효율적.
       *                             하지만 검색 요청시점에서 데이터 변경이 발생할 수 있므로, 실제 처리는 입력 컨트롤에 빈 문자열이 들어가
       *                             있는 경우에도 당 서블릿에 대한 재요청을 하는 것이 바람직.
       * 
       * 3> 검색어가 입력된 상태에서 검색 버튼 클릭시,   4> 검색어가 입력되어 검색된 상태에서 페이지 네이션 클릭시 
       *  - 위 두 경우 모두, searchWord 가 null 이 아니면서 searchWord 가 빈 문자열("")이 아닌것으로 평가됨에 따라
       *    getNotices(pageNum, searchField, searchWord) 메서드 하나로 통합 처리 가능.
       *  - 3> 검색어가 입력된 상태에서 검색 버튼 클릭시에는, 해당 페이지에서의 pageNum 을 그대로 파라미터로 전달.
       *  - 4> 검색어가 입력되어 검색된 상태에서 페이지 네이션 클릭시에는, 클릭한 페이지 네이션값을 그대로 파라미터로 전달.
       */
      if( searchWord == null ) {
         request.setAttribute("noticesModel", service.getNotices(defaultPageNum));
      } else if ( searchWord.equals("") ) {
         request.setAttribute("noticesModel", service.getNotices(Integer.parseInt(pageNum)));
      } else {
         request.setAttribute("noticesModel", service.getNotices(Integer.parseInt(pageNum), searchField, searchWord));
      }
      
      // 공지사항 페이지에서 기본적으로 페이징할 레코드 갯수(10)를 초기 파라미터를 통해 얻어 요청 객체에 심어 전달.
      request.setAttribute("pagingSizeValue", getServletContext().getInitParameter("pagingSizeValue"));
      
      // 공지사항 페이지에서 페이지 네이션들을 그룹화시킬 페이지 네이션 세트값(5)을 초기 파라미터를 통해 얻어 요청 객체에 심어 전달.
      request.setAttribute("pagenationSet", getServletContext().getInitParameter("pagenationSet"));
      
      // 구조 변경에 따른 포워딩 경로 변경.
      request.getRequestDispatcher("/WEB-INF/view/admin/notice/list.jsp").forward(request, response);
   }
}