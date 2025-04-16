<%@page import="com.psy7758.service.imp.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.psy7758.dto.Notice"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>페이지 네이션 링크를 클릭했을 때, 표시되는 페이지 네이션의 범위 결정</title>
   
   <link rel="stylesheet" href="/static/css/notice_list.css">
</head>

<body>
   <div id="body">
      <main class="main">
         <h2>공지사항</h2>

         <div>
            <form>
               <fieldset>
                  <legend class="hidden">공지사항 검색 필드</legend>
                  <label>검색분류
                     <select>
                        <option value="title">제목</option>
                        <option value="writerId">작성자</option>
                     </select>
                  </label>
                  <label>검색어
                     <input type="text" name="q"   value="" />
                  </label>
                  <input type="submit" value="검색" />
               </fieldset>
            </form>
         </div>
         <hr>

         <div>
            <h3 class="hidden">공지사항 목록</h3>
            <div class="table">
               <div class="w60">번호</div>
               <div class="expand">제목</div>
               <div class="w100">작성자</div>
               <div class="w100">작성일</div>
               <div class="w60">조회수</div>
               
               <c:forEach var="notice" items="${noticesModel}">
                  <div>${notice.id}</div>
                  <div>
                     <a href="detail/page?id=${notice.id}">${notice.title}</a></div>
                  <div>${notice.writer_id}</div>
                  <div>${notice.regDate}</div>
                  <div class="colorRed">${notice.hit}</div>
               </c:forEach>
            </div>
         </div>
         <hr>
         
         <div class="pageNationPart">
            <div>
               <h3 class="hidden">현재 페이지</h3>
               <div>
                  <span>1</span> / 1 pages
               </div>
            </div>

            <div>
               <!-- ================================================================================================================================= -->
               <!-- 링크 파라미터 값 전달에 따른 페이지 네이션 시작 번호 산출 -->
               
               <!--
                  링크를 클릭했을 때 href 를 통해 전달받은 파라미터(pageNum)를 현재 로드 페이지의 페이지 번호로 설정하되,
                  최초 로드시에는 파라미터(pageNum)가 전달되지 않으므로 디폴트 1 로 설정.
               -->
               <c:set var="pageNum" value="${ empty param.pageNum ? 1 : param.pageNum }"/>
               
               <!--
                  파라미터로부터 전달받은 페이지 번호(pageNum)를 통해 페이지 네이션 범위에 대한 시작 번호 산출.
                  
                  ※ 페이지 네이션값이 6 이상이 되는 값들에 대한 테스트는 웹 주소창에서 파라미터 값(pageNum)를 직접 입력받아 테스트.
               -->
               <c:set var="pageNationStartNum" value="${ pageNum - ( pageNum - 1 ) % 5 }"/>
               <ul>
                  <c:forEach var="i" begin="0" end="4" >
                     <!--
                        링크 클릭을 통해 결정된 pageNationStartNum 에 따라, 표시되는 페이지 네이션의 범위가 자동으로 결정.
                     -->
                     <li><a href="?pageNum=${pageNationStartNum + i}">${pageNationStartNum + i}</a></li>
                  </c:forEach>
               </ul>
               
               <div class="btn">
                  <button class="btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</button>
                  <button class="btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</button>
               </div>
            </div>
         </div>
      </main>
   </div>
</body>

</html>