<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>전체 레코드수 조회를 위한 서비스와 DAO 계층 메서드 추가 및 그에 따른 View(list.jsp) 페이지 추가 구성 - 1</title>
   
   <link rel="stylesheet" href="/static/css/notice_list.css">
   
   <script type="text/javascript">
      function search(){
         const optionField = document.querySelector('.optionField') 
            searchInput = document.querySelector('.searchInput');
         
         if(searchInput.value){
            location.href = '?pageNum=1&' + 'searchField=' + optionField.value + '&searchWord=' + searchInput.value; 
         } else {
            location.href = location.pathname;
         }
      }
      
      function changePageScope(page) {
          location.href = "?pageNum=" + page;
      }
   </script>
</head>

<body>
   <div id="body">
      <main class="main">
         <h2>공지사항</h2>

         <div>
            <div class="hidden">공지사항 검색 필드</div>
            <label>검색분류
               <select class="optionField">
                  <option value="title" ${searchField == 'writer_Id' ? '' : 'selected' }>제목</option>
                  <option value="writer_Id" ${searchField == 'writer_Id' ? 'selected' : '' }>작성자</option>
               </select>
            </label>
            <label>검색어
               <input type="text" class="searchInput" value="${searchWord}" />
            </label>
            <button type="button" onclick="search()">검색</button>
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
                     <a href="detail/page?id=${notice.id}&pageNum=${pageNum}&searchField=${searchField}&searchWord=${searchWord}">${notice.title}</a></div>
                  <div>${notice.writer_id}</div>
                  <div>${notice.regDate}</div>
                  <div class="colorRed">${notice.hit}</div>
               </c:forEach>
            </div>
         </div>
         <hr>
         
         <c:set var="wholePage" value="${ Math.ceil(noticeCnt / pagingSizeValue) }"/>
         <c:set var="pageNationStartNum" value="${ pageNum - ( pageNum - 1 ) % pagenationSet }"/>  <!-- 링크 클릭에 의해 로드된 DB 페이지에 해당하는 페이지 네이션 시작 번호  -->
         
         <div class="pageNationPart">
            <div>
               <h3 class="hidden">현재 페이지</h3>
               <div>
                  <span>${ pageNum }</span> / ${fn:substringBefore(wholePage, '.')} pages
               </div>
            </div>

            <div>
               <ul>
                  <c:forEach var="i" begin="0" end="${pagenationSet - 1 }" >
                     <c:set var="printPageNum" value="${ pageNationStartNum + i }"/>
                     <c:if test="${printPageNum <= wholePage }">
                        <li><a href="?pageNum=${printPageNum}&searchField=${searchField}&searchWord=${searchWord}">${printPageNum}</a></li>
                     </c:if>
                  </c:forEach>
               </ul>
               
               <div class="btn">
                  <c:choose>
                     <c:when test="${pageNationStartNum > 1}">
                        <button class="btn-prev" onclick = "changePageScope(${pageNationStartNum - 1})">이전</button>
                     </c:when>
                     <c:otherwise>
                        <button class="btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</button>
                     </c:otherwise>
                  </c:choose>
                  
                  <c:set var="nextPageNationStartNum" value="${ pageNationStartNum + 5 }"/>
                  <c:choose>
                     <c:when test="${nextPageNationStartNum <= pageNationLastNum }">
                        <button class="btn-next" onclick="changePageScope(${nextPageNationStartNum})">다음</button>
                     </c:when>
                     <c:otherwise>
                        <button class="btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</button>
                     </c:otherwise>
                  </c:choose>
               </div>
            </div>
         </div>
      </main>
   </div>
</body>

</html>