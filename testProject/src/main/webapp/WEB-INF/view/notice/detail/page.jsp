<%@page import="com.psy7758.service.imp.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.psy7758.dto.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>JSP 페이지에 대한 외부 CSS 적용과 서블릿 필터(CharSetServletFilter) 적용시 주의점</title>
   
   <link rel="stylesheet" href="/static/css/notice_detail_page.css">
</head>

<body>
   <div id="body">
      <main>
         <div>
            <h3 class="hidden">공지사항 내용</h3>
            
            <div class="table">
               <div>제목</div>
               <div>${noticeModel.title}</div>
               <div>작성일</div>
               <div>${noticeModel.regDate}</div>
               <div>작성자</div>
               <div>${noticeModel.writer_id}</div>
               <div>조회수</div>
               <div>${noticeModel.hit}</div>
               <div>첨부파일</div>
               <div>${noticeModel.files}</div>
               <div>${noticeModel.content}</div>
            </div>
         </div>
         
         <div class="pageNavigation">
            <div>
               <a href="/notice/list">목록</a>
            </div>

            <div>이전글</div>
            <div><a href="">인터넷 보안의 핵심 원리</a></div>
            
            <div>다음글</div>
            <div>다음글이 없습니다.</div>
         </div>
      </main>
   </div>
</body>

</html>