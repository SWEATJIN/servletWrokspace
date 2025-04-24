package com.psy7758.controller.admin.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psy7758.dto.view.notice.NoticeView;
import com.psy7758.service.imp.AdminService;

@WebServlet("/admin/notice/list")
public class NoticeListController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private final AdminService service = new AdminService();
   
   public static class PubDelData {
      private int[] pubTrueId_;
      private int[] pubFalseId_;
      private int[] delNotice;
      private String pudDelBtn;
      
      public PubDelData() {}

      public PubDelData(int[] pubTrueId_, int[] pubFalseId_, int[] delNotice, String pudDelBtn) {
         this.pubTrueId_ = pubTrueId_;
         this.pubFalseId_ = pubFalseId_;
         this.delNotice = delNotice;
         this.pudDelBtn = pudDelBtn;
      }

      public int[] getPubTrueId_() {
         return pubTrueId_;
      }

      public void setPubTrueId_(int[] pubTrueId_) {
         this.pubTrueId_ = pubTrueId_;
      }

      public int[] getPubFalseId_() {
         return pubFalseId_;
      }

      public void setPubFalseId_(int[] pubFalseId_) {
         this.pubFalseId_ = pubFalseId_;
      }

      public int[] getDelNotice() {
         return delNotice;
      }

      public void setDelNotice(int[] delNotice) {
         this.delNotice = delNotice;
      }

      public String getPudDelBtn() {
         return pudDelBtn;
      }

      public void setPudDelBtn(String pudDelBtn) {
         this.pudDelBtn = pudDelBtn;
      }
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int defaultPageNum = 1;
      
      String pageNum = request.getParameter("pageNum");
      String searchField = request.getParameter("searchField");
      String searchWord = request.getParameter("searchWord");
      
      List<NoticeView> notices = null;
      int noticeCnt = 0;
      
      if( searchWord == null ) {
         notices = service.getNotices(defaultPageNum);
         noticeCnt = service.getNoticeCnt();
         
         pageNum = String.valueOf(defaultPageNum);
      } else if ( searchWord.equals("") ) {
         notices = service.getNotices(Integer.parseInt(pageNum));
         noticeCnt = service.getNoticeCnt();
      } else {
         notices = service.getNotices(Integer.parseInt(pageNum), searchField, searchWord);
         noticeCnt = service.getNoticeCnt(searchField, searchWord);
      }
      
      request.setAttribute("pagingSizeValue", getServletContext().getInitParameter("pagingSizeValue"));
      request.setAttribute("pagenationSet", getServletContext().getInitParameter("pagenationSet"));
      request.setAttribute("noticeViews", notices);
      request.setAttribute("noticeCnt", noticeCnt);
      request.setAttribute("pageNum", pageNum);
      request.setAttribute("searchField", searchField);
      request.setAttribute("searchWord", searchWord);
      
      request.getRequestDispatcher("/WEB-INF/view/admin/notice/list.jsp").forward(request, response);
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PubDelData pubData = new ObjectMapper().readValue(request.getInputStream(), PubDelData.class);
      
      if( pubData.getPudDelBtn().equals("batchPubBtn") ) {
         service.setPub(pubData.getPubTrueId_(), pubData.getPubFalseId_());
      } else {
         service.delNotice(pubData.getDelNotice());
         
         /*
          * 프론트에서 Axios 를 이용한 AJAX 통신을 하므로, 서블릿에서 리디렉트 처리 불가.
          */
      }
   }
}